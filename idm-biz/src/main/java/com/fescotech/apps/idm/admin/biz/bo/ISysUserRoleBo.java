package com.fescotech.apps.idm.admin.biz.bo;
import java.util.List;

import com.fescotech.apps.idm.admin.domain.SysUserRole;

public interface ISysUserRoleBo {
	public void insert(SysUserRole sysUserRole);
	
	public void update(SysUserRole sysUserRole);
	
	public List<SysUserRole> querySysUserRole(SysUserRole sysUserRole);
	
	public SysUserRole loadSysUserRole(Long id);
	
	public int deleteByUserId(Long userId);
	
	public List<Long> queryRoleIdList(Long userId);
	
	public void saveOrUpdate(Long userId, List<Long> roleIdList) ;
}