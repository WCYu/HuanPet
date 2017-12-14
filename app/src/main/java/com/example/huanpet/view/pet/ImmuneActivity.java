package com.example.huanpet.view.pet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huanpet.R;

import cn.qqtheme.framework.picker.DatePicker;

public class ImmuneActivity extends AppCompatActivity {

    private ImageView fh;
    private TextView wc;
    private RelativeLayout title_view;
    private CheckBox petImnue_img;
    private ImageView d;
    private TextView topTime;
    private RelativeLayout Relate_pet_topIMMNUE;
    private CheckBox virus_quanwen;
    private CheckBox xixiao;
    private CheckBox quanxian;
    private CheckBox kuangquan;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immune);
        initView();
    }

    private void initView() {
        fh = (ImageView) findViewById(R.id.fh);
        wc = (TextView) findViewById(R.id.wc);
        title_view = (RelativeLayout) findViewById(R.id.title_view);
        petImnue_img = (CheckBox) findViewById(R.id.petImnue_img);
        d = (ImageView) findViewById(R.id.d);
        topTime = (TextView) findViewById(R.id.topTime);
        Relate_pet_topIMMNUE = (RelativeLayout) findViewById(R.id.Relate_pet_topIMMNUE);
        virus_quanwen = (CheckBox) findViewById(R.id.virus_quanwen);
        xixiao = (CheckBox) findViewById(R.id.xixiao);
        quanxian = (CheckBox) findViewById(R.id.quanxian);
        kuangquan = (CheckBox) findViewById(R.id.kuangquan);
        Relate_pet_topIMMNUE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker picker = new DatePicker(ImmuneActivity.this);
                picker.setRange(1990, 2030);//年份范围
                picker.setSubmitTextColor(Color.BLUE);
                picker.setCancelTextColor(Color.BLUE);
                picker.setTextColor(Color.BLACK);
                picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        if (!year.isEmpty() || !month.isEmpty() || !day.isEmpty()) {
                            Toast.makeText(ImmuneActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            topTime.setText(year + "年" + month + "月" + day + "日");
                        } else {
                            Toast.makeText(ImmuneActivity.this, "网络不佳,请稍后重试", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                picker.show();
            }
        });
        fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ImmuneActivity.this,PetAddActivity.class);
                startActivity(in);
            }
        });
    }

}
