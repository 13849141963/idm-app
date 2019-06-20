package com.fescotech.apps.idm.base.domain;

import java.util.Date;

/**
 * 
 */
public class BaseUserRoleData {

	/**
	 * 用户角色数据权限ID
	 */
	private Long baseUserRoleDataId; 
	
	/**
	 * 用户角色关系ID
	 */
	private Long baseUserRoleId; 
	
	/**
	 * 角色对应数据权限机构ID
	 */
	private Long orgId; 
	
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
	 * 数据权限状态(字典码:data_valid_state)
	 */
	private Integer dataState; 
	
	
	/**
	 * 读取用户角色数据权限ID
	 */
	public Long getBaseUserRoleDataId(){
		return baseUserRoleDataId;
	} 
	
	/**
	 * 设置 用户角色数据权限ID
	 */
	public void setBaseUserRoleDataId(Long baseUserRoleDataId){
		this.baseUserRoleDataId = baseUserRoleDataId;
	}
	
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
	 * 读取角色对应数据权限机构ID
	 */
	public Long getOrgId(){
		return orgId;
	} 
	
	/**
	 * 设置 角色对应数据权限机构ID
	 */
	public void setOrgId(Long orgId){
		this.orgId = orgId;
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
	 * 读取数据权限状态(字典码:data_valid_state)
	 */
	public Integer getDataState(){
		return dataState;
	} 
	
	/**
	 * 设置 数据权限状态(字典码:data_valid_state)
	 */
	public void setDataState(Integer dataState){
		this.dataState = dataState;
	}
	
	
}
