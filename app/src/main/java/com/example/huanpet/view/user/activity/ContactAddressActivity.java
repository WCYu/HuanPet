package com.example.huanpet.view.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;
import com.example.huanpet.utils.PreferencesUtil;
import com.example.huanpet.utils.util.AppUtils;
import com.example.huanpet.utils.util.CJSON;
import com.example.huanpet.utils.util.TableUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ContactAddressActivity extends BaseActivity {
    private CustomTool customtool_city;
    private EditText city_edittext;

    @Override
    public int initLayoutID() {
        return R.layout.activity_contact_address;
    }

    @Override
    public void initView() {
        AppUtils.setAppContext(this);
        customtool_city = findViewById(R.id.customtool_city);
        customtool_city.setOnLeftButtonClickListener(new CustomTool.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View v) {
                Intent intentname = new Intent(ContactAddressActivity.this,UserActivity.class);
                startActivity(intentname);
            }
        });
        customtool_city.setAppTitle("联系地址");
        customtool_city.setRightTitle("提交");
        customtool_city.initViewsVisible(true,true,false,true);
        city_edittext = findViewById(R.id.city_edittext);
        customtool_city.setOnRightTitleClickListener(new CustomTool.OnRightTitleClickListener() {
            @Override
            public void onRightTitleClick(View v) {
                if (TextUtils.isEmpty(city_edittext.getText().toString())){
                    Toast.makeText(ContactAddressActivity.this, "联系地址不能为空", Toast.LENGTH_SHORT).show();
                }else{
//                    Intent intent = getIntent();
//                    intent.putExtra("contactaddress",city_edittext.getText().toString());
//                    setResult(4,intent);
//                    finish();
                    address();
                }

            }
        });
    }

    private void address() {
        final String cityString = city_edittext.getText().toString();
        Map<String, Object> param = new HashMap<>();
        String userId = PreferencesUtil.getInstance().getAddress();
//        String userId = AppUtils.userInfo.getUserId();
        param.put(TableUtils.UserInfo.USERID, userId);
        param.put(TableUtils.UserInfo.USERNAME, cityString);
        // 生成提交服务器的JSON字符串
        String json = CJSON.toJSONMap(param);
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("data", json);
        Log.e("Model----111111------", json);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .post(builder.build())
                .url("http://123.56.150.230:8885/dog_family/user/updateUserInfo.jhtml")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("TAG", string);
                        PreferencesUtil.getInstance().setAddress(cityString);
                        finish();
                    }
                });
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
