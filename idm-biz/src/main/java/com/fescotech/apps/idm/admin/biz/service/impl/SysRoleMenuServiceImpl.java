package com.fescotech.apps.idm.admin.biz.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fescotech.apps.idm.admin.biz.bo.ISysRoleMenuBo;
import com.fescotech.apps.idm.admin.biz.service.ISysRoleMenuService;
/**
 * 角色与菜单对应关系
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl implements ISysRoleMenuService {
	private ISysRoleMenuBo sysRoleMenuBo;

	@Override
	public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
		 sysRoleMenuBo.saveOrUpdate(roleId, menuIdList);
	}

	@Override
	public List<Long> queryMenuIdList(Long roleId) {
		return sysRoleMenuBo.queryMenuIdList(roleId);
	}

	public ISysRoleMenuBo getSysRoleMenuBo() {
		return sysRoleMenuBo;
	}

	public void setSysRoleMenuBo(ISysRoleMenuBo sysRoleMenuBo) {
		this.sysRoleMenuBo = sysRoleMenuBo;
	}

}
