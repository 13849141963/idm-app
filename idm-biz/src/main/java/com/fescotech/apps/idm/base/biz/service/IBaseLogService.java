package com.fescotech.apps.idm.base.biz.service;

import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.domain.BaseLog;
import com.fescotech.apps.idm.base.domain.vo.BaseLogVo;

/**
 * 日志管理service
 * @author:lzl
 * @time:2017年6月29日 下午3:39:40
 */
public interface IBaseLogService {

	/**
	 * 新增日志信息
	 * @param baseLog
	 */
	public void insert(BaseLog baseLog);
	
	/**
	 * 获取日志信息列表(分页)
	 * @param baseLog
	 * @return
	 */
	public List<BaseLogVo> queryBaseLogList(Map<String, Object> map);
	
	/**
	 * 获取日志总数
	 * @param baseLog
	 * @return
	 */
	public int queryBaseLogCount(BaseLog baseLog);
	
	/**
	 * 根据Id获取日志信息
	 * @param logId
	 * @return
	 */
	public BaseLogVo queryLogMsg(Long logId);
}
