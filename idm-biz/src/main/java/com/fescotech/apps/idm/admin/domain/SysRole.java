package com.fescotech.apps.idm.admin.domain;

import java.util.Date;

/**
 * 
 */
public class SysRole {

	/**
	 * 
	 */
	private Long roleId; 
	
	/**
	 * 
	 */
	private String remark; 
	
	/**
	 * 
	 */
	private Date createTime; 
	
	/**
	 * 
	 */
	private String roleName; 
	
	
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
	public String getRemark(){
		return remark;
	} 
	
	/**
	 * 设置 
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	/**
	 * 读取
	 */
	public Date getCreateTime(){
		return createTime;
	} 
	
	/**
	 * 设置 
	 */
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	/**
	 * 读取
	 */
	public String getRoleName(){
		return roleName;
	} 
	
	/**
	 * 设置 
	 */
	public void setRoleName(String roleName){
		this.roleName = roleName;
	}
	
	
}
