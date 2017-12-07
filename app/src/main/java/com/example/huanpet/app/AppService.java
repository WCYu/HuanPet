package com.example.huanpet.app;

import android.app.Application;

import com.example.huanpet.view.user.ScreenAdaptation;

/**
 * Created by 阿禹 on 2017/12/6.
 */

public class AppService extends Application {
    public static BaseActivity baseActivity;
    @Override
    public void onCreate() {
        super.onCreate();
        //需要传入ui设计给的大小
        new ScreenAdaptation(this, 720,1280).register();
    }
}
