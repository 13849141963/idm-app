package com.fescotech.apps.idm.base.biz.service.impl;

import com.fescotech.apps.idm.base.biz.bo.IBaseDataBo;
import com.fescotech.apps.idm.base.biz.service.IBaseDataService;
import com.fescotech.apps.idm.base.domain.BaseData;

import java.util.List;
import java.util.Map;

public class BaseDataService  implements IBaseDataService {

    private IBaseDataBo  baseDataBo;

    public void setBaseDataBo(IBaseDataBo baseDataBo) {
        this.baseDataBo = baseDataBo;
    }

    @Override
    public void insert(BaseData baseData) {
        baseDataBo.insert(baseData);
    }

    @Override
    public BaseData queryByKey(Long dataId) {
        return baseDataBo.queryByKey(dataId);
    }

    @Override
    public void deleteBatch(Long[] dataIds) {
        baseDataBo.deleteBatch(dataIds);
    }

    @Override
    public List<BaseData> queryList(Map<String, Object> queryData) {
        return baseDataBo.queryList(queryData);
    }

    @Override
    public int queryTotal() {
        return baseDataBo.queryTotal();
    }

    @Override
    public void update(BaseData baseData) {
        baseDataBo.update(baseData);
    }

    //多条件进行查询:dataId,dataName,dataType
    @Override
    public List<BaseData> queryByData(BaseData baseData){
        return baseDataBo.queryByData(baseData);
    }

    //模糊查询数据权限集
    @Override
    public List<BaseData> queryByDataName(BaseData baseData){
        return baseDataBo.queryByDataName(baseData);
    }
    //获取模糊查询数据权限集的条数
    @Override
    public int queryFuzzyTotal(BaseData baseData) {
        return baseDataBo.queryFuzzyTotal(baseData);
    }
}
