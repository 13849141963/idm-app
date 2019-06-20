package com.fescotech.apps.idm.base.domain.vo;

import com.fescotech.apps.idm.base.domain.BaseUser;

import java.util.Date;

/****
 * 数据集和客户的关联表
 */
public class BaseUserDataVo extends BaseUser {

    private Long user_base_id;//

    private Long base_id;//

    private Long user_id;//

    private long dataId;

    private String dataName;

    private String dataType;

    private Date dataCreateTime;

    public Long getUser_base_id() {
        return user_base_id;
    }

    public void setUser_base_id(Long user_base_id) {
        this.user_base_id = user_base_id;
    }

    public Long getBase_id() {
        return base_id;
    }

    public void setBase_id(Long base_id) {
        this.base_id = base_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public long getDataId() {
        return dataId;
    }

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName == null ? null : dataName.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public Date getDataCreateTime() {
        return dataCreateTime;
    }

    public void setDataCreateTime(Date dataCreateTime) {
        this.dataCreateTime = dataCreateTime;
    }
}
