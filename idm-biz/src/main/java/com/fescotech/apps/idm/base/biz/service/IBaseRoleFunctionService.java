package com.fescotech.apps.idm.base.biz.service;

import java.util.List;

import com.fescotech.apps.idm.base.domain.BaseRoleFunction;

/**
 * 角色权限映射service
 * @author:lzl
 * @time:2017年6月28日 上午11:09:48
 */
public interface IBaseRoleFunctionService {

	/**
	 * 根据角色id获取已授权信息
	 * @param roleId
	 * @return
	 */
	public List<Long> queryFuncIdList(Long roleId);
	
	/**
	 * 批量删除角色功能中间表
	 * @param roleIds
	 */
	public void deleteRoleFunction(Long[] roleIds);
	
	/**
	 * 删除权限功能
	 * @param roleId
	 */
	public void deleteRoleFunction(Long roleId);
	
	/**
	 * 新增权限功能
	 * @param baseRoleFunction
	 */
	public void insertRoleFunction(BaseRoleFunction baseRoleFunction);
	
}
