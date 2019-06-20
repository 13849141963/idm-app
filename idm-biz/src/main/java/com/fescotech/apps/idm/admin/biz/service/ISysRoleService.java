package com.fescotech.apps.idm.admin.biz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.admin.domain.SysRole;
import com.fescotech.apps.idm.admin.domain.vo.SysRoleVo;

/**
 * 角色
 */
public interface ISysRoleService {
	SysRoleVo  queryObject(Long roleId);
	
	List<SysRole> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysRoleVo role);
	
	void update(SysRoleVo role);
	
	void deleteBatch(Long[] roleIds);
 
	public List<Long> queryMenuIdList(Long roleId) ;


	List<SysRole> queryAllList();
}
