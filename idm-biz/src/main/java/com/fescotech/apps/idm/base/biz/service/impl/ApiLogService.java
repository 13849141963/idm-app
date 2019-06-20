package com.fescotech.apps.idm.base.biz.service.impl;

import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.biz.bo.IApiLogBo;
import com.fescotech.apps.idm.base.biz.service.IApiLogService;
import com.fescotech.apps.idm.base.domain.ApiLog;
import com.fescotech.apps.idm.base.domain.vo.ApiLogVo;

/**
 * API日志管理service
 * @author:lzl
 * @time:2017年6月29日 下午3:39:40
 */
public class ApiLogService implements IApiLogService{

	private IApiLogBo apiLogBo;
	
	public void setApiLogBo(IApiLogBo apiLogBo) {
		this.apiLogBo = apiLogBo;
	}

	/**
	 * 新增日志信息
	 * @param baseLog
	 */
	@Override
	public void insert(ApiLog apiLog) {
		apiLogBo.insert(apiLog);
	}

	/**
	 * 获取日志信息列表（分页）
	 * @param baseLog
	 * @return
	 */
	@Override
	public List<ApiLogVo> queryApiLogList(Map<String, Object> map) {
		return apiLogBo.queryApiLogList(map);
	}

	/**
	 * 获取日志总数
	 * @param baseLog
	 * @return
	 */
	@Override
	public int queryApiLogCount(ApiLog baseLog) {
		return apiLogBo.queryApiLogCount(baseLog);
	}

	/**
	 * 根据Id获取日志信息
	 * @param logId
	 * @return
	 */
	@Override
	public ApiLogVo queryLogMsg(String logId) {
		return apiLogBo.queryLogMsg(logId);
	}

}
