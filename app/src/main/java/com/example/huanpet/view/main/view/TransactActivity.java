package com.example.huanpet.view.main.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;

public class TransactActivity extends BaseActivity {

    private CustomTool transact_custom;
    private TextView transact_orderid;
    private TextView transact_ordertime;
    private TextView transact_ordertmoney;

    @Override
    public int initLayoutID() {
        return R.layout.activity_transact;
    }

    public void initView() {
        transact_custom = (CustomTool) findViewById(R.id.transact_custom);
        transact_orderid = (TextView) findViewById(R.id.transact_orderid);
        transact_ordertime = (TextView) findViewById(R.id.transact_ordertime);
        transact_ordertmoney = (TextView) findViewById(R.id.transact_ordertmoney);
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
        transact_custom.setRightIcon(R.drawable.map);
        transact_custom.setReturnBtn("");
        transact_custom.setAppTitle("订单提交成功");
        transact_custom.initViewsVisible(true, true, false, false);
        transact_custom.setOnLeftButtonClickListener(new CustomTool.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View v) {
                finish();
            }
        });
        transact_custom.setOnRightImgClickLisrener(new CustomTool.OnRightImgClickListener() {
            @Override
            public void onRightImgClick(View v) {

            }
        });
    }
}
