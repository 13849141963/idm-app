package com.fescotech.apps.idm.base.biz.service;

import com.fescotech.apps.idm.base.domain.BaseUserData;
import com.fescotech.apps.idm.base.domain.vo.BaseUserDataVo;

import java.util.List;
import java.util.Map;

/**
 * 客户权限集关联
 */
public interface IBaseUserDataService {

    /**
     * 添加客户权限集
     */
    public void insert(BaseUserData baseUserData);

    /**
     * 批量添加客户权限集
     */
    public void insertList(List<BaseUserData> list);

    /**
     * 删除客户权限集
     */
    public void deleteBatch(Long[] user_base_id);

    /**
     * 根据条件查询客户权限集
     */
    List<BaseUserData> queryList(Map<String, Object> queryUserData);

    /**
     * 查询客户权限集总条数
     */
    int queryTotal();

    /**
     * 修改客户权限集总条数
     */
    public void update(BaseUserData baseUserData);

    //查询用户的权限集
    List<BaseUserDataVo> queryByData(BaseUserData baseUserData);

    //条件查询条数
    public int queryByDataCount(BaseUserData baseData);


}
