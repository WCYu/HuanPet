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
    }

    @Override
    public void setMyAppTitle() {

    }

}
