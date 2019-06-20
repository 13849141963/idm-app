package com.fescotech.apps.idm.base.biz.service.impl;


import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.biz.bo.IBaseUserRoleBo;
import com.fescotech.apps.idm.base.biz.service.IBaseUserRoleService;
import com.fescotech.apps.idm.base.domain.BaseUserRole;

public class BaseUserRoleServiceImpl  implements IBaseUserRoleService{

	private IBaseUserRoleBo baseUserRoleBo;
	public void setBaseUserRoleBo(IBaseUserRoleBo baseUserRoleBo) {
		this.baseUserRoleBo = baseUserRoleBo;
	}


	@Override
	public BaseUserRole getUserRole(BaseUserRole userRole) {
		return baseUserRoleBo.loadBaseUserRole(userRole);
	}


	@Override
	public void saveUserRole(BaseUserRole userRole) {
		baseUserRoleBo.insert(userRole);
		
	}


	@Override
	public void updateUserRole(BaseUserRole userRole) {
		baseUserRoleBo.update(userRole);
	}



	@Override
	public List<BaseUserRole> getUserRoleList(Map<String, Object> map) {
		return baseUserRoleBo.getUserRoleList(map);
	}


	@Override
	public int queryTotal(BaseUserRole bur) {
		// TODO Auto-generated method stub
		return baseUserRoleBo.queryBaseUserRoleCount(bur);
	}


	@Override
	public void deleteUserRole(Long[] baseUserRoleIds) {
		baseUserRoleBo.deleteBatch(baseUserRoleIds);
		
	}


	@Override
	public List<BaseUserRole> getUserRoleListByUr(BaseUserRole bur) {
		return baseUserRoleBo.getUserRoleListByUr(bur);
	}


	@Override
	public List<BaseUserRole> getUserRoleList(BaseUserRole userRole) {
		return baseUserRoleBo.queryBaseUserRole(userRole);
	}


	@Override
	public List<BaseUserRole> queryUserRoleListByRoleId(Long roleId) {
		return baseUserRoleBo.queryUserRoleListByRoleId(roleId);
	}


	@Override
	public List<BaseUserRole> queryUserRoleListByUserId(Long userId) {
		return baseUserRoleBo.queryUserRoleListByUserId(userId);
	}


}
