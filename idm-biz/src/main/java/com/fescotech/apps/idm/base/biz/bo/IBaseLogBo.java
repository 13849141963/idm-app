package com.fescotech.apps.idm.base.biz.bo;

import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.domain.BaseLog;
import com.fescotech.apps.idm.base.domain.vo.BaseLogVo;

public interface IBaseLogBo {

	public void insert(BaseLog baseLog);
	
	public List<BaseLogVo> queryBaseLogList(Map<String, Object> map);
	
	public int queryBaseLogCount(BaseLog baseLog);
	
	public BaseLogVo queryLogMsg(Long logId);
	
}
