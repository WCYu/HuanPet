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

public class PhoneActivity extends BaseActivity {

    private CustomTool customtool_phone;
    private EditText phnoe_edittext;
    @Override
    public int initLayoutID() {
        return R.layout.activity_phone;
    }

    @Override
    public void initView() {
        customtool_phone = findViewById(R.id.customtool_phone);
        customtool_phone.setOnLeftButtonClickListener(new CustomTool.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View v) {
                Intent intentname = new Intent(PhoneActivity.this,UserActivity.class);
                startActivity(intentname);
            }
        });
        customtool_phone.setAppTitle("手机");
        customtool_phone.setRightTitle("提交");
        customtool_phone.initViewsVisible(true,true,false,true);
        phnoe_edittext = findViewById(R.id.phnoe_edittext);
        customtool_phone.setOnRightTitleClickListener(new CustomTool.OnRightTitleClickListener() {
            @Override
            public void onRightTitleClick(View v) {
                if (TextUtils.isEmpty(phnoe_edittext.getText().toString())){
                    Toast.makeText(PhoneActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = getIntent();
                    intent.putExtra("phone",phnoe_edittext.getText().toString());
                    setResult(1,intent);
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
