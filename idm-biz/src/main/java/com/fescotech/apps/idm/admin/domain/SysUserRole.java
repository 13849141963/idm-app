package com.fescotech.apps.idm.admin.domain;


/**
 * 
 */
public class SysUserRole {

	/**
	 * 
	 */
	private Long userRoleId; 
	
	/**
	 * 
	 */
	private Long roleId; 
	
	/**
	 * 
	 */
	private Long userId; 
	
	
	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	/**
	 * 读取
	 */
	public Long getRoleId(){
		return roleId;
	} 
	
	/**
	 * 设置 
	 */
	public void setRoleId(Long roleId){
		this.roleId = roleId;
	}
	
	/**
	 * 读取
	 */
	public Long getUserId(){
		return userId;
	} 
	
	/**
	 * 设置 
	 */
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	
}
