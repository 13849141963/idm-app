package com.fescotech.apps.idm.api.dto;

public class ResultUserByRoleIdDto {

	/**
	 * 用户ID
	 */
	private Long userId; 
	
	/**
	 * 登录账号
	 */
	private String loginName; 
	
	/**
	 * 用户名称
	 */
	private String userName; 

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
