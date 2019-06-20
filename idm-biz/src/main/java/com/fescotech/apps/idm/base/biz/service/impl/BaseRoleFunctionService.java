package com.fescotech.apps.idm.base.biz.service.impl;

import java.util.List;

import com.fescotech.apps.idm.base.biz.bo.IBaseRoleFunctionBo;
import com.fescotech.apps.idm.base.biz.service.IBaseRoleFunctionService;
import com.fescotech.apps.idm.base.domain.BaseRoleFunction;

/**
 * 角色权限映射service
 * @author:lzl
 * @time:2017年6月28日 上午11:09:48
 */
public class BaseRoleFunctionService implements IBaseRoleFunctionService{
	
	/**
	 * 角色与功能管理Bo
	 */
	private IBaseRoleFunctionBo baseRoleFunctionBo;
	
	/**
	 * 设置角色与功能Bo
	 * @param baseRoleFunctionBo
	 */
	public void setBaseRoleFunctionBo(IBaseRoleFunctionBo baseRoleFunctionBo) {
		this.baseRoleFunctionBo = baseRoleFunctionBo;
	}

	/**
	 * 根据角色id获取已授权信息
	 * @param roleId
	 * @return
	 */
	@Override
	public List<Long> queryFuncIdList(Long roleId) {
		return baseRoleFunctionBo.queryFuncIdList(roleId);
	}

	/**
	 * 批量删除角色功能中间表
	 * @param roleIds
	 */
	@Override
	public void deleteRoleFunction(Long[] roleIds) {
		baseRoleFunctionBo.deleteBatch(roleIds);
	}

	/**
	 * 删除权限功能
	 * @param roleId
	 */
	@Override
	public void deleteRoleFunction(Long roleId) {
		baseRoleFunctionBo.deleteRoleFunction(roleId);
	}

	/**
	 * 新增权限功能
	 * @param baseRoleFunction
	 */
	@Override
	public void insertRoleFunction(BaseRoleFunction baseRoleFunction) {
		baseRoleFunctionBo.insertRoleEubction(baseRoleFunction);
	}

}
