package com.fescotech.apps.idm.admin.biz.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fescotech.apps.idm.admin.biz.bo.ISysRoleBo;
import com.fescotech.apps.idm.admin.biz.bo.ISysRoleMenuBo;
import com.fescotech.apps.idm.admin.biz.service.ISysRoleService;
import com.fescotech.apps.idm.admin.domain.SysRole;
import com.fescotech.apps.idm.admin.domain.vo.SysRoleVo;
/**
 * 角色
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements ISysRoleService {
	private ISysRoleBo sysRoleBo;
	private ISysRoleMenuBo sysRoleMenuBo;
	@Override
	public SysRoleVo  queryObject(Long roleId) {
		return sysRoleBo.loadSysRole(roleId);
	}

	@Override
	public List<SysRole> queryList(Map<String, Object> map) {
		return sysRoleBo.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysRoleBo.queryTotal(map);
	}

	@Override
	public void save(SysRoleVo role) {
		role.setCreateTime(new Date());
		sysRoleBo.insert(role);	
		if(role.getMenuIdList()!=null && !role.getMenuIdList().isEmpty()){
			//保存角色与菜单关系
			sysRoleMenuBo.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
		}
	}

	@Override
	public void update(SysRoleVo role) {
		sysRoleBo.update(role);		
		//更新角色与菜单关系
		sysRoleMenuBo.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Override
	public void deleteBatch(Long[] roleIds) {
		sysRoleBo.deleteBatch(roleIds);
	}

	public ISysRoleBo getSysRoleBo() {
		return sysRoleBo;
	}

	public void setSysRoleBo(ISysRoleBo sysRoleBo) {
		this.sysRoleBo = sysRoleBo;
	}

	public ISysRoleMenuBo getSysRoleMenuBo() {
		return sysRoleMenuBo;
	}

	public void setSysRoleMenuBo(ISysRoleMenuBo sysRoleMenuBo) {
		this.sysRoleMenuBo = sysRoleMenuBo;
	}

	@Override
	public List<Long> queryMenuIdList(Long roleId) {
		 return sysRoleMenuBo.queryMenuIdList(roleId);
	}

	@Override
	public List<SysRole> queryAllList() {
		return sysRoleBo.queryAllList();
	}

}
