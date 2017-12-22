package com.example.huanpet.view.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;

import java.util.Date;

/*
* 订单预约成功
* */
public class TransactActivity extends BaseActivity {

    private CustomTool transact_custom;
    private TextView transact_orderid;
    private TextView transact_ordertime;
    private TextView transact_ordertmoney;
    private String desc;
    private Button transact_contact;
    private Button transact_finish;

    @Override
    public int initLayoutID() {
        return R.layout.activity_transact;
    }

    public void initView() {
        transact_custom = (CustomTool) findViewById(R.id.transact_custom);
        transact_orderid = (TextView) findViewById(R.id.transact_orderid);
        transact_ordertime = (TextView) findViewById(R.id.transact_ordertime);
        transact_ordertmoney = (TextView) findViewById(R.id.transact_ordertmoney);
        transact_contact=findViewById(R.id.transact_contact);
        transact_finish=findViewById(R.id.transact_finish);
        Intent intent = getIntent();
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        String string = date.toString();
        transact_ordertime.setText(string);
        desc = intent.getStringExtra("desc");
        transact_orderid.setText(desc);
        int money = intent.getIntExtra("money", 0);
        transact_ordertmoney.setText(money + ".00");
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        transact_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TransactActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
