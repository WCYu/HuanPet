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

public class ContactAddressActivity extends BaseActivity {
    private CustomTool customtool_city;
    private EditText city_edittext;

    @Override
    public int initLayoutID() {
        return R.layout.activity_contact_address;
    }

    @Override
    public void initView() {
        customtool_city = findViewById(R.id.customtool_city);
        customtool_city.setOnLeftButtonClickListener(new CustomTool.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View v) {
                Intent intentname = new Intent(ContactAddressActivity.this,UserActivity.class);
                startActivity(intentname);
            }
        });
        customtool_city.setAppTitle("QQ");
        customtool_city.setRightTitle("提交");
        customtool_city.initViewsVisible(true,true,false,true);
        city_edittext = findViewById(R.id.city_edittext);
        customtool_city.setOnRightTitleClickListener(new CustomTool.OnRightTitleClickListener() {
            @Override
            public void onRightTitleClick(View v) {
                if (TextUtils.isEmpty(city_edittext.getText().toString())){
                    Toast.makeText(ContactAddressActivity.this, "QQ号不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = getIntent();
                    intent.putExtra("contactaddress",city_edittext.getText().toString());
                    setResult(4,intent);
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
