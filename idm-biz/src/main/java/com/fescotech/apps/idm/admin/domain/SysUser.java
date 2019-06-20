package com.fescotech.apps.idm.admin.domain;

import java.util.Date;
import java.util.List;

/**
 * 
 */
public class SysUser implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4136277098310905243L;

	/**
	 * 
	 */
	private String username; 
	
	/**
	 * 
	 */
	private Integer status; 
	
	/**
	 * 
	 */
	private String email; 
	
	/**
	 * 
	 */
	private Date createTime; 
	
	/**
	 * 
	 */
	private Long userId; 
	
	/**
	 * 
	 */
	private String password; 
	
	/**
	 * 
	 */
	private String mobile; 
	
	private List<SysRole> roleList;
	
	private String sysId;

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	/**
	 * 读取
	 */
	public String getUsername(){
		return username;
	} 
	
	/**
	 * 设置 
	 */
	public void setUsername(String username){
		this.username = username;
	}
	
	/**
	 * 读取
	 */
	public Integer getStatus(){
		return status;
	} 
	
	/**
	 * 设置 
	 */
	public void setStatus(Integer status){
		this.status = status;
	}
	
	/**
	 * 读取
	 */
	public String getEmail(){
		return email;
	} 
	
	/**
	 * 设置 
	 */
	public void setEmail(String email){
		this.email = email;
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
	public Long getUserId(){
		return userId;
	} 
	
	/**
	 * 设置 
	 */
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	/**
	 * 读取
	 */
	public String getPassword(){
		return password;
	} 
	
	/**
	 * 设置 
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	/**
	 * 读取
	 */
	public String getMobile(){
		return mobile;
	} 
	
	/**
	 * 设置 
	 */
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	
}
