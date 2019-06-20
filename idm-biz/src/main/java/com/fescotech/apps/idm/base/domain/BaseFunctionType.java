package com.fescotech.apps.idm.base.domain;

/**
 * 功能类型实体类
 * @author:lzl
 * @time:2017年6月23日 下午6:41:56
 */
public class BaseFunctionType {

	/**
	 * 功能类型编码（对应字典表BASE_DICT） 
	 */
	private String functionTypeCode;
	
	/**
	 * 功能类型名称（对应字典表BASE_DICT）
	 */
	private String functionTypeName;

	public String getFunctionTypeCode() {
		return functionTypeCode;
	}

	public void setFunctionTypeCode(String functionTypeCode) {
		this.functionTypeCode = functionTypeCode;
	}

	public String getFunctionTypeName() {
		return functionTypeName;
	}

	public void setFunctionTypeName(String functionTypeName) {
		this.functionTypeName = functionTypeName;
	}
	
}
