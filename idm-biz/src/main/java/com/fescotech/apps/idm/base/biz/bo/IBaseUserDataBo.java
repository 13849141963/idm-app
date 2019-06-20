package com.fescotech.apps.idm.base.biz.bo;
import com.fescotech.apps.idm.base.domain.BaseUserData;
import com.fescotech.apps.idm.base.domain.vo.BaseUserDataVo;

import java.util.List;
import java.util.Map;

public interface IBaseUserDataBo {


    public void insert(BaseUserData baseUserData);

    public void insertList(List<BaseUserData> list);

    public BaseUserData queryByKey(Long user_base_id);

    public void deleteBatch(Long[] user_base_id);

    public void update(BaseUserData baseUserData);

    int queryTotal();

    List<BaseUserData> queryList(Map<String, Object> queryData);

    //多条件进行查询:
    public List<BaseUserDataVo> queryByData(BaseUserData baseData);
    //条件查询条数
    public int queryByDataCount(BaseUserData baseData);
}
