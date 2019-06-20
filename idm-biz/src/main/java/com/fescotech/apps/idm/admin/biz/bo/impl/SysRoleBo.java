package com.fescotech.apps.idm.admin.biz.bo.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.admin.biz.bo.ISysRoleBo;
import com.fescotech.apps.idm.admin.domain.SysRole;
import com.fescotech.apps.idm.admin.domain.vo.SysRoleVo;
public class SysRoleBo extends AbstractBoT implements ISysRoleBo{
	public void insert(SysRole sysRole){
		baseDao.insert("sysRoleSql.insert",sysRole);
	}
	
	public void update(SysRole sysRole){
		baseDao.update("sysRoleSql.update",sysRole);
	}
	
	public List<SysRole> querySysRole(SysRole sysRole){
		return baseDao.queryForList("sysRoleSql.querySysRole",sysRole,SysRole.class);
	}
	
	public SysRoleVo loadSysRole(Long roleId){
		return (SysRoleVo)baseDao.queryForObject("sysRoleSql.queryByKey",roleId);
	}

	@Override
	public List<SysRole> queryList(Map<String, Object> map) {
		return baseDao.queryForList("sysRoleSql.queryList",map,SysRole.class);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return (int)baseDao.queryForObject("sysRoleSql.queryTotal",map);
	}

	@Override
	public void deleteBatch(Long[] roleIds) {
		baseDao.delete("sysRoleSql.deleteRole", roleIds);
		baseDao.delete("sysRoleSql.deleteSysRole", roleIds);
		baseDao.delete("sysRoleSql.deleteUserRole", roleIds);
	}

	@Override
	public List<SysRole> queryAllList() {
		return baseDao.queryForList("sysRoleSql.queryAllList", new HashMap<>(), SysRole.class);
	}
}