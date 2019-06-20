package com.fescotech.apps.idm.base.domain.vo;

import java.util.List;

import com.fescotech.apps.idm.base.domain.BaseFunction;
import com.fescotech.apps.idm.base.domain.BaseFunctionType;

public class BaseFunctionVo extends BaseFunction{

	/**
	 * ztree属性
	 */
	private Boolean open;
	
	private List<?> list;
	
	private String sysName;
	
	private String parentName;
	
	private String functionTypeName;
	
	private List<BaseFunctionType> baseFunctionTypeList;

	public List<BaseFunctionType> getBaseFunctionTypeList() {
		return baseFunctionTypeList;
	}

	public void setBaseFunctionTypeList(List<BaseFunctionType> baseFunctionTypeList) {
		this.baseFunctionTypeList = baseFunctionTypeList;
	}

	public String getFunctionTypeName() {
		return functionTypeName;
	}

	public void setFunctionTypeName(String functionTypeName) {
		this.functionTypeName = functionTypeName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
	
	public String getName(){
		return super.getFunctionName();
	}

	@Override
	public String toString() {
		return "BaseFunctionVo{" +
				"open=" + open +
				", list=" + list +
				", sysName='" + sysName + '\'' +
				", parentName='" + parentName + '\'' +
				", functionTypeName='" + functionTypeName + '\'' +
				", baseFunctionTypeList=" + baseFunctionTypeList +
				'}';
	}
}
