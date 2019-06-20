package com.fescotech.apps.idm.base.biz.service;

import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.domain.BaseCorpinfo;
import com.fescotech.apps.idm.base.domain.vo.BaseCorpinfoVo;

public interface IBaseCorpinfoService {
	
	List<BaseCorpinfoVo> queryTreeList();

	List<BaseCorpinfo> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	BaseCorpinfo queryObject(Long corpId);

	void save(BaseCorpinfo corp);

	void update(BaseCorpinfo corp);

	void deleteBatch(Long[] corpIds);

}
