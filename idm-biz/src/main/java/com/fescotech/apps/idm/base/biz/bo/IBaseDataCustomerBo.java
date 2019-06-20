package com.fescotech.apps.idm.base.biz.bo;

import com.fescotech.common.ext.biz.bo.IExtendBoT;
import com.fescotech.apps.idm.base.domain.BaseDataCustomer;

import java.util.List;
import java.util.Map;

public interface IBaseDataCustomerBo extends IExtendBoT {
	public void insert(BaseDataCustomer baseDataCustomer);

	public void update(BaseDataCustomer baseDataCustomer);

	public List<BaseDataCustomer> queryBaseDataCustomer(BaseDataCustomer baseDataCustomer);

	public BaseDataCustomer loadBaseDataCustomer(Long dataCustomerId);

	public BaseDataCustomer queryByKey(Long dataId);

	public void deleteBatch(Long[] dataIds);

	List<BaseDataCustomer> queryList(Map<String, Object> queryData);

	int queryTotal();

	public List<BaseDataCustomer> queryByData(BaseDataCustomer baseDataCustomer);


	public List<BaseDataCustomer> queryByDataName(BaseDataCustomer baseDataCustomer);
	
	public void deleteTree(Map map);
	
	public List<BaseDataCustomer> queryTreeNodes(Map map);
}