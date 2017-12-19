package com.example.huanpet.utils;

import android.content.SharedPreferences;

import com.example.huanpet.app.AppService;

import okhttp3.OkHttpClient;

/**
 * Created by 阿禹 on 2017/12/6.
 */

public class PreferencesUtil {
    private static PreferencesUtil preferencesUtil;
    private SharedPreferences sharedPreferences;
    private PreferencesUtil(){
        sharedPreferences = AppService.baseActivity.getSharedPreferences("pet", 0);
    }
    public static PreferencesUtil getInstance(){
        if(preferencesUtil==null){
            synchronized (PreferencesUtil.class){
                if(preferencesUtil==null){
                    preferencesUtil=new PreferencesUtil();
                }
            }
        }
        return preferencesUtil;
    }
    public void setWelcomeId(int id){
        sharedPreferences.edit().putInt("welcomeId",id).commit();
    }
    public int getWelcomeId(){
        return sharedPreferences.getInt("welcomeId",0);
    }
    public void setUserId(String name){
        sharedPreferences.edit().putString("userId",name).commit();
    }
    public String getUserId(){
        return sharedPreferences.getString("userId",null);
    }

    public void setUserName(String userName){
        sharedPreferences.edit().putString("userName",userName).commit();
    }
    public String getUserName(){
        return sharedPreferences.getString("userName",null);
    }

    public void setUserSex(String userSex){
        sharedPreferences.edit().putString("userSex",userSex).commit();
    }
    public String getUserSex(){
        return sharedPreferences.getString("userSex",null);
    }

    public void setBirthday(String Birthday){
        sharedPreferences.edit().putString("Birthday",Birthday).commit();
    }
    public String getBirthday(){
        return sharedPreferences.getString("Birthday",null);
    }


    public void setUserPhone(String userPhone){
        sharedPreferences.edit().putString("userPhone",userPhone).commit();
    }
    public String getUserPhone(){
        return sharedPreferences.getString("userPhone",null);
    }

    public void setQq(String Qq){
        sharedPreferences.edit().putString("Qq",Qq).commit();
    }
    public String getQq(){
        return sharedPreferences.getString("Qq",null);
    }

    public void setAddress(String Address){
        sharedPreferences.edit().putString("Address",Address).commit();
    }
    public String getAddress(){
        return sharedPreferences.getString("Address",null);
    }

    public void setWechat(String Wechat){
        sharedPreferences.edit().putString("Wechat",Wechat).commit();
    }
    public String getWechat(){
        return sharedPreferences.getString("Wechat",null);
    }

    public void clearUserMeassage(){
        setUserId(null);
    }
}
