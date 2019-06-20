package com.fescotech.apps.idm.base.domain;

/****
 * 数据集和客户的关联表
 */
public class BaseUserData {

    private Long user_base_id;//

    private Long base_id;//

    private Long user_id;//

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
}
