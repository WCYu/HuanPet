package com.example.huanpet.view.pet;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/18.
 */

class Imm  implements Serializable {

    private String path = null;

    private String immuneCode = null;

    private String immuneName = null;

    private String immuneTime = null;

    private String petCode = null;

    private String petName = null;

    private String userId = null;

    private String userName = null;

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPetCode() {
        return petCode;
    }

    public void setPetCode(String petCode) {
        this.petCode = petCode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public void setImmuneName(String immuneName) {
        this.immuneName = immuneName;
    }

    public String getImmuneTime() {
        return immuneTime;
    }

    public void setImmuneTime(String immuneTime) {
        this.immuneTime = immuneTime;
    }

}
