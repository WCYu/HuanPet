package com.example.huanpet.view.know;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;

public class KnowActivity extends BaseActivity {

    private CustomTool know_custom;

    @Override
    public int initLayoutID() {
        return R.layout.activity_know;
    }

    @Override
    public void initView() {
        know_custom = findViewById(R.id.know_custom);
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {
        know_custom.initViewsVisible(true,true,false,false);
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
