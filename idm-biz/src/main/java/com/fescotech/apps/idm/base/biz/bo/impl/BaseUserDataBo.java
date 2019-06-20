package com.fescotech.apps.idm.base.biz.bo.impl;
import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.base.biz.bo.IBaseUserDataBo;
import com.fescotech.apps.idm.base.domain.BaseUserData;
import com.fescotech.apps.idm.base.domain.vo.BaseUserDataVo;
import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BaseUserDataBo extends AbstractBoT implements IBaseUserDataBo   {


    @Autowired
    private SqlMapClient sqlMapClient;
    @Override
    public void insert(BaseUserData baseUserData) {
        baseDao.insert("baseUserDataSql.insert",baseUserData);
    }
    //ibatis的批量插入
    @Override
    public void insertList(List<BaseUserData> list) {
        try {
            this.sqlMapClient.insert("baseUserDataSql.insertList",list);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public BaseUserData queryByKey(Long data_customer_id) {
        return (BaseUserData) baseDao.queryForObject("baseUserDataSql.queryByKey",data_customer_id);
    }

    @Override
    public void deleteBatch(Long[] user_base_id) {
        baseDao.delete("baseUserDataSql.deleteBatch",user_base_id);
    }

    @Override
    public void update(BaseUserData baseUserData) {
        baseDao.update("baseUserDataSql.update",baseUserData);
    }

    @Override
    public int queryTotal() {
        return (int) baseDao.queryForObject("baseUserDataSql.queryTotal",null);
    }

    @Override
    public List<BaseUserData> queryList(Map<String, Object> queryData) {
        return baseDao.queryForList("baseUserDataSql.queryList",queryData,BaseUserData.class);
    }

    @Override
    public List<BaseUserDataVo> queryByData(BaseUserData baseUserData) {
        return baseDao.queryForList("baseUserDataSql.queryByUserData",baseUserData,BaseUserDataVo.class);
    }

    @Override
    public int queryByDataCount(BaseUserData baseUserData) {
        return (int)baseDao.queryForObject("baseUserDataSql.queryByUserDataCount",baseUserData);
    }



    /*@Override
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
*/
}
