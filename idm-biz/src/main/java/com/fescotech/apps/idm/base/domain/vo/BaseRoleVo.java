package com.fescotech.apps.idm.base.domain.vo;

import java.util.List;

import com.fescotech.apps.idm.base.domain.BaseRole;
import com.fescotech.apps.idm.base.domain.BaseRoleStatus;
import com.fescotech.apps.idm.base.domain.BaseRoleSys;

/**
 * 角色属性扩展实体类
 * @author:lzl
 * @time:2017年6月19日 下午6:38:46
 */
public class BaseRoleVo extends BaseRole{

	/**
	 * 角色拥有权限id集合(sysId_functionId)
	 */
	private List<String> functionIdList;
	
	/**
	 * 角色状态描述（0开启，1关闭）
	 */
	private String roleStatusName;
	
	/**
	 * 角色所属系统名称
	 */
	private String roleSysName;
	
	/**
	 * 角色状态集合
	 */
	private List<BaseRoleStatus> baseRoleStatusList;
	
	/**
	 * 角色系统集合
	 */
	private List<BaseRoleSys> baseRoleSysList;

	/**
	 * 获取角色系统名称
	 * @return
	 */
	public String getRoleSysName() {
		return roleSysName;
	}

	/**
	 * 设置角色系统名称
	 * @param roleSysName
	 */
	public void setRoleSysName(String roleSysName) {
		this.roleSysName = roleSysName;
	}

	/**
	 * 获取角色系统集合
	 * @return
	 */
	public List<BaseRoleSys> getBaseRoleSysList() {
		return baseRoleSysList;
	}

	/**
	 * 设置角色系统集合
	 * @param baseRoleSysList
	 */
	public void setBaseRoleSysList(List<BaseRoleSys> baseRoleSysList) {
		this.baseRoleSysList = baseRoleSysList;
	}

	/**
	 * 获取角色状态集合
	 * @return
	 */
	public List<BaseRoleStatus> getBaseRoleStatusList() {
		return baseRoleStatusList;
	}

	/**
	 * 设置角色状态集合
	 * @param baseRoleStatusList
	 */
	public void setBaseRoleStatusList(List<BaseRoleStatus> baseRoleStatusList) {
		this.baseRoleStatusList = baseRoleStatusList;
	}

	/**
	 * 获取角色状态描述
	 * @return
	 */
	public String getRoleStatusName() {
		return roleStatusName;
	}

	/**
	 * 设置角色状态描述
	 * @param roleStatusName
	 */
	public void setRoleStatusName(String roleStatusName) {
		this.roleStatusName = roleStatusName;
	}

	/**
	 * 获取角色拥有权限id集合
	 * @return
	 */
	public List<String> getFunctionIdList() {
		return functionIdList;
	}

	/**
	 * 设置角色拥有权限id集合
	 * @param functionIdList
	 */
	public void setFunctionIdList(List<String> functionIdList) {
		this.functionIdList = functionIdList;
	}

}
