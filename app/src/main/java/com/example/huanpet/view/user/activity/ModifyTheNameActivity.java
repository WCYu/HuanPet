package com.example.huanpet.view.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;
import com.example.huanpet.utils.OkhttpUtil;
import com.example.huanpet.utils.PreferencesUtil;
import com.example.huanpet.utils.util.AppUtils;
import com.example.huanpet.utils.util.CJSON;
import com.example.huanpet.utils.util.FileUtil;
import com.example.huanpet.utils.util.TableUtils;
import com.example.huanpet.utils.util.ToastUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ModifyTheNameActivity extends AppCompatActivity {


    private CustomTool customtool_name;
    private EditText customtool_edittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_the_name);
        initView();
        AppUtils.setAppContext(this);
    }

    public void initView() {

        customtool_name = findViewById(R.id.customtool_name);
        customtool_name.setOnLeftButtonClickListener(new CustomTool.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View v) {
                Intent intentname = new Intent(ModifyTheNameActivity.this,UserActivity.class);
                startActivity(intentname);
            }
        });
        customtool_name.setAppTitle("名称");
        customtool_name.setRightTitle("提交");
        customtool_name.initViewsVisible(true,true,false,true);
        customtool_edittext = findViewById(R.id.customtool_edittext);
        customtool_name.setOnRightTitleClickListener(new CustomTool.OnRightTitleClickListener() {
            @Override
            public void onRightTitleClick(View v) {
                if (TextUtils.isEmpty(customtool_edittext.getText().toString())){
                    Toast.makeText(ModifyTheNameActivity.this, "名称不能为空", Toast.LENGTH_SHORT).show();
                }else{
//                    Intent intent = getIntent();
//                    intent.putExtra("name",customtool_edittext.getText().toString());
//                    setResult(0,intent);
//                    finish();
                UpdateName();
                }

            }
        });
    }

    private void UpdateName() {
        final String nameString = customtool_edittext.getText().toString();
        Map<String, Object> param = new HashMap<>();
        String userId = PreferencesUtil.getInstance().getUserId();
//        String userId = AppUtils.userInfo.getUserId();
        param.put(TableUtils.UserInfo.USERID, userId);
        param.put(TableUtils.UserInfo.USERNAME,nameString);
        // 生成提交服务器的JSON字符串
        String json = CJSON.toJSONMap(param);
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("data",json);
        Log.e("Model----111111------",json);
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
                                Log.e("TAG",string);
                                PreferencesUtil.getInstance().setUserName(nameString);
                               finish();
                            }
                        });
                }
            });


    }

}
