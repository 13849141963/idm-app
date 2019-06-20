package com.fescotech.apps.idm.base.biz.bo;

import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.domain.ApiLog;
import com.fescotech.apps.idm.base.domain.vo.ApiLogVo;

public interface IApiLogBo {

	public void insert(ApiLog apiLog);
	
	public List<ApiLogVo> queryApiLogList(Map<String, Object> map);
	
	public int queryApiLogCount(ApiLog apiLog);
	
	public ApiLogVo queryLogMsg(String logId);
	
}
