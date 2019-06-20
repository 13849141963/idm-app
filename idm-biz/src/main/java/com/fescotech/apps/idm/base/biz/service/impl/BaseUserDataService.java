package com.fescotech.apps.idm.base.biz.service.impl;

import com.fescotech.apps.idm.base.biz.bo.IBaseUserDataBo;
import com.fescotech.apps.idm.base.biz.service.IBaseUserDataService;
import com.fescotech.apps.idm.base.domain.BaseUserData;
import com.fescotech.apps.idm.base.domain.vo.BaseUserDataVo;

import java.util.List;
import java.util.Map;

public class BaseUserDataService implements IBaseUserDataService {



    private IBaseUserDataBo baseUserDataBo;

    public void setBaseUserDataBo(IBaseUserDataBo baseUserDataBo) {
        this.baseUserDataBo = baseUserDataBo;
    }

    @Override
    public void insert(BaseUserData baseUserData) {
        baseUserDataBo.insert(baseUserData);
    }

    //批量插入权限集
    @Override
    public void insertList(List<BaseUserData> list) {
        baseUserDataBo.insertList(list);
    }

    @Override
    public void deleteBatch(Long[] user_base_id) {
        baseUserDataBo.deleteBatch(user_base_id);
    }

    @Override
    public List<BaseUserData> queryList(Map<String, Object> queryUserData) {
        return baseUserDataBo.queryList(queryUserData);
    }

    @Override
    public int queryTotal() {
        return baseUserDataBo.queryTotal();
    }

    @Override
    public void update(BaseUserData baseUserData) {
        baseUserDataBo.update(baseUserData);
    }

    @Override
    public List<BaseUserDataVo> queryByData(BaseUserData baseUserData) {
        return baseUserDataBo.queryByData(baseUserData);
    }

    @Override
    public int queryByDataCount(BaseUserData baseData) {
        return baseUserDataBo.queryByDataCount(baseData);
    }
}
