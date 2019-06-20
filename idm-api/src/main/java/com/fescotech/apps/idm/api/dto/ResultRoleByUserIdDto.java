package com.fescotech.apps.idm.api.dto;

public class ResultRoleByUserIdDto {

	/**
	 * 用户ID
	 */
	private Long roleId; 
	
	/**
	 * 登录账号
	 */
	private String roleName;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	} 
	
}
