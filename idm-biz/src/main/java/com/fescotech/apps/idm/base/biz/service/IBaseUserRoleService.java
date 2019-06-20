package com.fescotech.apps.idm.base.biz.service;


import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.domain.BaseUserRole;

public interface IBaseUserRoleService {


	BaseUserRole getUserRole(BaseUserRole userRole);

	void saveUserRole(BaseUserRole userRole);

	void updateUserRole(BaseUserRole userRole);
	
	List<BaseUserRole> getUserRoleList(BaseUserRole userRole);

	List<BaseUserRole> getUserRoleList(Map<String, Object> map);

	int queryTotal(BaseUserRole bur);

	void deleteUserRole(Long[] baseUserRoleIds);

	List<BaseUserRole> getUserRoleListByUr(BaseUserRole bur);
	
	public List<BaseUserRole> queryUserRoleListByRoleId(Long roleId);
	
	public List<BaseUserRole> queryUserRoleListByUserId(Long userId);

}
