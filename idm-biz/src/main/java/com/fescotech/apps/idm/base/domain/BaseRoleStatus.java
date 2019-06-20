package com.fescotech.apps.idm.base.domain;

/**
 * 角色状态实体类
 * @author:lzl
 * @time:2017年6月19日 下午2:02:43
 */
public class BaseRoleStatus {

	/**
	 * 角色状态编码（对应字典表BASE_DICT）
	 */
	private String roleStatueCode;
	/**
	 * 角色状态名称（对应字典表BASE_DICT）
	 */
	private String roleStatueName;
	
	/**
	 * 获取角色状态编码
	 * @return
	 */
	public String getRoleStatueCode() {
		return roleStatueCode;
	}
	
	/**
	 * 设置角色状态编码
	 * @param roleStatueCode
	 */
	public void setRoleStatueCode(String roleStatueCode) {
		this.roleStatueCode = roleStatueCode;
	}
	
	/**
	 * 获取角色状态名称
	 * @return
	 */
	public String getRoleStatueName() {
		return roleStatueName;
	}
	
	/**
	 * 设置角色状态名称
	 * @param roleStatueName
	 */
	public void setRoleStatueName(String roleStatueName) {
		this.roleStatueName = roleStatueName;
	} 
	
}
