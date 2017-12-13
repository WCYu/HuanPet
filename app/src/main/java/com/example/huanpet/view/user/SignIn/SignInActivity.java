package com.example.huanpet.view.user.SignIn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;

public class SignInActivity extends BaseActivity implements View.OnClickListener {

    private ImageView header_back;
    private Button header_titles;
    private EditText edit_phone;
    private EditText edit_pwd;
    private Button btn_find_pwd;
    private Button btn_login;
    private ImageView main_login_qq;
    private ImageView main_login_wachat;
    private TextView login_qq;


    @Override
    public int initLayoutID() {
        return R.layout.activity_sign_in;
    }

    @Override
    public void initView() {
        header_back = (ImageView) findViewById(R.id.header_back);
        header_titles = (Button) findViewById(R.id.header_titles);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        edit_pwd = (EditText) findViewById(R.id.edit_pwd);
        btn_find_pwd = (Button) findViewById(R.id.btn_find_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);
        main_login_qq = (ImageView) findViewById(R.id.main_login_qq);
        main_login_wachat = (ImageView) findViewById(R.id.main_login_wachat);
        login_qq = (TextView) findViewById(R.id.login_qq);

        header_titles.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.header_titles:
                Intent intentheader = new Intent(SignInActivity.this,RegisterActivity.class);
                startActivity(intentheader);
                break;
        }
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
