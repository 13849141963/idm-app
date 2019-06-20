package com.fescotech.apps.idm.base.biz.bo.impl;
import java.util.List;

import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.base.biz.bo.IBaseRoleFunctionBo;
import com.fescotech.apps.idm.base.domain.BaseRoleFunction;

public class BaseRoleFunctionBo extends AbstractBoT implements IBaseRoleFunctionBo{

	public void insert(BaseRoleFunction baseRoleFunction){
		baseDao.insert("baseRoleFunctionSql.insertBaseRoleFunction",baseRoleFunction);
	}
	
	public void update(BaseRoleFunction baseRoleFunction){
		baseDao.update("baseRoleFunctionSql.updateBaseRoleFunction",baseRoleFunction);
	}
	
	public List<BaseRoleFunction> queryBaseRoleFunction(BaseRoleFunction baseRoleFunction){
		return baseDao.queryForList("baseRoleFunctionSql.queryBaseRoleFunction",baseRoleFunction,BaseRoleFunction.class);
	}
	
	public BaseRoleFunction loadBaseRoleFunction(Long roleId){
		return (BaseRoleFunction)baseDao.queryForObject("baseRoleFunctionSql.queryByKey",roleId);
	}
	public int queryBaseRoleFunctionCount(BaseRoleFunction baseRoleFunction){
		return (int)baseDao.queryForObject("baseRoleFunctionSql.queryBaseRoleFunctionCount",baseRoleFunction);
	}

	@Override
	public List<Long> queryFuncIdList(Long roleId) {
		return baseDao.queryForList("baseRoleFunctionSql.queryFuncIdList", roleId,Long.class);
	}

	@Override
	public void deleteRoleFunction(Long roleId) {
		baseDao.delete("baseRoleFunctionSql.deleteRoleFunction", roleId);
	}

	@Override
	public void insertRoleEubction(BaseRoleFunction baseRoleFunction) {
		baseDao.insert("baseRoleFunctionSql.insertRoleFunction", baseRoleFunction);
	}

	@Override
	public void deleteBatch(Long[] roleIds) {
		baseDao.delete("baseRoleFunctionSql.deleteBatch", roleIds);
	}
}