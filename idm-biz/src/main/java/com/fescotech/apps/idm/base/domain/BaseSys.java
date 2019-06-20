package com.fescotech.apps.idm.base.domain;

import java.util.Date;

public class BaseSys {

	private Long sysId;//主键
	private String alias;//应用别名
	private String sysName;//应用名字
	private String sysUrl;//应用地址
	private String remark;//描述
	private Integer openFlag;//开启与否的标记
	private Long operator;//操作员(主键?
	private String operatorName;//操作员
	private Date operTime;//操作时间
	/**
	 * 创建人id
	 */
	private Long creator;
	
	/**
	 * 创建人名称
	 */
	private String creatorName;
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
	public Long getSysId() {
		return sysId;
	}
	public void setSysId(Long sysId) {
		this.sysId = sysId;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public String getSysUrl() {
		return sysUrl;
	}
	public void setSysUrl(String sysUrl) {
		this.sysUrl = sysUrl;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getOpenFlag() {
		return openFlag;
	}
	public void setOpenFlag(Integer openFlag) {
		this.openFlag = openFlag;
	}
	public Long getOperator() {
		return operator;
	}
	public void setOperator(Long operator) {
		this.operator = operator;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Date getOperTime() {
		return operTime;
	}
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	
	
	
}
