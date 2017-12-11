package com.example.huanpet.view.know;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;
import com.example.huanpet.utils.OkhttpUtil;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class KnowActivity extends BaseActivity {

    private CustomTool know_custom;
    private TextView tx;

    @Override
    public int initLayoutID() {
        return R.layout.activity_know;
    }

    @Override
    public void initView() {
        know_custom = findViewById(R.id.know_custom);
        tx = findViewById(R.id.tx);
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {
        know_custom.initViewsVisible(true, true, false, false);
        know_custom.setAppTitle("寄养须知");
        know_custom.setReturnBtn("  ");
    }

    @Override
    public void initListener() {
        know_custom.setOnLeftButtonClickListener(new CustomTool.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View v) {
                finish();
            }
        });
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> stringStringHashMap = new HashMap<>();
                stringStringHashMap.put("sign","7dd7276f7a7b1b657e0088707b92ca08");
                stringStringHashMap.put("ip","172.20.10.3");
                stringStringHashMap.put("token","7dd7276f7a7b1b657e0088707b92ca08");
                stringStringHashMap.put("channel","android");
                RequestBody requestBody = new RequestBody() {
                    @Override
                    public MediaType contentType() {
                        return null;
                    }

                    @Override
                    public void writeTo(BufferedSink sink) throws IOException {

                    }
                };
                OkhttpUtil.getInstance().post("http://123.56.150.230:8885/dog_family/", stringStringHashMap,requestBody, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Toast.makeText(KnowActivity.this, response.body().string()+"", Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    }
                });
            }
        });
    }

    @Override
    public void setMyAppTitle() {

    }

}
