package com.fescotech.apps.idm.base.biz.bo.impl;

import java.util.List;
import java.util.Map;

import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.base.biz.bo.IBaseLogBo;
import com.fescotech.apps.idm.base.domain.BaseLog;
import com.fescotech.apps.idm.base.domain.vo.BaseLogVo;

public class BaseLogBo extends AbstractBoT implements IBaseLogBo{

	@Override
	public void insert(BaseLog baseLog) {
		baseDao.insert("baseLogSql.insertBaseLog", baseLog);
	}

	@Override
	public List<BaseLogVo> queryBaseLogList(Map<String, Object> map) {
		return baseDao.queryForList("baseLogSql.queryList", map, BaseLogVo.class);
	}

	@Override
	public int queryBaseLogCount(BaseLog baseLog) {
		return (int)baseDao.queryForObject("baseLogSql.queryBaseLogCount",baseLog);
	}

	@Override
	public BaseLogVo queryLogMsg(Long logId) {
		return (BaseLogVo) baseDao.queryForObject("baseLogSql.loardLogMsgByKey", logId);
	}

}
