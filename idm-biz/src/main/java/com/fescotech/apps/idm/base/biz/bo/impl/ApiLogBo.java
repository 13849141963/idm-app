package com.fescotech.apps.idm.base.biz.bo.impl;

import java.util.List;
import java.util.Map;

import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.base.biz.bo.IApiLogBo;
import com.fescotech.apps.idm.base.domain.ApiLog;
import com.fescotech.apps.idm.base.domain.vo.ApiLogVo;

public class ApiLogBo extends AbstractBoT implements IApiLogBo{

	@Override
	public void insert(ApiLog apiLog) {
		baseDao.insert("apiLogSql.insertApiLog", apiLog);
	}

	@Override
	public List<ApiLogVo> queryApiLogList(Map<String, Object> map) {
		return baseDao.queryForList("apiLogSql.queryList", map, ApiLogVo.class);
	}

	@Override
	public int queryApiLogCount(ApiLog apiLog) {
		return (int)baseDao.queryForObject("apiLogSql.queryApiLogCount",apiLog);
	}

	@Override
	public ApiLogVo queryLogMsg(String logId) {
		return (ApiLogVo) baseDao.queryForObject("apiLogSql.loardLogMsgByKey", logId);
	}

}
