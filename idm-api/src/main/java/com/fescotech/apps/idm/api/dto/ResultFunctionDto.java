package com.fescotech.apps.idm.api.dto;

public class ResultFunctionDto {

	/**
	 * 功能ID
	 */
	private Long functionId; 
	
	/**
	 * 功能名称
	 */
	private String functionName; 
	
	/**
	 * 功能对应URL
	 */
	private String url; 
	
	/**
	 * 功能类型(功能、菜单、模块)
	 */
	private Integer functionType; 
	
	/**
	 * 状态(是否有效)
	 */
	private Integer funState; 
	
	/**
	 * 权限标识
	 */
	private String perm;

	public Long getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Long functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getFunctionType() {
		return functionType;
	}

	public void setFunctionType(Integer functionType) {
		this.functionType = functionType;
	}

	public Integer getFunState() {
		return funState;
	}

	public void setFunState(Integer funState) {
		this.funState = funState;
	}

	public String getPerm() {
		return perm;
	}

	public void setPerm(String perm) {
		this.perm = perm;
	} 
	
	
}
