package com.fescotech.apps.idm.base.biz.service;

import com.fescotech.apps.idm.base.domain.BaseData;

import java.util.List;
import java.util.Map;

/**
 * 数据集权限管理
 */
public interface IBaseDataService {

    /**
     * 添加数据权限集
     */
    public void insert(BaseData baseData);

    /**
     * 通过主键查询数据权限集
     */
    public BaseData queryByKey(Long dataId);

    /**
     * 删除数据权限集
     */
    public void deleteBatch(Long[] dataIds);

    /**
     * 根据条件查询数据权限集
     */
    List<BaseData> queryList(Map<String, Object> queryData);

    /**
     * 查询数据权限集总条数
     */
    int queryTotal();

    /**
     * 修改数据权限集总条数
     */
    public void update(BaseData baseData);

    //多条件进行查询:dataId,dataName,dataType
    public List<BaseData> queryByData(BaseData baseData);

    //模糊查询数据权限集
    public List<BaseData> queryByDataName(BaseData baseData);

    //模糊查询数据权限集的条数
    public int queryFuzzyTotal(BaseData baseData);

}
