package com.fescotech.apps.idm.admin.biz.bo.impl;
import java.util.List;

import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.admin.biz.bo.ISysUserRoleBo;
import com.fescotech.apps.idm.admin.domain.SysUserRole;

public class SysUserRoleBo extends AbstractBoT implements ISysUserRoleBo{
	public void insert(SysUserRole sysUserRole){
		baseDao.insert("sysUserRoleSql.insert",sysUserRole);
	}
	
	public void update(SysUserRole sysUserRole){
		baseDao.update("sysUserRoleSql.update",sysUserRole);
	}
	
	public List<SysUserRole> querySysUserRole(SysUserRole sysUserRole){
		return baseDao.queryForList("sysUserRoleSql.querySysUserRole",sysUserRole,SysUserRole.class);
	}
	
	public SysUserRole loadSysUserRole(Long id){
		return (SysUserRole)baseDao.queryForObject("sysUserRoleSql.queryByKey",id);
	}

	@Override
	public int deleteByUserId(Long userId) {
		return baseDao.delete("sysUserRoleSql.deleteByUserId",userId);
	}

	@Override
	public List<Long> queryRoleIdList(Long userId) {
		return baseDao.queryForList("sysUserRoleSql.queryRoleIdList",userId,Long.class);
	}
	
	public void saveOrUpdate(Long userId, List<Long> roleIdList) {
		if(roleIdList.size() == 0){
			return ;
		}		
		//先删除用户与角色关系
		this.deleteByUserId(userId);		
		//保存用户与角色关系
		for(Long roleId : roleIdList){
			SysUserRole sur =new SysUserRole();
			sur.setRoleId(roleId);
			sur.setUserId(userId);
			this.insert(sur);
		}
	}
}