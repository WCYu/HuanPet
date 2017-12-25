package com.example.huanpet.view.pet.View;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.huanpet.app.AppService;
import com.example.huanpet.utils.util.PetInfo;
import com.example.huanpet.utils.util.TokenUtil;
import com.example.huanpet.utils.util.UserInfo;
import com.example.huanpet.view.pet.api.App;

/**
 * Created by Administrator on 2017/12/21.
 */

public class Uuu  {

    private static Uuu userManager;
    public UserInfo userInfo;
    private PetInfo petInfo;
    private SharedPreferences sharedPreferences;
    public synchronized static Uuu getIntance(){
        if(userManager==null){
            userManager=new Uuu();
        }
        return userManager;

    }
    public Uuu(){
        sharedPreferences= AppService.getInstance().getSharedPreferences("user_info",0);

    }

    //保存当前用户
    public void saveUser(UserInfo userInfo){
        if(userInfo==null){
            return;
        }
        this.userInfo=userInfo;
        sharedPreferences.edit().putString("user",userInfo.toString());
        sharedPreferences.edit().putString("userId",userInfo.getUserId());
        sharedPreferences.edit().putString("usetName",userInfo.getUserName());
        sharedPreferences.edit().commit();
    }
    public UserInfo getUserInfo(){

        return userInfo;
    }

    //用户是否登陆
    public boolean isLogin(){
        String userId= sharedPreferences.getString("userId",null);
        return !TextUtils.isEmpty(userId);
    }
//     public void saveLogin(){
//
//     }

    //清除用户信息
    public void clearUser(){
        userInfo=null;
        saveUser(userInfo);

    }
    //获取Token
    public void saveToken(){
        sharedPreferences.edit().putString("token", TokenUtil.createToken());
    }
    public String getToken(){
        String token=sharedPreferences.getString("token",null);
        return token;

    }
    //存储宠物信息
    public  void  savePetInfo(PetInfo petInfo){
        sharedPreferences.edit().putString("petInfo",petInfo.toString());
        this.petInfo=petInfo;
    }
    public PetInfo getPetInfo(){
        if(userInfo.getUserId()==null){
            return null;
        }
        return  petInfo;
    }
    //获取id
    public  String getUserId(){
        String id=sharedPreferences.getString("userId",null);
        return id;
    }
    //湖区用户名
    public  String getUserName(){
        String name=sharedPreferences.getString("usetName",null);
        return name;
    }
}
