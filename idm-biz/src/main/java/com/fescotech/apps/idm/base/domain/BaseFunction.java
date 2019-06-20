package com.fescotech.apps.idm.base.domain;

import java.util.Date;

/**
 * 统一用户管理功能授权实体类
 * @author:lzl
 * @time:2017年6月19日 下午6:42:16
 */
public class BaseFunction {

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
	 * 父级功能ID
	 */
	private Long parentId; 
	
	/**
	 * 描述
	 */
	private String remark; 
	
	/**
	 * 排序
	 */
	private Long showOrder; 
	
	/**
	 * 层级
	 */
	private Long funcLevel; 
	
	/**
	 * 是否为叶子节点
	 */
	private Integer isLeaf; 
	
	/**
	 * 功能类型(功能、菜单、模块)
	 */
	private Integer functionType; 
	
	/**
	 * 状态(是否有效)
	 */
	private Integer funState; 
	
	/**
	 * 操作人
	 */
	private Long operator; 
	
	/**
	 * 操作时间
	 */
	private Date operTime; 
	
	/**
	 * 公司ID
	 */
	private Long corpId; 
	
	/**
	 * 系统ID
	 */
	private Integer sysId; 
	
	/**
	 * 权限标识
	 */
	private String perm; 
	
	/**
	 * 创建人id
	 */
	private Long creator;
	
	/**
	 * 创建人名称
	 */
	private String creatorName;
	
	/**
	 * 创建人机构
	 */
	private Long creatorOrg;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 读取创建时间
	 * @return
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 * @param creatTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 读取创建人id
	 * @return
	 */
	public Long getCreator() {
		return creator;
	}

	/**
	 * 设置创建人id
	 * @param creator
	 */
	public void setCreator(Long creator) {
		this.creator = creator;
	}

	/**
	 * 读取创建人名称
	 * @return
	 */
	public String getCreatorName() {
		return creatorName;
	}

	/**
	 * 设置创建人名称
	 * @param creatorName
	 */
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	/**
	 * 读取创建人机构
	 * @return
	 */
	public Long getCreatorOrg() {
		return creatorOrg;
	}

	/**
	 * 设置创建人机构
	 * @param creatorOrg
	 */
	public void setCreatorOrg(Long creatorOrg) {
		this.creatorOrg = creatorOrg;
	}

	/**
	 * 读取功能ID
	 */
	public Long getFunctionId(){
		return functionId;
	} 
	
	/**
	 * 设置 功能ID
	 */
	public void setFunctionId(Long functionId){
		this.functionId = functionId;
	}
	
	/**
	 * 读取功能名称
	 */
	public String getFunctionName(){
		return functionName;
	} 
	
	/**
	 * 设置 功能名称
	 */
	public void setFunctionName(String functionName){
		this.functionName = functionName;
	}
	
	/**
	 * 读取功能对应URL
	 */
	public String getUrl(){
		return url;
	} 
	
	/**
	 * 设置 功能对应URL
	 */
	public void setUrl(String url){
		this.url = url;
	}
	
	/**
	 * 读取父级功能ID
	 */
	public Long getParentId(){
		return parentId;
	} 
	
	/**
	 * 设置 父级功能ID
	 */
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}
	
	/**
	 * 读取描述
	 */
	public String getRemark(){
		return remark;
	} 
	
	/**
	 * 设置 描述
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	/**
	 * 读取排序
	 */
	public Long getShowOrder(){
		return showOrder;
	} 
	
	/**
	 * 设置 排序
	 */
	public void setShowOrder(Long showOrder){
		this.showOrder = showOrder;
	}
	
	/**
	 * 读取层级
	 */
	public Long getFuncLevel(){
		return funcLevel;
	} 
	
	/**
	 * 设置 层级
	 */
	public void setFuncLevel(Long funcLevel){
		this.funcLevel = funcLevel;
	}
	
	/**
	 * 读取是否为叶子节点
	 */
	public Integer getIsLeaf(){
		return isLeaf;
	} 
	
	/**
	 * 设置 是否为叶子节点
	 */
	public void setIsLeaf(Integer isLeaf){
		this.isLeaf = isLeaf;
	}
	
	/**
	 * 读取功能类型(功能、菜单、模块)
	 */
	public Integer getFunctionType(){
		return functionType;
	} 
	
	/**
	 * 设置 功能类型(功能、菜单、模块)
	 */
	public void setFunctionType(Integer functionType){
		this.functionType = functionType;
	}
	
	/**
	 * 读取状态(是否有效)
	 */
	public Integer getFunState(){
		return funState;
	} 
	
	/**
	 * 设置 状态(是否有效)
	 */
	public void setFunState(Integer funState){
		this.funState = funState;
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
	 * 读取公司ID
	 */
	public Long getCorpId(){
		return corpId;
	} 
	
	/**
	 * 设置 公司ID
	 */
	public void setCorpId(Long corpId){
		this.corpId = corpId;
	}
	
	/**
	 * 读取系统ID
	 */
	public Integer getSysId(){
		return sysId;
	} 
	
	/**
	 * 设置 系统ID
	 */
	public void setSysId(Integer sysId){
		this.sysId = sysId;
	}
	
	/**
	 * 读取权限标识
	 */
	public String getPerm(){
		return perm;
	} 
	
	/**
	 * 设置 权限标识
	 */
	public void setPerm(String perm){
		this.perm = perm;
	}
	
	
}
