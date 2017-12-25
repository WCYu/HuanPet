package com.example.huanpet.view.pet.api;

import android.app.Application;

import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.PreferencesUtil;
import com.example.huanpet.view.user.ScreenAdaptation;


/**
 * Created by 阿三 on 2017/12/7.
 */
public class App extends Application {
    public static BaseActivity baseActivity;
    private String SP_FILE_NAME="SP_FILE";
    public static App mApplication;
    private PreferencesUtil mSpUtil;


    public synchronized static App getInstance() {
        return mApplication;
    }
    public synchronized PreferencesUtil getmSpUtil(){
        if(mSpUtil==null){
           // mSpUtil=new PreferencesUtil(this,SP_FILE_NAME);
        }
        return mSpUtil;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication=this;
        new ScreenAdaptation(this, 720,1280).register();


    }
}
