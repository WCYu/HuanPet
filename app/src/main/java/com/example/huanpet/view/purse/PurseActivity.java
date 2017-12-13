package com.example.huanpet.view.purse;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;

public class PurseActivity extends BaseActivity {
    private CustomTool purse_custom;
    private TextView money;
    private TextView chongzhi;
    private TextView tixian;
    private RelativeLayout myzhifubao;
    private RelativeLayout myyouhuiquan;
    private RelativeLayout myjifen;


    @Override
    public int initLayoutID() {
        return R.layout.activity_purse;
    }

    @Override
    public void initView() {
        purse_custom = (CustomTool) findViewById(R.id.purse_custom);
        money = (TextView) findViewById(R.id.money);
        chongzhi = (TextView) findViewById(R.id.chongzhi);
        tixian = (TextView) findViewById(R.id.tixian);
        myzhifubao = (RelativeLayout) findViewById(R.id.myzhifubao);
        myyouhuiquan = (RelativeLayout) findViewById(R.id.myyouhuiquan);
        myjifen = (RelativeLayout) findViewById(R.id.myjifen);
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {
        purse_custom.initViewsVisible(true,true,false,true);
        purse_custom.setReturnBtn("   ");
        purse_custom.setAppTitle("我的钱包");
        purse_custom.setRightTitle("交易记录");
    }

    @Override
    public void initListener() {
        purse_custom.setOnLeftButtonClickListener(new CustomTool.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View v) {
                finish();
            }
        });
        myjifen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PurseActivity.this,MyJifenActivity.class));
            }
        });
        purse_custom.setOnRightImgClickLisrener(new CustomTool.OnRightImgClickListener() {
            @Override
            public void onRightImgClick(View v) {

            }
        });
    }

    @Override
    public void setMyAppTitle() {

    }
}
