package com.fescotech.apps.idm.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fescotech.apps.idm.admin.biz.service.ISysMenuService;
import com.fescotech.apps.idm.admin.domain.SysMenu;
import com.fescotech.apps.idm.admin.domain.vo.R;
import com.fescotech.apps.idm.admin.domain.vo.SysMenuVo;
import com.fescotech.apps.idm.util.Constant.MenuType;
import com.fescotech.apps.idm.util.Pager;
import com.fescotech.apps.idm.web.dto.PageDto;

/**
 * 系统菜单
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {
	@Autowired
	private ISysMenuService sysMenuService;
	
	/**
	 * 所有菜单列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:menu:list")
	public R list(PageDto dto){
		Integer page=dto.getPage();
		Integer limit=dto.getLimit();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", (page - 1) * limit);
		map.put("end", page*limit);
		map.put("orderBy", dto.getSidx());
		map.put("orderFlag", dto.getOrder());
		//查询列表数据
		List<SysMenuVo> menuList = sysMenuService.queryList(map);
		int total = sysMenuService.queryTotal(map);
		
		Pager pageUtil = new Pager(menuList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:menu:select")
	public R select(){
		//查询列表数据
		List<SysMenuVo> menuList = sysMenuService.queryNotButtonList();	
		return R.ok().put("menuList", menuList);
	}
	
	/**
	 * 角色授权菜单
	 */
	@RequestMapping("/perms")
	@RequiresPermissions("sys:menu:perms")
	public R perms(){
		//查询列表数据
		List<SysMenuVo> menuList = sysMenuService.queryList(new HashMap<String, Object>());
		
		return R.ok().put("menuList", menuList);
	}
	
	/**
	 * 菜单信息
	 */
	@RequestMapping("/info/{menuId}")
	@RequiresPermissions("sys:menu:info")
	public R info(@PathVariable("menuId") Long menuId){
		SysMenu  menu = sysMenuService.queryObject(menuId);
		return R.ok().put("menu", menu);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:menu:save")
	public R save(@RequestBody SysMenuVo menu){
		//数据校验
		String errorMsg = verifyForm(menu);
		if(!errorMsg.equals(""))
			return R.error(errorMsg);
		sysMenuService.save(menu);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:menu:update")
	public R update(@RequestBody SysMenuVo menu){
		//数据校验
		String errorMsg = verifyForm(menu);
		if(!errorMsg.equals(""))
			return R.error(errorMsg);
		sysMenuService.update(menu);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:menu:delete")
	public R delete(@RequestBody Long[] menuIds){
//		for(Long menuId : menuIds){
//			if(menuId.longValue() <= 28){
//				return R.error("系统菜单，不能删除");
//			}
//		}
		sysMenuService.deleteBatch(menuIds);
		
		return R.ok();
	}
	
	/**
	 * 用户菜单列表
	 */
	@RequestMapping("/user")
	public R user(){
		List<SysMenuVo> menuList = sysMenuService.getUserMenuList(getUserId());
		
		return R.ok().put("menuList", menuList);
	}
	
	/**
	 * 验证参数是否正确
	 */
	private String verifyForm(SysMenuVo menu){
		String errorMsg = "";
		if(StringUtils.isBlank(menu.getName())){
			errorMsg = "菜单名称不能为空";
		} 
		
		if(menu.getParentId() == null){
			errorMsg = "上级菜单不能为空";
		}
		
		//上级菜单类型
		int parentType = MenuType.CATALOG.getValue();
		if(menu.getParentId() != 0){
			SysMenu  parentMenu = sysMenuService.queryObject(menu.getParentId());
			parentType = parentMenu.getMenuType();
		}
		
		if(menu.getMenuType() == null){
			errorMsg = "菜单类型不能为空";
		}
		
//		//目录、菜单
//		if(menu.getMenuType() == MenuType.CATALOG.getValue() ||
//				menu.getMenuType() == MenuType.MENU.getValue()){
//			if(parentType != MenuType.CATALOG.getValue()){
//				errorMsg = "上级菜单只能为目录类型";
//			}
//		}
		
		//按钮
		if(menu.getMenuType() == MenuType.BUTTON.getValue()){
			if(parentType != MenuType.MENU.getValue()){
				errorMsg = "上级菜单只能为菜单类型";
			}
		}
		return errorMsg;
	}
}
