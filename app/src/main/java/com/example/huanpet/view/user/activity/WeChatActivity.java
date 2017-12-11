package com.example.huanpet.view.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;

public class WeChatActivity extends BaseActivity {

    private CustomTool customtool_wechat;
    private EditText wechat_edittext;
    @Override
    public int initLayoutID() {
        return R.layout.activity_we_chat;
    }

    @Override
    public void initView() {
        customtool_wechat = findViewById(R.id.customtool_wechat);
        customtool_wechat.setOnLeftButtonClickListener(new CustomTool.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View v) {
                Intent intentname = new Intent(WeChatActivity.this,UserActivity.class);
                startActivity(intentname);
            }
        });
        customtool_wechat.setAppTitle("微信");
        customtool_wechat.setRightTitle("提交");
        customtool_wechat.initViewsVisible(true,true,false,true);
        wechat_edittext = findViewById(R.id.wechat_edittext);
        customtool_wechat.setOnRightTitleClickListener(new CustomTool.OnRightTitleClickListener() {
            @Override
            public void onRightTitleClick(View v) {
                if (TextUtils.isEmpty(wechat_edittext.getText().toString())){
                    Toast.makeText(WeChatActivity.this, "微信号不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = getIntent();
                    intent.putExtra("wechat",wechat_edittext.getText().toString());
                    setResult(2,intent);
                    finish();
                }

            }
        });
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void setMyAppTitle() {

    }
}
