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
import com.fescotech.apps.idm.admin.biz.service.ISysRoleService;
import com.fescotech.apps.idm.admin.domain.SysRole;
import com.fescotech.apps.idm.admin.domain.vo.R;
import com.fescotech.apps.idm.admin.domain.vo.SysRoleVo;
import com.fescotech.apps.idm.util.Pager;

/**
 * 角色管理
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
	@Autowired
	private ISysRoleService sysRoleService ;
	/**
	 * 角色列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:role:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", (page - 1) * limit);
		map.put("end", page*limit);
		
		//查询列表数据
		List<SysRole> list = sysRoleService.queryList(map);
		int total = sysRoleService.queryTotal(map);
		
		Pager pageUtil = new Pager(list, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 角色列表
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:role:select")
	public R select(){
		//查询列表数据
		List<SysRole> list = sysRoleService.queryAllList();
		return R.ok().put("list", list);
	}
	
	/**
	 * 角色信息
	 */
	@RequestMapping("/info/{roleId}")
	@RequiresPermissions("sys:role:info")
	public R info(@PathVariable("roleId") Long roleId){
		SysRoleVo  role = sysRoleService.queryObject(roleId);
		//查询角色对应的菜单
		List<Long> menuIdList = sysRoleService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);
		return R.ok().put("role", role);
	}
	
	/**
	 * 保存角色
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:role:save")
	public R save(@RequestBody SysRoleVo role){
		if(StringUtils.isBlank(role.getRoleName())){
			return R.error("角色名称不能为空");
		}
		sysRoleService.save(role);
		return R.ok();
	}
	
	/**
	 * 修改角色
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:role:update")
	public R update(@RequestBody SysRoleVo role){
		if(StringUtils.isBlank(role.getRoleName())){
			return R.error("角色名称不能为空");
		}
		sysRoleService.update(role);
		return R.ok();
	}
	
	/**
	 * 删除角色
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:role:delete")
	public R delete(@RequestBody Long[] roleIds){
		sysRoleService.deleteBatch(roleIds);
		return R.ok();
	}
}
