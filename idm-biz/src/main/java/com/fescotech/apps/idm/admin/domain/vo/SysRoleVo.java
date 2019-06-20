package com.fescotech.apps.idm.admin.domain.vo;

import java.util.List;

import com.fescotech.apps.idm.admin.domain.SysRole;

public class SysRoleVo extends SysRole {

	private List<Long> menuIdList;

	public List<Long> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<Long> menuIdList) {
		this.menuIdList = menuIdList;
	}
	
}
