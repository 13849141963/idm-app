package com.fescotech.apps.idm.base.domain.vo;

import com.fescotech.apps.idm.base.domain.BaseData;

import java.util.List;

public class BaseDataVo extends BaseData {

    /**
     * ztree属性
     */
    private Boolean open;

    private List<?> list;

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
