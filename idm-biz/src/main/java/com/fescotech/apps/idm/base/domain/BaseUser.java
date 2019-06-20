package com.fescotech.apps.idm.base.domain;

import java.util.Date;

/**
 * 
 */
public class BaseUser {

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
	
	/**
	 * 密码有效截止日期
	 */
	private Date expireTime; 
	
	/**
	 * 地址
	 */
	private String address; 
	
	/**
	 * 电话(加区号，分机号)
	 */
	private String phone; 
	
	/**
	 * EMAIL
	 */
	private String email; 
	
	/**
	 * 用户密码
	 */
	private String userPwd; 
	
	/**
	 * 性别
	 */
	private Integer sex; 
	
	/**
	 * 用户描述
	 */
	private String remark; 
	
	/**
	 * 状态
	 */
	private Integer userStatus; 
	
	/**
	 * 员工内部编号
	 */
	private String userNumber; 
	
	/**
	 * 手机号
	 */
	private String mobile; 
	
	/**
	 * 公司id
	 */
	private Long corpId; 
	
	/**
	 * 邮政编码
	 */
	private String postCode; 
	
	/**
	 * 操作时间
	 */
	private Date operTime; 
	
	/**
	 * 操作人
	 */
	private Long operator; 
	
	/**
	 * 用户类型(1用户2公司管理3超级管理)
	 */
	private Integer userType; 
	
	/**
	 * 用户所属机构ID，行政意义，目前没有在系统中做业务控制
	 */
	private Long orgId; 
	
	/*
	 * 用户的操作人姓名
	 * */
	private String operatorName;
	
	/**
	 * 创建时间
	 */
	private Date createTime; 

	//创建人（主键）
	private Long creator;
	
	//创建人姓名
	private String creatorName;
	
	//创建人所在机构
	private Long creatorOrg;
	
	//操作人所在机构
	private Long operatorOrg;
	
	
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
	 * 读取登录账号
	 */
	public String getLoginName(){
		return loginName;
	} 
	
	/**
	 * 设置 登录账号
	 */
	public void setLoginName(String loginName){
		this.loginName = loginName;
	}
	
	/**
	 * 读取用户名称
	 */
	public String getUserName(){
		return userName;
	} 
	
	/**
	 * 设置 用户名称
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	/**
	 * 读取密码有效截止日期
	 */
	public Date getExpireTime() {
		return expireTime;
	}
	
	/**
	 * 设置 密码有效截止日期
	 */
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	
	

	/**
	 * 读取地址
	 */
	public String getAddress(){
		return address;
	} 
	

	/**
	 * 设置 地址
	 */
	public void setAddress(String address){
		this.address = address;
	}
	
	/**
	 * 读取电话(加区号，分机号)
	 */
	public String getPhone(){
		return phone;
	} 
	
	/**
	 * 设置 电话(加区号，分机号)
	 */
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	/**
	 * 读取EMAIL
	 */
	public String getEmail(){
		return email;
	} 
	
	/**
	 * 设置 EMAIL
	 */
	public void setEmail(String email){
		this.email = email;
	}
	
	/**
	 * 读取用户密码
	 */
	public String getUserPwd(){
		return userPwd;
	} 
	
	/**
	 * 设置 用户密码
	 */
	public void setUserPwd(String userPwd){
		this.userPwd = userPwd;
	}
	
	/**
	 * 读取性别
	 */
	public Integer getSex(){
		return sex;
	} 
	
	/**
	 * 设置 性别
	 */
	public void setSex(Integer sex){
		this.sex = sex;
	}
	
	
	
	/**
	 * 读取状态
	 */
	public Integer getUserStatus(){
		return userStatus;
	} 
	
	/**
	 * 设置 状态
	 */
	public void setUserStatus(Integer userStatus){
		this.userStatus = userStatus;
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
	 * 读取员工内部编号
	 */
	public String getUserNumber(){
		return userNumber;
	} 
	
	/**
	 * 设置 员工内部编号
	 */
	public void setUserNumber(String userNumber){
		this.userNumber = userNumber;
	}
	
	
	
	/**
	 * 读取手机号
	 */
	public String getMobile(){
		return mobile;
	} 
	
	/**
	 * 设置 手机号
	 */
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	/**
	 * 读取公司id
	 */
	public Long getCorpId(){
		return corpId;
	} 
	
	/**
	 * 设置 公司id
	 */
	public void setCorpId(Long corpId){
		this.corpId = corpId;
	}
	
	/**
	 * 读取邮政编码
	 */
	public String getPostCode(){
		return postCode;
	} 
	
	/**
	 * 设置 邮政编码
	 */
	public void setPostCode(String postCode){
		this.postCode = postCode;
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
	 * 读取操作人
	 */
	public Long getOperator(){
		return operator;
	} 
	
	/**
	 * 设置 操作人
	 */
	public void setOperator(Long operator){
		this.operator = operator;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Long getCreatorOrg() {
		return creatorOrg;
	}

	public void setCreatorOrg(Long creatorOrg) {
		this.creatorOrg = creatorOrg;
	}

	public Long getOperatorOrg() {
		return operatorOrg;
	}

	public void setOperatorOrg(Long operatorOrg) {
		this.operatorOrg = operatorOrg;
	}
	
	
	
	
}
