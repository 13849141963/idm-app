package com.fescotech.apps.idm.base.biz.bo;

import com.fescotech.apps.idm.base.domain.BaseData;

import java.util.List;
import java.util.Map;

public interface IBaseDataBo {

    public void insert(BaseData baseData);

    public BaseData queryByKey(Long dataId);

    public void deleteBatch(Long[] dataIds);

    List<BaseData> queryList(Map<String, Object> queryData);

    int queryTotal();

    public void update(BaseData baseData);

    //多条件进行查询:dataId,dataName,dataType
    public List<BaseData> queryByData(BaseData baseData);
    //模糊查询数据权限集
    public List<BaseData> queryByDataName(BaseData baseData);
    //模糊查询数据权限集的条数
    public int queryFuzzyTotal(BaseData baseData);


}
