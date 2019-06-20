package com.fescotech.apps.idm.base.biz.service.impl;

import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.biz.bo.IBaseCorpinfoBo;
import com.fescotech.apps.idm.base.biz.service.IBaseCorpinfoService;
import com.fescotech.apps.idm.base.domain.BaseCorpinfo;
import com.fescotech.apps.idm.base.domain.vo.BaseCorpinfoVo;

public class BaseCorpinfoService implements IBaseCorpinfoService {

	private IBaseCorpinfoBo baseCorpinfoBo;
	
	public void setBaseCorpinfoBo(IBaseCorpinfoBo baseCorpinfoBo) {
		this.baseCorpinfoBo = baseCorpinfoBo;
	}
	
	@Override
	public List<BaseCorpinfoVo> queryTreeList() {
		return baseCorpinfoBo.queryTreeList();
	}

	@Override
	public List<BaseCorpinfo> queryList(Map<String, Object> map) {
		return baseCorpinfoBo.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return baseCorpinfoBo.queryTotal(map);
	}

	@Override
	public BaseCorpinfo queryObject(Long corpId) {
		return baseCorpinfoBo.get(corpId);
	}

	@Override
	public void save(BaseCorpinfo corp) {
		baseCorpinfoBo.insert(corp);
	}

	@Override
	public void update(BaseCorpinfo corp) {
		baseCorpinfoBo.update(corp);
	}

	@Override
	public void deleteBatch(Long[] corpIds) {
		baseCorpinfoBo.deleteBatch(corpIds);
	}

}
