package com.fescotech.apps.idm.admin.biz.bo;
import java.util.List;

import com.fescotech.apps.idm.admin.domain.SysRole;
import com.fescotech.apps.idm.admin.domain.SysRoleMenu;
public interface ISysRoleMenuBo {
	public void insert(SysRoleMenu sysRoleMenu);
	
	public void update(SysRoleMenu sysRoleMenu);
	
	public List<SysRoleMenu> querySysRoleMenu(SysRoleMenu sysRoleMenu);
	
	public SysRoleMenu loadSysRoleMenu(Long id);
	
	public int deleteByRoleId(Long roleId);
	
	public List<Long> queryMenuIdList(Long roleId);
	
	public void saveOrUpdate(Long roleId, List<Long> menuIdList);

}