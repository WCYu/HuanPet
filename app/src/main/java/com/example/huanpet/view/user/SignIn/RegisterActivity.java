package com.example.huanpet.view.user.SignIn;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.huanpet.R;
import com.example.huanpet.app.AppService;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.entity.RegisterBean;
import com.example.huanpet.utils.OkhttpUtil;
import com.example.huanpet.utils.util.CJSON;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private Button back_header;
    private Button header_title;
    private EditText edit_phone;
    private Button btn_get_code;
    private EditText edit_code;
    private EditText edit_username;
    private EditText edit_password;
    private ImageButton btn_register;
    private ImageView main_login_qq;
    private ImageView main_login_wachat;
    private LinearLayout container;

    @Override
    public int initLayoutID() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        back_header = (Button) findViewById(R.id.back_header);
        header_title = (Button) findViewById(R.id.header_title);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        btn_get_code = (Button) findViewById(R.id.btn_get_code);
        edit_code = (EditText) findViewById(R.id.edit_code);
        edit_username = (EditText) findViewById(R.id.edit_username);
        edit_password = (EditText) findViewById(R.id.edit_password);
        btn_register = (ImageButton) findViewById(R.id.btn_register);
        main_login_qq = (ImageView) findViewById(R.id.main_login_qq);
        main_login_wachat = (ImageView) findViewById(R.id.main_login_wachat);
        container = (LinearLayout) findViewById(R.id.container);

        back_header.setOnClickListener(this);
        header_title.setOnClickListener(this);
        btn_get_code.setOnClickListener(this);
        main_login_qq.setOnClickListener(this);
        main_login_wachat.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
            switch (v.getId()){
                //登陆
                case R.id.header_title:
                    Intent intentheader = new Intent(RegisterActivity.this,SignInActivity.class);
                    startActivity(intentheader);
                    break;
                    //取消
                case R.id.back_header:
                    Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                    //获取验证码
                case R.id.btn_get_code:
                    Toast.makeText(this, "获取验证码", Toast.LENGTH_SHORT).show();
                    break;
                    //QQ登陆
                case R.id.main_login_qq:
                    Toast.makeText(this, "QQ登陆", Toast.LENGTH_SHORT).show();
                    break;
                    //微信登陆
                case R.id.main_login_wachat:
                    Toast.makeText(this, "微信登陆", Toast.LENGTH_SHORT).show();
                    break;
                    //注册
                case R.id.btn_register:
                    HashMap map=new HashMap();
                    map.put("userPhone",edit_phone.getText().toString());
                    map.put("userName",edit_username.getText().toString());
                    map.put("password",edit_password.getText().toString());
                    FormBody.Builder builder = new FormBody.Builder();
                    String josn = CJSON.toJSONMap(map);
                    builder.add("data",josn);

                    OkhttpUtil.getInstance().postJson("http://123.56.150.230:8885/dog_family/" + "user/register.jhtml", builder.build(), new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String string = response.body().string();
                            AppService.baseActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.e("TAAG-------",string);
                                    Gson gs=new Gson();
                                    RegisterBean registerBean = gs.fromJson(string, RegisterBean.class);
                                    RegisterBean.ResultBean result = registerBean.getResult();
                                    boolean ret=registerBean.isRet();
                                    if(ret){
                                        finish();
                                    }else {
                                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });
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
