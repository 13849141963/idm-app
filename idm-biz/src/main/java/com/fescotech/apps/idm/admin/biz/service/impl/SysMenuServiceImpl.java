package com.fescotech.apps.idm.admin.biz.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.fescotech.apps.idm.admin.biz.bo.ISysMenuBo;
import com.fescotech.apps.idm.admin.biz.bo.ISysUserBo;
import com.fescotech.apps.idm.admin.biz.service.ISysMenuService;
import com.fescotech.apps.idm.admin.domain.SysMenu;
import com.fescotech.apps.idm.admin.domain.vo.SysMenuVo;
import com.fescotech.apps.idm.util.Constant.MenuType;
@Service("sysMenuService")
public class SysMenuServiceImpl implements ISysMenuService {
	private ISysMenuBo sysMenuBo;
	private ISysUserBo sysUserBo;
	@Override
	public List<SysMenuVo> queryListParentId(Long parentId, List<Long> menuIdList) {
		List<SysMenuVo> menuList = sysMenuBo.queryListParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}
		
		List<SysMenuVo> userMenuList = new ArrayList<>();
		for(SysMenuVo menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<SysMenuVo> queryNotButtonList() {
		return sysMenuBo.queryNotButtonList();
	}

	@Override
	public List<SysMenuVo> getUserMenuList(Long userId) {
		//系统管理员，拥有最高权限
		if(userId == 1){
			return getAllMenuList(null);
		}
		
		//用户菜单列表
		List<Long> menuIdList = sysUserBo.queryAllMenuId(userId);
		return getAllMenuList(menuIdList);
	}
	
	@Override
	public SysMenu queryObject(Long menuId) {
		return sysMenuBo.loadSysMenu(menuId);
	}

	@Override
	public List<SysMenuVo> queryList(Map<String, Object> map) {
		if(!map.containsKey("start")){
			String json = JSON.toJSONString(map);
			SysMenuVo smv = JSON.parseObject(json, SysMenuVo.class);
			return sysMenuBo.querySysMenu(smv);
		}
		
		return sysMenuBo.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysMenuBo.queryTotal(map);
	}

	@Override
	public void save(SysMenu menu) {
		sysMenuBo.insert(menu);
	}

	@Override
	public void update(SysMenu menu) {
		sysMenuBo.update(menu);
	}
	@Override
	public void deleteBatch(Long[] menuIds) {
		sysMenuBo.deleteBatch(menuIds);
	}
	
	/**
	 * 获取所有菜单列表
	 */
	private List<SysMenuVo> getAllMenuList(List<Long> menuIdList){
		//查询根菜单列表
		List<SysMenuVo> menuList = queryListParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);
		
		return menuList;
	}
	/**
	 * 递归
	 */
	private List<SysMenuVo> getMenuTreeList(List<SysMenuVo> menuList, List<Long> menuIdList){
		List<SysMenuVo> subMenuList = new ArrayList<SysMenuVo>();
		
		for(SysMenuVo entity : menuList){
			if(entity.getMenuType() == MenuType.CATALOG.getValue()){//目录
				entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}
		
		return subMenuList;
	}

	public ISysMenuBo getSysMenuBo() {
		return sysMenuBo;
	}

	public void setSysMenuBo(ISysMenuBo sysMenuBo) {
		this.sysMenuBo = sysMenuBo;
	}

	public ISysUserBo getSysUserBo() {
		return sysUserBo;
	}

	public void setSysUserBo(ISysUserBo sysUserBo) {
		this.sysUserBo = sysUserBo;
	}
}