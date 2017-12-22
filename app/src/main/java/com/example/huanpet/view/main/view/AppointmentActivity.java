package com.example.huanpet.view.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.entity.OrderItemInfo;
import com.example.huanpet.utils.CustomTool;
import com.example.huanpet.utils.PreferencesUtil;
import com.example.huanpet.utils.UrlPath;
import com.example.huanpet.utils.util.AppUtils;
import com.example.huanpet.utils.util.CJSON;
import com.example.huanpet.view.main.presenter.HomePresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
/*
* 预约寄养
* */
public class AppointmentActivity extends BaseActivity implements View.OnClickListener, IHomeView {

    private CustomTool appointment_custom;
    private TextView appointment_day;
    private TextView appointment_day_yuan;
    private ImageView appointment_xi_add;
    private TextView appointment_xicode;
    private ImageView appointment_xi_jian;
    private TextView appointment_xi_gold;
    private ImageView appointment_jie_add;
    private TextView appointment_jie_code;
    private ImageView appointment_jie_jian;
    private TextView appointment_jie_gold;
    private TextView appointment_heji_gold;
    private LinearLayout appointment_addpet;
    private EditText comment_et;
    private TextView appointment_zong_gold;
    private Button appointment_yuyue;

    private int xizao = 0;
    private int jiesong = 0;
    private int day = 0;
    private int zong;
    private LinearLayout start_date;
    private LinearLayout end_date;
    private TextView start_tv;
    private TextView end_tv;
    private TextView appointment_date;
    private String careId;
    private String family;

    @Override
    public int initLayoutID() {
        return R.layout.activity_appointment;
    }

