package com.example.huanpet.app;

import android.app.Application;

import com.example.huanpet.utils.util.AppUtils;
import com.example.huanpet.view.user.ScreenAdaptation;

/**
 * Created by 阿禹 on 2017/12/6.
 */

public class AppService extends Application {
    public static BaseActivity baseActivity;
    private static AppService mApplication;
    public synchronized static AppService getInstance() {
        return mApplication;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication=this;
        //需要传入ui设计给的大小
        AppUtils.setAppContext(this);
        new ScreenAdaptation(this, 720,1280).register();
    }
}
