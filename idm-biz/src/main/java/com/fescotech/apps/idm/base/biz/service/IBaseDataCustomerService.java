package com.fescotech.apps.idm.base.biz.service;

import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.domain.BaseDataCustomer;;

public interface IBaseDataCustomerService {
	/**
	 * 添加数据权限集
	 */
	public void insert(BaseDataCustomer baseDataCustomer);

	/**
	 * 通过主键查询数据权限集
	 */
	public BaseDataCustomer queryByKey(Long dataId);

	/**
	 * 删除数据权限集
	 */
	public void deleteBatch(Long[] dataIds);

	/**
	 * 根据条件查询数据权限集
	 */
	List<BaseDataCustomer> queryList(Map<String, Object> queryData);

	/**
	 * 查询数据权限集总条数
	 */
	int queryTotal();

	/**
	 * 修改数据权限集总条数
	 */
	public void update(BaseDataCustomer baseDataCustomer);

	// 多条件进行查询:dataId,dataName,dataType
	public List<BaseDataCustomer> queryByData(BaseDataCustomer baseDataCustomer);

	// 模糊查询数据权限集
	public List<BaseDataCustomer> queryByDataName(BaseDataCustomer baseDataCustomer);
	
	public void deleteTree(Map map);
	
	public List<BaseDataCustomer> queryTreeNodes(Map map);
}
