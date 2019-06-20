package com.fescotech.apps.idm.base.biz.bo;
import java.util.List;

import com.fescotech.apps.idm.base.domain.BaseRoleFunction;

public interface IBaseRoleFunctionBo {
	public void insert(BaseRoleFunction baseRoleFunction);
	
	public void update(BaseRoleFunction baseRoleFunction);
	
	public List<BaseRoleFunction> queryBaseRoleFunction(BaseRoleFunction baseRoleFunction);

	public BaseRoleFunction loadBaseRoleFunction(Long roleId);
	
	public int queryBaseRoleFunctionCount(BaseRoleFunction baseRoleFunction);
	
	public List<Long> queryFuncIdList(Long roleId);
	
	public void deleteRoleFunction(Long roleId);
	
	public void insertRoleEubction(BaseRoleFunction baseRoleFunction);
	
	public void deleteBatch(Long[] roleIds);
}