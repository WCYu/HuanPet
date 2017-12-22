package com.example.huanpet.view.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;

import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;

/*
* 日历类
* */
public class DataActivity extends BaseActivity {

    private DatePicker date_data;

    @Override
    public int initLayoutID() {
        return R.layout.activity_data;
    }

    public void initView() {
        date_data = (DatePicker) findViewById(R.id.date_data);
        date_data.setDate(2017,12);
        date_data.setMode(DPMode.SINGLE);
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {
        date_data.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {
                Intent intent=new Intent(DataActivity.this,AppointmentActivity.class);
                intent.putExtra("data",date);
                setResult(0,intent);
                finish();
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void setMyAppTitle() {

    }
}
