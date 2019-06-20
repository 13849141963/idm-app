package com.fescotech.idm.client.model;
public class IdmUserDto {
	/**
	 * 新密码（更新时候用）
	 */
	private String newPassword;
	private String idmKey;
	private String timeStamp;
	private String sign;
	/**
	 * 登录账号
	 */
	private String loginName;
	private String userPwd;
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getIdmKey() {
		return idmKey;
	}
	public void setIdmKey(String idmKey) {
		this.idmKey = idmKey;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	 
}
