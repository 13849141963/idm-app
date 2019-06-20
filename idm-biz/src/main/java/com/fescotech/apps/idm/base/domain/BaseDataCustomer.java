package com.fescotech.apps.idm.base.domain;


/**
 * 
 */
public class BaseDataCustomer {

	/**
	 * 数据集权限与客户关联id
	 */
	private Long dataCustomerId; 
	
	/**
	 * 客户id
	 */
	private Long customerId; 
	
	/**
	 * 数据集权限id
	 */
	private Long baseId; 
	
	/**
	 * 客户名称
	 */
	private String customerName; 
	
	/**
	 * 客户父id
	 */
	private Long customerParentId; 
	
	
	/**
	 * 读取数据集权限与客户关联id
	 */
	public Long getDataCustomerId(){
		return dataCustomerId;
	} 
	
	/**
	 * 设置 数据集权限与客户关联id
	 */
	public void setDataCustomerId(Long dataCustomerId){
		this.dataCustomerId = dataCustomerId;
	}
	
	/**
	 * 读取客户id
	 */
	public Long getCustomerId(){
		return customerId;
	} 
	
	/**
	 * 设置 客户id
	 */
	public void setCustomerId(Long customerId){
		this.customerId = customerId;
	}
	
	/**
	 * 读取数据集权限id
	 */
	public Long getBaseId(){
		return baseId;
	} 
	
	/**
	 * 设置 数据集权限id
	 */
	public void setBaseId(Long baseId){
		this.baseId = baseId;
	}
	
	/**
	 * 读取客户名称
	 */
	public String getCustomerName(){
		return customerName;
	} 
	
	/**
	 * 设置 客户名称
	 */
	public void setCustomerName(String customerName){
		this.customerName = customerName;
	}
	
	/**
	 * 读取客户父id
	 */
	public Long getCustomerParentId(){
		return customerParentId;
	} 
	
	/**
	 * 设置 客户父id
	 */
	public void setCustomerParentId(Long customerParentId){
		this.customerParentId = customerParentId;
	}
	
	
}
