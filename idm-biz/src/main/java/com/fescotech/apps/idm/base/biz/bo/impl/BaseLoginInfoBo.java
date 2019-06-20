package com.fescotech.apps.idm.base.biz.bo.impl;
import java.util.List;

import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.base.biz.bo.IBaseLoginInfoBo;
import com.fescotech.apps.idm.base.domain.BaseLoginInfo;

public class BaseLoginInfoBo extends AbstractBoT implements IBaseLoginInfoBo{

	public void insert(BaseLoginInfo baseLoginInfo){
		baseDao.insert("baseLoginInfoSql.insertBaseLoginInfo",baseLoginInfo);
	}
	
	public void update(BaseLoginInfo baseLoginInfo){
		baseDao.update("baseLoginInfoSql.updateBaseLoginInfo",baseLoginInfo);
	}
	
	public List<BaseLoginInfo> queryBaseLoginInfo(BaseLoginInfo baseLoginInfo){
		return baseDao.queryForList("baseLoginInfoSql.queryBaseLoginInfo",baseLoginInfo,BaseLoginInfo.class);
	}
	
	public BaseLoginInfo loadBaseLoginInfo(Long loginInfoId){
		return (BaseLoginInfo)baseDao.queryForObject("baseLoginInfoSql.queryByKey",loginInfoId);
	}
	public int queryBaseLoginInfoCount(BaseLoginInfo baseLoginInfo){
		return (int)baseDao.queryForObject("baseLoginInfoSql.queryBaseLoginInfoCount",baseLoginInfo);
	}
}