package com.fescotech.apps.idm.base.biz.bo.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fescotech.common.ext.biz.bo.impl.ExtendBoT;
import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.base.biz.bo.IBaseDataCustomerBo;
import com.fescotech.apps.idm.base.domain.BaseData;
import com.fescotech.apps.idm.base.domain.BaseDataCustomer;

@Repository
public class BaseDataCustomerBo extends AbstractBoT implements IBaseDataCustomerBo {
	public void insert(BaseDataCustomer baseDataCustomer) {
		baseDao.insert("baseDataCustomerSql.insert", baseDataCustomer);
	}

	public void update(BaseDataCustomer baseDataCustomer) {
		baseDao.update("baseDataCustomerSql.update", baseDataCustomer);
	}

	public List<BaseDataCustomer> queryBaseDataCustomer(BaseDataCustomer baseDataCustomer) {
		return baseDao.queryForList("baseDataCustomerSql.queryBaseDataCustomer", baseDataCustomer,
				BaseDataCustomer.class);
	}

	public BaseDataCustomer loadBaseDataCustomer(Long dataCustomerId) {
		return (BaseDataCustomer) baseDao.queryForObject("baseDataCustomerSql.queryByKey", dataCustomerId);
	}

	@Override
	public Long createKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> createKey(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executeBatch() {
		// TODO Auto-generated method stub

	}

	@Override
	public void startBatch() {
		// TODO Auto-generated method stub

	}

	@Override
	public BaseDataCustomer queryByKey(Long dataCustomerId) {
		// TODO Auto-generated method stub
		return (BaseDataCustomer) baseDao.queryForObject("baseDataCustomerSql.queryByKey", dataCustomerId);
	}

	@Override
	public void deleteBatch(Long[] Ids) {
		// TODO Auto-generated method stub
		baseDao.delete("baseDataCustomerSql.deleteBatch",Ids);
	}

	@Override
	public List<BaseDataCustomer> queryList(Map<String, Object> queryData) {
		// TODO Auto-generated method stub
		return baseDao.queryForList("baseDataCustomerSql.queryList", queryData, BaseDataCustomer.class);
	}

	@Override
	public int queryTotal() {
		// TODO Auto-generated method stub
		return (int) baseDao.queryForObject("baseDataCustomerSql.queryTotal", null);
	}

	@Override
	public List<BaseDataCustomer> queryByData(BaseDataCustomer baseDataCustomer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BaseDataCustomer> queryByDataName(BaseDataCustomer baseDataCustomer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTree(Map map) {
		// TODO Auto-generated method stub
		baseDao.delete("baseDataCustomerSql.deleteTree",map);
	}

	@Override
	public List<BaseDataCustomer> queryTreeNodes(Map map) {
		// TODO Auto-generated method stub
		return baseDao.queryForList("baseDataCustomerSql.queryTreeNodes", map, BaseDataCustomer.class);
	}

}