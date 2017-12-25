package com.example.huanpet.view.pet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.custom.EditTextWithDelete;
import com.example.huanpet.utils.OkhttpUtil;
import com.example.huanpet.utils.PreferencesUtil;
import com.example.huanpet.utils.util.AppUtils;
import com.example.huanpet.utils.util.CJSON;
import com.example.huanpet.utils.util.PetInfo;
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

public class WeightActivity extends BaseActivity {

    private EditTextWithDelete clearEditText;
    private TextView wc;
    private EditTextWithDelete searchEditText;
    private TextView button_clear;
    private ImageView fh;


    @Override
    public int initLayoutID() {
        return R.layout.activity_weight;
    }

    public void initView() {
        wc = (TextView) findViewById(R.id.wc);
        searchEditText = (EditTextWithDelete) findViewById(R.id.searchEditText);
        button_clear = (TextView) findViewById(R.id.button_clear);
        clearEditText = findViewById(R.id.searchEditText);
        fh = (ImageView) findViewById(R.id.fh);
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        wc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(searchEditText.getText().toString())) {
                    Toast.makeText(WeightActivity.this, "体重不能为空", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent=new Intent();
                    String name=searchEditText.getText().toString().trim();
                    intent.putExtra("name",name);
                    setResult(RESULT_OK,intent);
                    finish();
                    //UpdateName();

                    // TODO validate success, do something


                }
            }

            private void UpdateName() {
                final String nameString = clearEditText.getText().toString();
                Map<String, Object> param = new HashMap<>();
                String userId = PreferencesUtil.getInstance().getUserId();
//        String userId = AppUtils.userInfo.getUserId();
                param.put(TableUtils.PetInfo.USERID, userId);
                param.put(TableUtils.PetInfo.PETWEIGHT, nameString);
                // 生成提交服务器的JSON字符串
                String json = CJSON.toJSONMap(param);
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("data", json);
                Log.e("Model----111111------", json);
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .post(builder.build())
                        .url("http://123.56.150.230:8885/dog_family/petInfo/savePetInfo.jhtml")
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
                                PreferencesUtil.getInstance().setPetWeight(nameString);

                                finish();
                            }
                        });
                    }
                });
            }


        });
        fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });
    }

    @Override
    public void setMyAppTitle() {

    }
}
