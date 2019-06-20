package com.fescotech.apps.idm.base.biz.bo.impl;
import java.util.List;

import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.base.biz.bo.IBaseUserRoleDataBo;
import com.fescotech.apps.idm.base.domain.BaseUserRoleData;

public class BaseUserRoleDataBo extends AbstractBoT implements IBaseUserRoleDataBo{

	public void insert(BaseUserRoleData baseUserRoleData){
		baseDao.insert("baseUserRoleDataSql.insertBaseUserRoleData",baseUserRoleData);
	}
	
	public void update(BaseUserRoleData baseUserRoleData){
		baseDao.update("baseUserRoleDataSql.updateBaseUserRoleData",baseUserRoleData);
	}
	
	public List<BaseUserRoleData> queryBaseUserRoleData(BaseUserRoleData baseUserRoleData){
		return baseDao.queryForList("baseUserRoleDataSql.queryBaseUserRoleData",baseUserRoleData,BaseUserRoleData.class);
	}
	
	public BaseUserRoleData loadBaseUserRoleData(Long baseUserRoleDataId){
		return (BaseUserRoleData)baseDao.queryForObject("baseUserRoleDataSql.queryByKey",baseUserRoleDataId);
	}
	public int queryBaseUserRoleDataCount(BaseUserRoleData baseUserRoleData){
		return (int)baseDao.queryForObject("baseUserRoleDataSql.queryBaseUserRoleDataCount",baseUserRoleData);
	}
}