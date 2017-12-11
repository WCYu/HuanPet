package com.example.huanpet.view.user.image;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.huanpet.app.AppService;

/**
 * Created by 奇奇 on 2017/11/29.
 */
public class UserManagers {

    private static UserManagers sInstance;

    private SharedPreferences prefs;

    private boolean isUserInfoRetrieved;

    private UserManagers(){
        prefs = AppService.baseActivity.getSharedPreferences("user_info", 0);
    }

    public static UserManagers getInstance() {
        if (sInstance == null) {
            sInstance = new UserManagers();
        }
        return sInstance;
    }

    public boolean isUserLogged() {
        String userId = prefs.getString("userId", null);
        return  !TextUtils.isEmpty(userId);
    }

    public String getUserId() {
        return prefs.getString("userId", null);
    }

    public void saveUserId(String userId) {
        prefs.edit().putString("userId", userId).commit();
    }

    public String getNickName() {
        return prefs.getString("nickName", null);
    }

    public void saveNickName(String nickName) {
        prefs.edit().putString("nickName", nickName).commit();
    }

    public String getUserFace() {
        return prefs.getString("userface", null);
    }

    public void saveUserFace(String userface) {
        prefs.edit().putString("userface", userface).commit();
    }

    public String getVerifycode (){return prefs.getString("verifycode", null);}

    public void saveVerifycode(String verifycode) {
        prefs.edit().putString("verifycode", verifycode).commit();
    }

    /**
     * 是否成功请求过用户信息
     * @return
     */
    public boolean isUserInfoRetrieved() {
        return isUserInfoRetrieved;
    }

    public void setUserInfoRetrieved(boolean b) {
        isUserInfoRetrieved = b;
    }

    /**
     * 删除用户信息
     */
    public void clearUser() {
        prefs.edit().remove("userId").remove("nickName").remove("userface").remove("verifycode").commit();
    }
}
