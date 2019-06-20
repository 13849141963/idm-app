package com.fescotech.apps.idm.base.domain;

import java.util.Date;

/**
 * 角色管理实体类
 * @author:lzl
 * @time:2017年6月19日 下午6:42:41
 */
public class BaseRole {

	/**
	 * 主键角色id
	 */
	private Long roleId; 
	
	/**
	 * 公司ID
	 */
	private Long corpId; 
	
	/**
	 * 角色分类：字典 ROLE_FUN_TYPE
	 */
	private Integer roleCate; 
	
	/**
	 * 角色名称
	 */
	private String roleName; 
	
	/**
	 * 角色编码
	 */
	private String roleCode; 
	
	/**
	 * 角色类型（产品专员、产品部经理、销售顾问、销售部经理、业务代表、业务部经理）
	 */
	private Integer roleType; 
	
	/**
	 * 创建时间
	 */
	private Date buildTime; 
	
	/**
	 * 描述
	 */
	private String roleDesc; 
	
	/**
	 * 有效状态
	 */
	private Integer userStatus; 
	
	/**
	 * 排序
	 */
	private Long roleOrder; 
	
	/**
	 * 操作人
	 */
	private Long roleOperator; 
	
	/**
	 * 操作时间
	 */
	private Date operTime; 
	/**
	 * ROLE_STATUS
	 */
	private Integer roleStatus; 
	/**
	 * 系统ID
	 */
	private Integer sysId;
	
	/**
	 * 创建人id
	 */
	private Long creator;
	
	/**
	 * 创建人名称
	 */
	private String creatorName;
	
	/**
	 * 创建人组织
	 */
	private Long creatorOrg;
	
	/**
	 * 读取创建人名称
	 * @return
	 */
	public String getCreatorName() {
		return creatorName;
	}

	/**
	 * 设置创建人名称
	 * @param creatorName
	 */
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	/**
	 * 读取创建人机构
	 * @return
	 */
	public Long getCreatorOrg() {
		return creatorOrg;
	}

	/**
	 * 设置创建人机构
	 * @param creatorOrg
	 */
	public void setCreatorOrg(Long creatorOrg) {
		this.creatorOrg = creatorOrg;
	}

	/**
	 * 读取创建人id
	 * @return
	 */
	public Long getCreator() {
		return creator;
	}

	/**
	 * 设置创建人id
	 * @param creator
	 */
	public void setCreator(Long creator) {
		this.creator = creator;
	}

	/**
	 * 读取描述
	 * @return
	 */
	public String getRoleDesc() {
		return roleDesc;
	}

	/**
	 * 设置描述
	 * @param roleDesc
	 */
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	/**
	 * 读取排序
	 * @return
	 */
	public Long getRoleOrder() {
		return roleOrder;
	}

	/**
	 * 设置排序
	 * @param roleOrder
	 */
	public void setRoleOrder(Long roleOrder) {
		this.roleOrder = roleOrder;
	}

	/**
	 * 读取操作人
	 * @return
	 */
	public Long getRoleOperator() {
		return roleOperator;
	}

	/**
	 * 设置操作人
	 * @param roleOperator
	 */
	public void setRoleOperator(Long roleOperator) {
		this.roleOperator = roleOperator;
	}

	/**
	 * 获取系统ID
	 */
	public Integer getSysId() {
		return sysId;
	}

	/**
	 * 设置系统ID
	 */
	public void setSysId(Integer sysId) {
		this.sysId = sysId;
	}
	
	/**
	 * 读取主键角色id
	 */
	public Long getRoleId(){
		return roleId;
	} 
	
	/**
	 * 设置 主键角色id
	 */
	public void setRoleId(Long roleId){
		this.roleId = roleId;
	}
	
	/**
	 * 读取公司ID
	 */
	public Long getCorpId(){
		return corpId;
	} 
	
	/**
	 * 设置 公司ID
	 */
	public void setCorpId(Long corpId){
		this.corpId = corpId;
	}
	
	/**
	 * 读取角色分类：字典 ROLE_FUN_TYPE
	 */
	public Integer getRoleCate(){
		return roleCate;
	} 
	
	/**
	 * 设置 角色分类：字典 ROLE_FUN_TYPE
	 */
	public void setRoleCate(Integer roleCate){
		this.roleCate = roleCate;
	}
	
	/**
	 * 读取角色名称
	 */
	public String getRoleName(){
		return roleName;
	} 
	
	/**
	 * 设置 角色名称
	 */
	public void setRoleName(String roleName){
		this.roleName = roleName;
	}
	
	/**
	 * 读取角色编码
	 */
	public String getRoleCode(){
		return roleCode;
	} 
	
	/**
	 * 设置 角色编码
	 */
	public void setRoleCode(String roleCode){
		this.roleCode = roleCode;
	}
	
	/**
	 * 读取角色类型（产品专员、产品部经理、销售顾问、销售部经理、业务代表、业务部经理
）
	 */
	public Integer getRoleType(){
		return roleType;
	} 
	
	/**
	 * 设置 角色类型（产品专员、产品部经理、销售顾问、销售部经理、业务代表、业务部经理
）
	 */
	public void setRoleType(Integer roleType){
		this.roleType = roleType;
	}
	
	/**
	 * 读取创建时间
	 */
	public Date getBuildTime(){
		return buildTime;
	} 
	
	/**
	 * 设置 创建时间
	 */
	public void setBuildTime(Date buildTime){
		this.buildTime = buildTime;
	}
	
	/**
	 * 读取有效状态
	 */
	public Integer getUserStatus(){
		return userStatus;
	} 
	
	/**
	 * 设置 有效状态
	 */
	public void setUserStatus(Integer userStatus){
		this.userStatus = userStatus;
	}
	
	/**
	 * 读取操作时间
	 */
	public Date getOperTime(){
		return operTime;
	} 
	
	/**
	 * 设置 操作时间
	 */
	public void setOperTime(Date operTime){
		this.operTime = operTime;
	}
	
	/**
	 * 读取ROLE_STATUS
	 */
	public Integer getRoleStatus(){
		return roleStatus;
	} 
	
	/**
	 * 设置 ROLE_STATUS
	 */
	public void setRoleStatus(Integer roleStatus){
		this.roleStatus = roleStatus;
	}
	
	
}
