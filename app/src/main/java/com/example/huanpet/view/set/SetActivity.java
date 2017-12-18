package com.example.huanpet.view.set;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.widget.Toast;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;
import com.example.huanpet.utils.PreferencesUtil;
import com.example.huanpet.view.set.view.GuanYuActivity;
import com.example.huanpet.view.set.view.JianYiActivity;
import com.example.huanpet.view.set.view.NewGongNengActivity;
import com.example.huanpet.view.set.view.PingFenActivity;



public class SetActivity extends BaseActivity implements View.OnClickListener{

    private CustomTool set_custom;
    private RelativeLayout jianyi;
    private RelativeLayout clear;
    private RelativeLayout pingfen;
    private RelativeLayout guanyu;
    private RelativeLayout wifi;
    private RelativeLayout new_gongneng;
    private RelativeLayout clear_cache;
    private TextView exit_login;


    @Override
    public int initLayoutID() {
        return R.layout.activity_set;
    }

    @Override
    public void initView() {
        set_custom = (CustomTool) findViewById(R.id.set_custom);
        jianyi = (RelativeLayout) findViewById(R.id.jianyi);
        clear = (RelativeLayout) findViewById(R.id.clear_cache);
        pingfen = (RelativeLayout) findViewById(R.id.pingfen);
        guanyu = (RelativeLayout) findViewById(R.id.guanyu);
        wifi = (RelativeLayout) findViewById(R.id.wifi);
        new_gongneng = (RelativeLayout) findViewById(R.id.new_gongneng);
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
            jianyi.setOnClickListener(this);
            exit_login.setOnClickListener(this);
    }

    @Override
    public void setMyAppTitle() {
            set_custom.setAppTitle("设置");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jianyi:
                startActivity(new Intent(this, JianYiActivity.class));
                break;
            case R.id.new_gongneng:
                startActivity(new Intent(this, NewGongNengActivity.class));
                break;
            case R.id.pingfen:
                startActivity(new Intent(this, PingFenActivity.class));
                break;
            case R.id.guanyu:
                startActivity(new Intent(this, GuanYuActivity.class));
                break;
            case R.id.wifi:

                break;
            case R.id.cache:

                break;
            case R.id.clear_cache:

                break;
            case R.id.exit_login:
                PreferencesUtil.getInstance().clearUserMeassage();
                finish();
                String userId = PreferencesUtil.getInstance().getUserId();
                if (userId==null)
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                else
                PreferencesUtil.getInstance().clearUserMeassage();
                finish();
                break;
        }
    }
}
