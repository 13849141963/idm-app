package com.fescotech.apps.idm.base.domain;

/**
 * 角色所属系统实体类
 * @author:lzl
 * @time:2017年6月20日 下午2:27:43
 */
public class BaseRoleSys {

	/**
	 * 系统ID
	 */
	private Integer roleSysId;
	/**
	 * 系统名称
	 */
	private String roleSysName;
	
	/**
	 * 获取系统id
	 * @return
	 */
	public Integer getRoleSysId() {
		return roleSysId;
	}
	
	/**
	 * 设置系统id
	 * @param roleSysId
	 */
	public void setRoleSysId(Integer roleSysId) {
		this.roleSysId = roleSysId;
	}
	
	/**
	 * 获取系统名称
	 * @return
	 */
	public String getRoleSysName() {
		return roleSysName;
	}
	
	/**
	 * 设置系统名称
	 * @param roleSysName
	 */
	public void setRoleSysName(String roleSysName) {
		this.roleSysName = roleSysName;
	}
	
}
