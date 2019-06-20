package com.fescotech.apps.idm.admin.biz.bo;
import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.admin.domain.SysRole;
import com.fescotech.apps.idm.admin.domain.vo.SysRoleVo;
public interface ISysRoleBo {
	public void insert(SysRole sysRole);
	
	public void update(SysRole sysRole);
	
	public List<SysRole> querySysRole(SysRole sysRole);
	
	public SysRoleVo loadSysRole(Long roleId);
	
	
	public int queryTotal(Map<String, Object> map);
	
	public void deleteBatch(Long[] roleIds);

	public List<SysRole> queryList(Map<String, Object> map);

	public List<SysRole> queryAllList();
}