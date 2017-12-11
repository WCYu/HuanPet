package com.example.huanpet.view.purse;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;

/**
 * Created by 解亚鑫 on 2017/12/8.
 */

public class MyJifenActivity extends BaseActivity {
    private CustomTool jifen_custom;
    private TextView login;
    private RelativeLayout everyday;
    private TextView fabiao_tx;
    private RelativeLayout fabiao;
    private TextView haoping_tx;
    private RelativeLayout haoping;

    @Override
    public int initLayoutID() {
        return R.layout.activity_myjifen;
    }

    @Override
    public void initView() {
        jifen_custom = findViewById(R.id.jifen_custom);
        login = findViewById(R.id.login);
        everyday = findViewById(R.id.everyday);
        fabiao_tx = findViewById(R.id.fabiao_tx);
        fabiao = findViewById(R.id.fabiao);
        haoping_tx = findViewById(R.id.haoping_tx);
        haoping = findViewById(R.id.haoping);
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {
        jifen_custom.initViewsVisible(true,true,false,true);
        jifen_custom.setRightTitle("确定");
        jifen_custom.setAppTitle("积分任务");
        jifen_custom.setReturnBtn("  ");
    }

    @Override
    public void initListener() {
        jifen_custom.setOnLeftButtonClickListener(new CustomTool.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View v) {
                finish();
            }
        });
        jifen_custom.setOnRightImgClickLisrener(new CustomTool.OnRightImgClickListener() {
            @Override
            public void onRightImgClick(View v) {

            }
        });
    }

    @Override
    public void setMyAppTitle() {

    }

}
