package com.fescotech.apps.idm.base.biz.bo.impl;

import java.util.List;
import java.util.Map;

import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.base.biz.bo.IBaseSysBo;
import com.fescotech.apps.idm.base.domain.BaseSys;

public class BaseSysBo extends AbstractBoT implements IBaseSysBo{

	@Override
	public List<BaseSys> queryBaseSys(Map<String, Object> query) {
		return baseDao.queryForList("baseSysSql.queryList",query,BaseSys.class);
	}

	@Override
	public int queryTotal(Map<String, Object> queryData) {
		return (int) baseDao.queryForObject("baseSysSql.queryTotal",queryData);
	}

	@Override
	public BaseSys loadBaseSys(Long sysId) {
		return (BaseSys)baseDao.queryForObject("baseSysSql.queryByKey",sysId);
	}

	@Override
	public void deleteBatch(Long[] sysIds) {
		baseDao.delete("baseSysSql.deleteBatch", sysIds);
	}

	@Override
	public void insert(BaseSys sys) {
		baseDao.insert("baseSysSql.insert", sys);
	}

	@Override
	public void update(BaseSys sys) {
		baseDao.update("baseSysSql.update",sys);
	}
	
	@Override
	public List<BaseSys> queryBaseSysBySys(BaseSys sys) {
		return baseDao.queryForList("baseSysSql.queryBaseSysBySys",sys,BaseSys.class);
	}
	
	@Override
	public BaseSys loadBaseSysBySys(BaseSys sys) {
		return (BaseSys)baseDao.queryForObject("baseSysSql.queryBaseSysBySys",sys);
	}

}
