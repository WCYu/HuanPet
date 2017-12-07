package com.example.huanpet.view.news;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;

public class NewsActivity extends BaseActivity{

    private CustomTool news_custom;
    private ListView news_lv;

    @Override
    public int initLayoutID() {
        return R.layout.activity_news;
    }

    @Override
    public void initView() {
        news_custom = findViewById(R.id.news_custom);
        news_lv = findViewById(R.id.news_lv);
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {
        news_custom.initViewsVisible(true,true,false,false);
        news_custom.setAppTitle("消息");
        news_custom.setReturnBtn(" ");
    }

    @Override
    public void initListener() {
        news_custom.setOnLeftButtonClickListener(new CustomTool.OnLeftButtonClickListener() {
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
