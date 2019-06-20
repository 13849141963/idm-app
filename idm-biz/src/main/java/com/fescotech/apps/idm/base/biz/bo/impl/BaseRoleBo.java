package com.fescotech.apps.idm.base.biz.bo.impl;
import java.util.List;
import java.util.Map;

import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.base.biz.bo.IBaseRoleBo;
import com.fescotech.apps.idm.base.domain.BaseRole;
import com.fescotech.apps.idm.base.domain.vo.BaseRoleVo;
public class BaseRoleBo extends AbstractBoT implements IBaseRoleBo{
	public void insert(BaseRole baseRole){
		baseDao.insert("baseRoleSql.insertBaseRole",baseRole);
	}
	
	public void update(BaseRole baseRole){
		baseDao.update("baseRoleSql.updateBaseRole",baseRole);
	}
	
	@Override
	public void deleteBatch(Long[] roleIds) {
		baseDao.delete("baseRoleSql.deleteBatch", roleIds);
	}
	
	public List<BaseRole> queryBaseRole(BaseRole baseRole){
		return baseDao.queryForList("baseRoleSql.queryBaseRole",baseRole,BaseRole.class);
	}
	@Override
	public BaseRole findBaseRole(BaseRole baseRole){
		return (BaseRole) baseDao.queryForObject("baseRoleSql.queryBaseRole",baseRole);
	}
	
	public BaseRoleVo loadBaseRole(Long roleId){
		return (BaseRoleVo)baseDao.queryForObject("baseRoleSql.loadBaseRoleByKey",roleId);
	}
	public int queryBaseRoleCount(BaseRole baseRole){
		return (int)baseDao.queryForObject("baseRoleSql.queryBaseRoleCount",baseRole);
	}

	@Override
	public List<BaseRoleVo> queryList(Map<String, Object> map) {
		return baseDao.queryForList("baseRoleSql.queryList",map, BaseRoleVo.class);
	}

}