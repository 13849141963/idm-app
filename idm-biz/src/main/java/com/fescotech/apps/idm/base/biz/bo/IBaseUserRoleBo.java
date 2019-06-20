package com.fescotech.apps.idm.base.biz.bo;
import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.domain.BaseUserRole;
public interface IBaseUserRoleBo {
	public void insert(BaseUserRole baseUserRole);
	
	public void update(BaseUserRole baseUserRole);
	
	public List<BaseUserRole> queryBaseUserRole(BaseUserRole baseUserRole);

	public BaseUserRole loadBaseUserRole(Long baseUserRoleId);
	
	public int queryBaseUserRoleCount(BaseUserRole baseUserRole);

	BaseUserRole loadBaseUserRole(BaseUserRole baseUserRole);

	public List<BaseUserRole> getUserRoleList(Map<String, Object> map);

	void deleteBatch(Long[] baseUserRoleIds);

	List<BaseUserRole> getUserRoleListByUr(BaseUserRole baseUserRole);
	
	public List<BaseUserRole> queryUserRoleListByRoleId(Long roleId);
	
	public List<BaseUserRole> queryUserRoleListByUserId(Long userId);
}