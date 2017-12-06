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
            synchronized (OkhttpUtil.class){
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
}