    public void initView() {
        appointment_custom = (CustomTool) findViewById(R.id.appointment_custom);
        appointment_day = (TextView) findViewById(R.id.appointment_day);
        appointment_day_yuan = (TextView) findViewById(R.id.appointment_day_yuan);
        appointment_xi_add = (ImageView) findViewById(R.id.appointment_xi_add);
        appointment_xicode = (TextView) findViewById(R.id.appointment_xicode);
        appointment_xi_jian = (ImageView) findViewById(R.id.appointment_xi_jian);
        appointment_xi_gold = (TextView) findViewById(R.id.appointment_xi_gold);
        appointment_jie_add = (ImageView) findViewById(R.id.appointment_jie_add);
        appointment_jie_code = (TextView) findViewById(R.id.appointment_jie_code);
        appointment_jie_jian = (ImageView) findViewById(R.id.appointment_jie_jian);
        appointment_jie_gold = (TextView) findViewById(R.id.appointment_jie_gold);
        appointment_heji_gold = (TextView) findViewById(R.id.appointment_heji_gold);
        appointment_addpet = (LinearLayout) findViewById(R.id.appointment_addpet);
        comment_et = (EditText) findViewById(R.id.comment_et);
        appointment_zong_gold = (TextView) findViewById(R.id.appointment_zong_gold);
        appointment_yuyue = (Button) findViewById(R.id.appointment_yuyue);
        start_date = findViewById(R.id.start_date);
        end_date = findViewById(R.id.end_date);
        start_tv = findViewById(R.id.start_tv);
        end_tv = findViewById(R.id.end_tv);
        appointment_date = findViewById(R.id.appointment_date);

        Intent intent = getIntent();
        careId = intent.getStringExtra("userId");
        family = intent.getStringExtra("family");

        start_date.setOnClickListener(this);
        end_date.setOnClickListener(this);
        appointment_yuyue.setOnClickListener(this);
        appointment_xi_add.setOnClickListener(this);
        appointment_xi_jian.setOnClickListener(this);
        appointment_jie_add.setOnClickListener(this);
        appointment_jie_jian.setOnClickListener(this);
        appointment_addpet.setOnClickListener(this);
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
        appointment_custom.setRightIcon(R.drawable.map);
        appointment_custom.setReturnBtn("");
        appointment_custom.setAppTitle("预约寄养");
        appointment_custom.initViewsVisible(true, true, false, false);
        appointment_custom.setOnLeftButtonClickListener(new CustomTool.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View v) {
                finish();
            }
        });
        appointment_custom.setOnRightImgClickLisrener(new CustomTool.OnRightImgClickListener() {
            @Override
            public void onRightImgClick(View v) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appointment_yuyue:
                ArrayList list = new ArrayList();
                for (int i = 0; i < 3; i++) {
                    OrderItemInfo orderinfo1 = new OrderItemInfo();
                    orderinfo1.setIsService(1);
                    orderinfo1.setPetDuration(day);
//                orderinfo1.setPetId();
//                orderinfo1.setPetImage();
//                orderinfo1.setPetName();
//                orderinfo1.setPetPricingCode();
//                orderinfo1.setPetPricingPrice();
//                orderinfo1.setPrice(new BigDecimal());
//                orderinfo1.setServiceCode();
//                orderinfo1.setServiceCount(1 + "");
//                orderinfo1.setServiceName();
//                orderinfo1.setServicePrice(new BigDecimal());
                    list.add(orderinfo1);
                }
                HashMap param = new HashMap();
                param.put("orderAmount", zong);
                param.put("paidUpAmount", zong);
                param.put("receivableAmount", zong);
                param.put("serviceBeginTime", start_tv.getText().toString());
                param.put("serviceEndTime", end_tv.getText().toString());
                param.put("userId", PreferencesUtil.getInstance().getUserId());
                param.put("userName", PreferencesUtil.getInstance().getUserName());
                param.put("userWord", comment_et.getText().toString());
                param.put("usersName", family);
                param.put("usersId", careId);
                param.put("orderItemInfoVOs", list);
                new HomePresenter(AppointmentActivity.this).getData((UrlPath.TOTALPATH + UrlPath.JIYANGPATH), param, 0);
                //                Intent in = new Intent(AppointmentActivity.this, TransactActivity.class);
//                startActivity(in);
                break;
            case R.id.appointment_xi_add:
                if (xizao >= 0 && xizao < 3) {
                    xizao++;
                }
                break;
            case R.id.appointment_xi_jian:
                if (xizao > 0 && xizao < 4) {
                    xizao--;
                }
                break;
            case R.id.appointment_jie_add:
                if (jiesong >= 0 && jiesong < 3) {
                    jiesong++;
                }
                break;
            case R.id.appointment_jie_jian:
                if (jiesong > 0 && jiesong < 4) {
                    jiesong--;
                }
                break;
            case R.id.appointment_addpet:

                break;
            case R.id.start_date:
                Intent intent = new Intent(AppointmentActivity.this, DataActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.end_date:
                Intent intentEnd = new Intent(AppointmentActivity.this, DataActivity.class);
                startActivityForResult(intentEnd, 1);
                break;
        }
        setComputations();
    }

    private void setComputations() {
        appointment_day.setText(day + "天");
        appointment_date.setText(day + "晚");
        appointment_day_yuan.setText(30 * day + "元");
        appointment_xi_gold.setText(xizao * 60 + "元");
        appointment_jie_gold.setText(jiesong * 50 + "元");
        appointment_xicode.setText(xizao + "");
        appointment_jie_code.setText(jiesong + "");
        appointment_heji_gold.setText((xizao * 60) + (jiesong * 50) + (day * 30) + "元");
        zong = (xizao * 60) + (jiesong * 50) + (day * 30);
        appointment_zong_gold.setText("￥" + zong);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            String extra = data.getStringExtra("data");
            if (requestCode == 0 && resultCode == 0) {
                start_tv.setText(extra);
            } else if (requestCode == 1 && resultCode == 0) {
                end_tv.setText(extra);
                String string = start_tv.getText().toString();
                String[] split = string.split("-");
                String[] split1 = extra.split("-");
                int start = Integer.parseInt(split[2]);
                int end = Integer.parseInt(split1[2]);
                int date = end - start;
                day = date;
                setComputations();
            }
        }
    }

    private void submit() {
        String et = comment_et.getText().toString().trim();
        if (TextUtils.isEmpty(et)) {
            Toast.makeText(this, "和他/她说点儿什么吧", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    public void getData(String data, int i) {
        Log.e("----Appointment----", data);
        try {
            JSONObject jsonObject = new JSONObject(data);
            boolean ret = (boolean) jsonObject.get("ret");
            if (ret) {
                String desc = (String) jsonObject.get("desc");
                Intent in = new Intent(AppointmentActivity.this, TransactActivity.class);
                in.putExtra("desc",desc);
                in.putExtra("money",zong);
                startActivity(in);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
