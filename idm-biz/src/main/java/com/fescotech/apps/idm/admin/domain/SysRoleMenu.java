package com.fescotech.apps.idm.admin.domain;


/**
 * 
 */
public class SysRoleMenu {

	/**
	 * 
	 */
	private Long roleMenuId; 
	
	/**
	 * 
	 */
	private Long menuId; 
	
	/**
	 * 
	 */
	private Long roleId; 
	
		 
	public Long getRoleMenuId() {
		return roleMenuId;
	}

	public void setRoleMenuId(Long roleMenuId) {
		this.roleMenuId = roleMenuId;
	}

	/**
	 * 读取
	 */
	public Long getMenuId(){
		return menuId;
	} 
	
	/**
	 * 设置 
	 */
	public void setMenuId(Long menuId){
		this.menuId = menuId;
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
	
	
}
