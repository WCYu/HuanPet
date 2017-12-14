package com.example.huanpet.view.set;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;

public class SetActivity extends BaseActivity {

    private CustomTool set_custom;
    private RelativeLayout jilu;
    private RelativeLayout clear;
    private RelativeLayout pingfen;
    private RelativeLayout guanyu;
    private RelativeLayout wife;
    private RelativeLayout sycnc;
    private RelativeLayout clear_cache;
    private TextView exit_login;


    @Override
    public int initLayoutID() {
        return R.layout.activity_set;
    }

    @Override
    public void initView() {
        set_custom = (CustomTool) findViewById(R.id.set_custom);
        jilu = (RelativeLayout) findViewById(R.id.jilu);
        clear = (RelativeLayout) findViewById(R.id.clear);
        pingfen = (RelativeLayout) findViewById(R.id.pingfen);
        guanyu = (RelativeLayout) findViewById(R.id.guanyu);
        wife = (RelativeLayout) findViewById(R.id.wife);
        sycnc = (RelativeLayout) findViewById(R.id.sycnc);
        clear_cache = (RelativeLayout) findViewById(R.id.clear_cache);
        exit_login = (TextView) findViewById(R.id.exit_login);
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {
        set_custom.initViewsVisible(true,true,false,false);
        set_custom.setReturnBtn(" ");

    }

    @Override
    public void initListener() {
            set_custom.setOnLeftButtonClickListener(new CustomTool.OnLeftButtonClickListener() {
                @Override
                public void onLeftButtonClick(View v) {
                    finish();
                }
            });
    }

    @Override
    public void setMyAppTitle() {
            set_custom.setAppTitle("设置");
    }
}
