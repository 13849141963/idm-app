package com.fescotech.apps.idm.base.domain;

import java.util.Date;

/**
 * 
 */
public class BaseUserRole {

	/**
	 * 用户角色关系ID
	 */
	private Long baseUserRoleId; 
	
	/**
	 * 用户ID
	 */
	private Long userId; 
	
	/**
	 * 角色ID
	 */
	private Long roleId; 
	/*
	 * 角色名
	 * */
	private String roleName;
	
	/**
	 * 角色所在机构ID
	 */
	private Long orgId; 
	
	
	/**
	 * 关联的非标准角色ID
	 */
	private Long hideRoleId; 
	
	/**
	 * 用户角色权限状态(有效、已删除)，1有效，2删除
	 */
	private Integer userRoleState; 
	
	/**
	 * 创建人
	 */
	private Long createId; 
	
	/**
	 * 创建时间
	 */
	private Date createTime; 
	
	/**
	 * 修改人
	 */
	private Long operator; 
	
	/**
	 * 修改时间
	 */
	private Date operTime; 
	
	/**
	 * 用户名称（部门名称）
	 */
	private String userName; 
	
	
	/**
	 * 读取用户角色关系ID
	 */
	public Long getBaseUserRoleId(){
		return baseUserRoleId;
	} 
	
	/**
	 * 设置 用户角色关系ID
	 */
	public void setBaseUserRoleId(Long baseUserRoleId){
		this.baseUserRoleId = baseUserRoleId;
	}
	
	/**
	 * 读取用户ID
	 */
	public Long getUserId(){
		return userId;
	} 
	
	/**
	 * 设置 用户ID
	 */
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	/**
	 * 读取角色ID
	 */
	public Long getRoleId(){
		return roleId;
	} 
	
	/**
	 * 设置 角色ID
	 */
	public void setRoleId(Long roleId){
		this.roleId = roleId;
	}
	
	/**
	 * 读取角色所在机构ID
	 */
	public Long getOrgId(){
		return orgId;
	} 
	
	/**
	 * 设置 角色所在机构ID
	 */
	public void setOrgId(Long orgId){
		this.orgId = orgId;
	}
	
	/**
	 * 读取关联的非标准角色ID
	 */
	public Long getHideRoleId(){
		return hideRoleId;
	} 
	
	/**
	 * 设置 关联的非标准角色ID
	 */
	public void setHideRoleId(Long hideRoleId){
		this.hideRoleId = hideRoleId;
	}
	
	/**
	 * 读取用户角色权限状态(有效、已删除)
	 */
	public Integer getUserRoleState(){
		return userRoleState;
	} 
	
	/**
	 * 设置 用户角色权限状态(有效、已删除)
	 */
	public void setUserRoleState(Integer userRoleState){
		this.userRoleState = userRoleState;
	}
	
	/**
	 * 读取创建人
	 */
	public Long getCreateId(){
		return createId;
	} 
	
	/**
	 * 设置 创建人
	 */
	public void setCreateId(Long createId){
		this.createId = createId;
	}
	
	/**
	 * 读取创建时间
	 */
	public Date getCreateTime(){
		return createTime;
	} 
	
	/**
	 * 设置 创建时间
	 */
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	/**
	 * 读取修改人
	 */
	public Long getOperator(){
		return operator;
	} 
	
	/**
	 * 设置 修改人
	 */
	public void setOperator(Long operator){
		this.operator = operator;
	}
	
	/**
	 * 读取修改时间
	 */
	public Date getOperTime(){
		return operTime;
	} 
	
	/**
	 * 设置 修改时间
	 */
	public void setOperTime(Date operTime){
		this.operTime = operTime;
	}
	
	/**
	 * 读取用户名称（部门名称）
	 */
	public String getUserName(){
		return userName;
	} 
	
	/**
	 * 设置 用户名称（部门名称）
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
