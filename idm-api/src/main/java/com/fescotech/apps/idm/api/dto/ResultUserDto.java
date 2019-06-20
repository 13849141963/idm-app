package com.fescotech.apps.idm.api.dto;

import java.util.List;

public class ResultUserDto {

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
	 * 性别
	 */
	private Integer sex; 
	
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
	 * 所属组织的Id和名称
	 */
	private Long orgId; 
	private String orgName; 
	
	/**
	 * 邮政编码
	 */
	private String postCode;

	/**
	 * 公司id
	 */
	private Long cropId;

	/**
	 * 用户类型(1用户2公司管理3超级管理)
	 */
	private Integer userType;
	
	private List<String> perms;

	public List<String> getPerms() {
		return perms;
	}

	public void setPerms(List<String> perms) {
		this.perms = perms;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Long getCropId() {
		return cropId;
	}

	public void setCropId(Long cropId) {
		this.cropId = cropId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	} 
	
	
}
