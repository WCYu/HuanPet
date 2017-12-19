package com.example.huanpet.view.pet.adapter;

import java.util.Date;

/**
 * Created by Administrator on 2017/12/18.
 */

public class ImmuneInfo {
    private static final long serialVersionUID = 1L;
    private long id;
    private String immuneCode;// 免疫code
    private String immuneName;// 免疫名称
    private Date immuneTime;// 免疫时间
    private int isUse; // 是否启用
    private int state; // 免疫状�??

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImmuneCode() {
        return immuneCode;
    }

    public void setImmuneCode(String immuneCode) {
        this.immuneCode = immuneCode;
    }

    public String getImmuneName() {
        return immuneName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setImmuneName(String immuneName) {
        this.immuneName = immuneName;
    }

    public Date getImmuneTime() {
        return immuneTime;
    }

    public void setImmuneTime(Date immuneTime) {
        this.immuneTime = immuneTime;
    }

    public int getIsUse() {
        return isUse;
    }

    public void setIsUse(int isUse) {
        this.isUse = isUse;
    }

    @Override
    public String toString() {
        return "ImmuneInfo [id=" + id + ", immuneCode=" + immuneCode
                + ", immuneName=" + immuneName + ", immuneTime=" + immuneTime
                + ", isUse=" + isUse + ", state=" + state + "]";
    }

}
