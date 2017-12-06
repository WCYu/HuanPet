package com.example.huanpet.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.huanpet.R;

/**
 * Created by 阿禹 on 2017/12/6.
 */

public  abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutID());
        AppService.baseActivity=this;
        initView();
        initData();
        initAdapter();
        initListener();
        setMyAppTitle();
    }
    public abstract int initLayoutID();
    public abstract void initView();
    public abstract void initAdapter();
    public abstract void initData();
    public abstract void initListener();
    public abstract void setMyAppTitle();
}
