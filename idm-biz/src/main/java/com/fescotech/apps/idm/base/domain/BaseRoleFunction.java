package com.fescotech.apps.idm.base.domain;


/**
 * 角色与功能关联实体类
 * @author:lzl
 * @time:2017年6月19日 下午6:43:14
 */
public class BaseRoleFunction {

	/**
	 * 主键角色id
	 */
	private Long roleId; 
	
	/**
	 * 主键菜单id
	 */
	private Long functionId; 
	
	/**
	 * 查询范围(1：负责的业务数据 2：部门下的业务数据 3：全部数据)(字典码:QUERY_RANGE_TYPE)
	 */
	private Integer queryRange; 
	
	/**
	 * 查询指定公司数据
	 */
	private String queryCorp; 
	
	/**
	 * 主键id
	 */
	private Long roleFunctionId; 
	
	
	/**
	 * 读取主键角色id
	 */
	public Long getRoleId(){
		return roleId;
	} 
	
	/**
	 * 设置 主键角色id
	 */
	public void setRoleId(Long roleId){
		this.roleId = roleId;
	}
	
	/**
	 * 读取主键菜单id
	 */
	public Long getFunctionId(){
		return functionId;
	} 
	
	/**
	 * 设置 主键菜单id
	 */
	public void setFunctionId(Long functionId){
		this.functionId = functionId;
	}
	
	/**
	 * 读取查询范围(1：负责的业务数据 2：部门下的业务数据 3：全部数据)(字典码:QUERY_RANGE_TYPE)
	 */
	public Integer getQueryRange(){
		return queryRange;
	} 
	
	/**
	 * 设置 查询范围(1：负责的业务数据 2：部门下的业务数据 3：全部数据)(字典码:QUERY_RANGE_TYPE)
	 */
	public void setQueryRange(Integer queryRange){
		this.queryRange = queryRange;
	}
	
	/**
	 * 读取查询指定公司数据
	 */
	public String getQueryCorp(){
		return queryCorp;
	} 
	
	/**
	 * 设置 查询指定公司数据
	 */
	public void setQueryCorp(String queryCorp){
		this.queryCorp = queryCorp;
	}

	/**
	 * 读取主键id
	 */
	public Long getRoleFunctionId() {
		return roleFunctionId;
	}

	/**
	 * 设置主键id
	 * @param roleFunctionId
	 */
	public void setRoleFunctionId(Long roleFunctionId) {
		this.roleFunctionId = roleFunctionId;
	}
	
}
