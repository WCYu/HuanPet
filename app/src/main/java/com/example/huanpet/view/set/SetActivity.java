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
    private RelativeLayout wife;
    private RelativeLayout clear;
    private RelativeLayout pingfen;
    private RelativeLayout guanyu;
    private TextView version;
    private RelativeLayout sycnc;



    @Override
    public int initLayoutID() {
        return R.layout.activity_set;
    }

    @Override
    public void initView() {
        set_custom = (CustomTool) findViewById(R.id.set_custom);
        jilu = (RelativeLayout) findViewById(R.id.jilu);
        wife = (RelativeLayout) findViewById(R.id.wife);
        clear = (RelativeLayout) findViewById(R.id.clear);
        pingfen = (RelativeLayout) findViewById(R.id.pingfen);
        guanyu = (RelativeLayout) findViewById(R.id.guanyu);
        version = (TextView) findViewById(R.id.version);
        sycnc = (RelativeLayout) findViewById(R.id.sycnc);
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {
        set_custom.initViewsVisible(true,true,false,false);
        set_custom.setAppTitle("设置");
        set_custom.setReturnBtn("   ");
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

    }
}
