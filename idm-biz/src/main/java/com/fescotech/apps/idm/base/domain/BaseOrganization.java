package com.fescotech.apps.idm.base.domain;

import java.util.Date;

/**
 * 
 */
public class BaseOrganization {

	/**
	 * 
	 */
	private String orgName; 
	
	/**
	 * 
	 */
	private String orgType; 
	
	/**
	 * 
	 */
	private Integer orgState; 
	
	/**
	 * 
	 */
	private String postcode; 
	
	/**
	 * 
	 */
	private Long orgLevel; 
	
	/**
	 * 
	 */
	private Long operator; 
	
	/**
	 * 
	 */
	private String shortName; 
	
	/**
	 * 
	 */
	private Integer chargeType; 
	
	/**
	 * 
	 */
	private Date operTime; 
	
	/**
	 * 
	 */
	private String phone; 
	
	/**
	 * 
	 */
	private Integer isVirtual; 
	
	/**
	 * 
	 */
	private Long corpId; 
	
	/**
	 * 
	 */
	private Integer isLeaf; 
	
	/**
	 * 
	 */
	private String fax; 
	
	/**
	 * 
	 */
	private String oldOrgNo; 
	
	/**
	 * 
	 */
	private String address; 
	
	/**
	 * 
	 */
	private Integer depType; 
	
	/**
	 * 
	 */
	private String tell; 
	
	/**
	 * 
	 */
	private Long orgId; 
	
	/**
	 * 
	 */
	private Long managerId; 
	
	/**
	 * 
	 */
	private Integer isRoot; 
	
	/**
	 * 
	 */
	private String description; 
	
	/**
	 * 
	 */
	private Long displayOrder; 
	
	/**
	 * 
	 */
	private String orgCode; 
	
	/**
	 * 
	 */
	private Long parentId; 
	
	private String parentName;
	
	private String companyName;
	
	/**
	 * 读取
	 */
	public String getOrgName(){
		return orgName;
	} 
	
	/**
	 * 设置 
	 */
	public void setOrgName(String orgName){
		this.orgName = orgName;
	}
	
	/**
	 * 读取
	 */
	public String getOrgType(){
		return orgType;
	} 
	
	/**
	 * 设置 
	 */
	public void setOrgType(String orgType){
		this.orgType = orgType;
	}
	
	/**
	 * 读取
	 */
	public Integer getOrgState(){
		return orgState;
	} 
	
	/**
	 * 设置 
	 */
	public void setOrgState(Integer orgState){
		this.orgState = orgState;
	}
	
	/**
	 * 读取
	 */
	public String getPostcode(){
		return postcode;
	} 
	
	/**
	 * 设置 
	 */
	public void setPostcode(String postcode){
		this.postcode = postcode;
	}
	
	/**
	 * 读取
	 */
	public Long getOrgLevel(){
		return orgLevel;
	} 
	
	/**
	 * 设置 
	 */
	public void setOrgLevel(Long orgLevel){
		this.orgLevel = orgLevel;
	}
	
	/**
	 * 读取
	 */
	public Long getOperator(){
		return operator;
	} 
	
	/**
	 * 设置 
	 */
	public void setOperator(Long operator){
		this.operator = operator;
	}
	
	/**
	 * 读取
	 */
	public String getShortName(){
		return shortName;
	} 
	
	/**
	 * 设置 
	 */
	public void setShortName(String shortName){
		this.shortName = shortName;
	}
	
	/**
	 * 读取
	 */
	public Integer getChargeType(){
		return chargeType;
	} 
	
	/**
	 * 设置 
	 */
	public void setChargeType(Integer chargeType){
		this.chargeType = chargeType;
	}
	
	/**
	 * 读取
	 */
	public Date getOperTime(){
		return operTime;
	} 
	
	/**
	 * 设置 
	 */
	public void setOperTime(Date operTime){
		this.operTime = operTime;
	}
	
	/**
	 * 读取
	 */
	public String getPhone(){
		return phone;
	} 
	
	/**
	 * 设置 
	 */
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	/**
	 * 读取
	 */
	public Integer getIsVirtual(){
		return isVirtual;
	} 
	
	/**
	 * 设置 
	 */
	public void setIsVirtual(Integer isVirtual){
		this.isVirtual = isVirtual;
	}
	
	/**
	 * 读取
	 */
	public Long getCorpId(){
		return corpId;
	} 
	
	/**
	 * 设置 
	 */
	public void setCorpId(Long corpId){
		this.corpId = corpId;
	}
	
	/**
	 * 读取
	 */
	public Integer getIsLeaf(){
		return isLeaf;
	} 
	
	/**
	 * 设置 
	 */
	public void setIsLeaf(Integer isLeaf){
		this.isLeaf = isLeaf;
	}
	
	/**
	 * 读取
	 */
	public String getFax(){
		return fax;
	} 
	
	/**
	 * 设置 
	 */
	public void setFax(String fax){
		this.fax = fax;
	}
	
	/**
	 * 读取
	 */
	public String getOldOrgNo(){
		return oldOrgNo;
	} 
	
	/**
	 * 设置 
	 */
	public void setOldOrgNo(String oldOrgNo){
		this.oldOrgNo = oldOrgNo;
	}
	
	/**
	 * 读取
	 */
	public String getAddress(){
		return address;
	} 
	
	/**
	 * 设置 
	 */
	public void setAddress(String address){
		this.address = address;
	}
	
	/**
	 * 读取
	 */
	public Integer getDepType(){
		return depType;
	} 
	
	/**
	 * 设置 
	 */
	public void setDepType(Integer depType){
		this.depType = depType;
	}
	
	/**
	 * 读取
	 */
	public String getTell(){
		return tell;
	} 
	
	/**
	 * 设置 
	 */
	public void setTell(String tell){
		this.tell = tell;
	}
	
	/**
	 * 读取
	 */
	public Long getOrgId(){
		return orgId;
	} 
	
	/**
	 * 设置 
	 */
	public void setOrgId(Long orgId){
		this.orgId = orgId;
	}
	
	/**
	 * 读取
	 */
	public Long getManagerId(){
		return managerId;
	} 
	
	/**
	 * 设置 
	 */
	public void setManagerId(Long managerId){
		this.managerId = managerId;
	}
	
	/**
	 * 读取
	 */
	public Integer getIsRoot(){
		return isRoot;
	} 
	
	/**
	 * 设置 
	 */
	public void setIsRoot(Integer isRoot){
		this.isRoot = isRoot;
	}
	
	/**
	 * 读取
	 */
	public String getDescription(){
		return description;
	} 
	
	/**
	 * 设置 
	 */
	public void setDescription(String description){
		this.description = description;
	}
	
	/**
	 * 读取
	 */
	public Long getDisplayOrder(){
		return displayOrder;
	} 
	
	/**
	 * 设置 
	 */
	public void setDisplayOrder(Long displayOrder){
		this.displayOrder = displayOrder;
	}
	
	/**
	 * 读取
	 */
	public String getOrgCode(){
		return orgCode;
	} 
	
	/**
	 * 设置 
	 */
	public void setOrgCode(String orgCode){
		this.orgCode = orgCode;
	}
	
	/**
	 * 读取
	 */
	public Long getParentId(){
		return parentId;
	} 
	
	/**
	 * 设置 
	 */
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}
