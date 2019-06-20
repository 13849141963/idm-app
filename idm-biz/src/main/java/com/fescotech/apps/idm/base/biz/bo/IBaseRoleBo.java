package com.fescotech.apps.idm.base.biz.bo;
import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.domain.BaseRole;
import com.fescotech.apps.idm.base.domain.vo.BaseRoleVo;
public interface IBaseRoleBo {
	public void insert(BaseRole baseRole);
	
	public void update(BaseRole baseRole);
	
	public void deleteBatch(Long[] roleIds);
	
	public List<BaseRole> queryBaseRole(BaseRole baseRole);

	public BaseRoleVo loadBaseRole(Long roleId);
	
	public int queryBaseRoleCount(BaseRole baseRole);
	
	public List<BaseRoleVo> queryList(Map<String, Object> map);

	BaseRole findBaseRole(BaseRole baseRole);
}