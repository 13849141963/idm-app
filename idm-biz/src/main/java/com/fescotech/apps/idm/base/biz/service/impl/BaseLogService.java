package com.fescotech.apps.idm.base.biz.service.impl;

import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.biz.bo.IBaseLogBo;
import com.fescotech.apps.idm.base.biz.service.IBaseLogService;
import com.fescotech.apps.idm.base.domain.BaseLog;
import com.fescotech.apps.idm.base.domain.vo.BaseLogVo;

/**
 * 日志管理service
 * @author:lzl
 * @time:2017年6月29日 下午3:39:40
 */
public class BaseLogService implements IBaseLogService{

	private IBaseLogBo baseLogBo;
	
	public void setBaseLogBo(IBaseLogBo baseLogBo) {
		this.baseLogBo = baseLogBo;
	}

	/**
	 * 新增日志信息
	 * @param baseLog
	 */
	@Override
	public void insert(BaseLog baseLog) {
		baseLogBo.insert(baseLog);
	}

	/**
	 * 获取日志信息列表（分页）
	 * @param baseLog
	 * @return
	 */
	@Override
	public List<BaseLogVo> queryBaseLogList(Map<String, Object> map) {
		return baseLogBo.queryBaseLogList(map);
	}

	/**
	 * 获取日志总数
	 * @param baseLog
	 * @return
	 */
	@Override
	public int queryBaseLogCount(BaseLog baseLog) {
		return baseLogBo.queryBaseLogCount(baseLog);
	}

	/**
	 * 根据Id获取日志信息
	 * @param logId
	 * @return
	 */
	@Override
	public BaseLogVo queryLogMsg(Long logId) {
		return baseLogBo.queryLogMsg(logId);
	}

}
