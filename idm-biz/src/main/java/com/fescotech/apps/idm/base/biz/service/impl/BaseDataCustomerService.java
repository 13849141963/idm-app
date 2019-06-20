package com.fescotech.apps.idm.base.biz.service.impl;

import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.biz.bo.IBaseDataCustomerBo;
import com.fescotech.apps.idm.base.biz.service.IBaseDataCustomerService;
import com.fescotech.apps.idm.base.domain.BaseDataCustomer;

public class BaseDataCustomerService implements IBaseDataCustomerService {
	private IBaseDataCustomerBo baseDataCustomerBo;

	public IBaseDataCustomerBo getBaseDataCustomerBo() {
		return baseDataCustomerBo;
	}

	public void setBaseDataCustomerBo(IBaseDataCustomerBo baseDataCustomerBo) {
		this.baseDataCustomerBo = baseDataCustomerBo;
	}

	@Override
	public void insert(BaseDataCustomer baseDataCustomer) {
		// TODO Auto-generated method stub
		baseDataCustomerBo.insert(baseDataCustomer);
	}

	@Override
	public BaseDataCustomer queryByKey(Long dataId) {
		// TODO Auto-generated method stub
		return baseDataCustomerBo.queryByKey(dataId);
	}

	@Override
	public void deleteBatch(Long[] dataIds) {
		// TODO Auto-generated method stub
		baseDataCustomerBo.deleteBatch(dataIds);
	}

	@Override
	public List<BaseDataCustomer> queryList(Map<String, Object> queryData) {
		// TODO Auto-generated method stub
		return baseDataCustomerBo.queryList(queryData);
	}

	@Override
	public int queryTotal() {
		// TODO Auto-generated method stub
		return baseDataCustomerBo.queryTotal();
	}

	@Override
	public void update(BaseDataCustomer baseDataCustomer) {
		// TODO Auto-generated method stub
		baseDataCustomerBo.update(baseDataCustomer);
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
		baseDataCustomerBo.deleteTree(map);
	}

	@Override
	public List<BaseDataCustomer> queryTreeNodes(Map map) {
		// TODO Auto-generated method stub
		return baseDataCustomerBo.queryTreeNodes(map);
	}

}
