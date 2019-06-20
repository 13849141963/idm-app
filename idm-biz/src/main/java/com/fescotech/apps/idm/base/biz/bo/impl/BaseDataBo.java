package com.fescotech.apps.idm.base.biz.bo.impl;

import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.base.biz.bo.IBaseDataBo;
import com.fescotech.apps.idm.base.domain.BaseData;

import java.util.List;
import java.util.Map;

public class BaseDataBo extends AbstractBoT implements IBaseDataBo {

    @Override
    public void insert(BaseData baseData) {
        baseDao.insert("baseDataSql.insert",baseData);
    }

    @Override
    public BaseData queryByKey(Long dataId) {
        return (BaseData) baseDao.queryForObject("baseDataSql.queryByKey",dataId);
    }

    @Override
    public void deleteBatch(Long[] dataIds) {
        baseDao.delete("baseDataSql.deleteBatch",dataIds);
    }

    @Override
    public List<BaseData> queryList(Map<String, Object> queryData) {
        return baseDao.queryForList("baseDataSql.queryList",queryData,BaseData.class);
    }

    @Override
    public int queryTotal() {
        return (int) baseDao.queryForObject("baseDataSql.queryTotal",null);
    }

    @Override
    public void update(BaseData baseData) {

        baseDao.update("baseDataSql.update",baseData);
    }
    //多条件进行查询:dataId,dataName,dataType
    @Override
    public List<BaseData> queryByData(BaseData baseData){
       return baseDao.queryForList("baseDataSql.queryByData",baseData,BaseData.class);
    }
    @Override
    public List<BaseData> queryByDataName(BaseData baseData){
        return baseDao.queryForList("baseDataSql.queryDataByName",baseData,BaseData.class);
    }

    @Override
    public int queryFuzzyTotal(BaseData baseData) {
        return (int) baseDao.queryForObject("baseDataSql.queryFuzzyTotal",baseData);
    }

}
