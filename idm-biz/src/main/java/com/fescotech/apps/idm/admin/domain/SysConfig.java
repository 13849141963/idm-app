package com.fescotech.apps.idm.admin.domain;
/**
 * 系统配置
 */
public class SysConfig {

	/**
	 * 
	 */
	private Long confId; 
	
	/**
	 * 
	 */
	private String remark; 
	
	/**
	 * 
	 */
	private Integer confStatus; 
	
	/**
	 * 
	 */
	private String confValue; 
	
	/**
	 * 
	 */
	private String confKey;

	public Long getConfId() {
		return confId;
	}

	public void setConfId(Long confId) {
		this.confId = confId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getConfStatus() {
		return confStatus;
	}

	public void setConfStatus(Integer confStatus) {
		this.confStatus = confStatus;
	}

	public String getConfValue() {
		return confValue;
	}

	public void setConfValue(String confValue) {
		this.confValue = confValue;
	}

	public String getConfKey() {
		return confKey;
	}

	public void setConfKey(String confKey) {
		this.confKey = confKey;
	} 
}
