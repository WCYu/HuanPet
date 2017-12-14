package com.example.huanpet.view.user.SignIn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huanpet.R;
import com.example.huanpet.app.AppService;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.entity.RegisterBean;
import com.example.huanpet.utils.OkhttpUtil;
import com.example.huanpet.utils.PreferencesUtil;
import com.example.huanpet.utils.util.CJSON;
import com.example.huanpet.view.main.view.HomeActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

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
        btn_find_pwd.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        main_login_qq.setOnClickListener(this);
        main_login_wachat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.header_titles:
                Intent intentheader = new Intent(SignInActivity.this,RegisterActivity.class);
                startActivity(intentheader);
                break;
            case R.id.btn_login:
                HashMap map=new HashMap();
                map.put("userPhone",edit_phone.getText().toString());
                map.put("password",edit_pwd.getText().toString());
                FormBody.Builder builder = new FormBody.Builder();
                String josn = CJSON.toJSONMap(map);
                builder.add("data",josn);

                OkhttpUtil.getInstance().postJson("http://123.56.150.230:8885/dog_family/" + "user/login.jhtml", builder.build(), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String string = response.body().string();
                        AppService.baseActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    JSONObject jsonObject=new JSONObject(string);
                                   boolean ret = (boolean) jsonObject.get("ret");
                                   if(ret){
                                       JSONObject result = jsonObject.getJSONObject("result");
                                       String userId = (String) result.get("userId");
                                       PreferencesUtil instance = PreferencesUtil.getInstance();
                                              instance .setUserId(userId);
                                       Log.e("Tat------111111-------",userId);
                                       Log.e("Tat------222222-------",instance.getUserId());
                                       Intent in=new Intent(SignInActivity.this,HomeActivity.class);
                                       startActivity(in);
                                       finish();
                                   }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                });
                Toast.makeText(this, "登陆", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_find_pwd:
                Toast.makeText(this, "忘记密码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_login_qq:
                Toast.makeText(this, "QQ", Toast.LENGTH_SHORT).show();

                break;
            case R.id.main_login_wachat:
                Toast.makeText(this, "微信", Toast.LENGTH_SHORT).show();

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
