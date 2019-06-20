package com.fescotech.apps.idm.base.biz.bo.impl;
import java.util.List;
import java.util.Map;

import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.base.biz.bo.IBaseUserRoleBo;
import com.fescotech.apps.idm.base.domain.BaseUserRole;

public class BaseUserRoleBo extends AbstractBoT implements IBaseUserRoleBo{

	public void insert(BaseUserRole baseUserRole){
		baseDao.insert("baseUserRoleSql.insertBaseUserRole",baseUserRole);
	}
	
	public void update(BaseUserRole baseUserRole){
		baseDao.update("baseUserRoleSql.updateBaseUserRole",baseUserRole);
	}
	
	@Override
	public void deleteBatch(Long[] baseUserRoleIds){
		baseDao.update("baseUserRoleSql.deleteBatch",baseUserRoleIds);
	}
	
	public List<BaseUserRole> queryBaseUserRole(BaseUserRole baseUserRole){
		return baseDao.queryForList("baseUserRoleSql.queryBaseUserRole",baseUserRole,BaseUserRole.class);
	}
	@Override
	public BaseUserRole loadBaseUserRole(BaseUserRole baseUserRole){
		return (BaseUserRole) baseDao.queryForObject("baseUserRoleSql.queryBaseUserRole",baseUserRole);
	}
	
	public BaseUserRole loadBaseUserRole(Long baseUserRoleId){
		return (BaseUserRole)baseDao.queryForObject("baseUserRoleSql.queryByKey",baseUserRoleId);
	}
	public int queryBaseUserRoleCount(BaseUserRole baseUserRole){
		return (int)baseDao.queryForObject("baseUserRoleSql.queryBaseUserRoleCount",baseUserRole);
	}

	@Override
	public List<BaseUserRole> getUserRoleList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return baseDao.queryForList("baseUserRoleSql.queryBaseUserRoleList",map,BaseUserRole.class);
	}
	
	@Override
	public List<BaseUserRole> getUserRoleListByUr(BaseUserRole baseUserRole) {
		// TODO Auto-generated method stub
		return baseDao.queryForList("baseUserRoleSql.queryBaseUserRole",baseUserRole,BaseUserRole.class);
	}

	@Override
	public List<BaseUserRole> queryUserRoleListByRoleId(Long roleId) {
		return baseDao.queryForList("baseUserRoleSql.queryBaseUserRoleByRoleId",roleId,BaseUserRole.class);
	}

	@Override
	public List<BaseUserRole> queryUserRoleListByUserId(Long userId) {
		return baseDao.queryForList("baseUserRoleSql.queryBaseUserRoleByUserId",userId,BaseUserRole.class);
	}
}