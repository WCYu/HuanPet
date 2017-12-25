package com.example.huanpet.view.pet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huanpet.R;
import com.example.huanpet.app.AppService;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.PreferencesUtil;
import com.example.huanpet.utils.util.CJSON;
import com.example.huanpet.utils.util.TableUtils;
import com.example.huanpet.view.pet.adapter.ImmuneInfo;
import com.example.huanpet.view.user.activity.UserActivity;
import com.example.huanpet.view.user.adpter.NumericWheelAdapter;
import com.example.huanpet.view.user.widget.WheelView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cn.qqtheme.framework.picker.DatePicker;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ImmuneActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout App;
    private Drawable leftDrawable;
    private Button button1,button2,button3,getButton3,button4;
    private List<Imm> valueslist = null;
    private List<Imm> datalist = null;
    private List<ImmuneInfo> jiulist=null;
    private String path;
    private TextView time;
    private RelativeLayout mTv_time;
    private TextView immune_info;

    @Override
    public int initLayoutID() {
        return R.layout.activity_immune;
    }

    @Override
    public void initView() {
        Resources res = getResources();
        leftDrawable=res.getDrawable(R.drawable.imm_check);
        leftDrawable.setBounds(0, 0, leftDrawable.getMinimumWidth(),
                leftDrawable.getMinimumHeight());
        App= (RelativeLayout) findViewById(R.id.App_title);


        time= (TextView) findViewById(R.id.pet_mianyi_time_tv);
        mTv_time= (RelativeLayout) findViewById(R.id.pet_mianyi_time);
        mTv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker picker = new DatePicker(ImmuneActivity.this);
                picker.setRange(1990, 2030);//年份范围
                picker.setSubmitTextColor(Color.BLUE);
                picker.setCancelTextColor(Color.BLUE);
                picker.setTextColor(Color.BLACK);
                picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        if(!year.isEmpty()||!month.isEmpty()||!day.isEmpty()){
                            Toast.makeText(ImmuneActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            time.setText(year+"年"+month+"月"+day+"日");
                        }else{
                            Toast.makeText(ImmuneActivity.this, "网络不佳,请稍后重试", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                picker.show();
            }
        });


        //初始化免疫病毒左边的图片

        button1= (Button) findViewById(R.id.pet_mianyi_bing1);
        button2= (Button) findViewById(R.id.pet_mianyi_bing2);
        button3= (Button) findViewById(R.id.pet_mianyi_bing3);
        button4= (Button) findViewById(R.id.pet_mianyi_bing4);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        if (getIntent().getStringExtra("petimmlist") != null) {
            String petimmlist = getIntent().getStringExtra("petimmlist");
            jiulist = CJSON.parseArray(petimmlist, ImmuneInfo.class);
        }
        //查询免疫信息
        querymianyiInfo();
        App.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {



            Intent intent=new Intent();
                if(time.getText().toString().isEmpty()){
                    Toast.makeText(ImmuneActivity.this, "请选择上次免疫时间", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(valueslist==null){
                    return;
                }
                for (Imm imm : valueslist) {
                    imm.setImmuneTime(time.getText().toString());
                }
                String list = CJSON.toJSONString(valueslist);
                intent.putExtra("list", list);
                Log.i("TAG", "=======1=======" + list);
                Toast.makeText(ImmuneActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
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

    }

    private void querymianyiInfo() {
        OkHttpClient okhttp=new OkHttpClient();
        Map<String,Object> map=new HashMap<>();
        map.put(TableUtils.ImmuneInfo.ISUSE,1+"");
        final String json = CJSON.toJSONMap(map);
        FormBody.Builder builder=new FormBody.Builder();
        builder.add(CJSON.DATA, json);
        Request request=new Request.Builder().url(CJSON.URL_STRING+"immuneInfo/getImmuneInfos.jhtml").post(builder.build()).build();
        okhttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String str=response.body().string();
                if(CJSON.getRET(str)) {
                    String desc = CJSON.getDESC(str);
                    datalist = CJSON.parseArray(desc, Imm.class);
                }
                AppService.baseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        button1.setText(datalist.get(0).getImmuneName());
                        button2.setText(datalist.get(1).getImmuneName());
                        button3.setText(datalist.get(2).getImmuneName());
                        button4.setText(datalist.get(3).getImmuneName());
                        if(jiulist!=null){
                            for(int i=0;i<jiulist.size();i++){
                                for(int y=0;y<datalist.size();y++){
                                    if(jiulist.get(i).getImmuneCode().equals(datalist.get(y).getImmuneCode())){

                                        switch (y){
                                            case 0:
                                                button1.setCompoundDrawables(leftDrawable, null, null,
                                                        null);
                                                button1.setOnClickListener(obclick);
                                                break;
                                            case 1:
                                                button2.setCompoundDrawables(leftDrawable, null, null,
                                                        null);
                                                button2.setOnClickListener(obclick);
                                                break;
                                            case 2:
                                                button3.setCompoundDrawables(leftDrawable, null, null,
                                                        null);
                                                button3.setOnClickListener(obclick);
                                                break;
                                            case 3:

                                                button4.setCompoundDrawables(leftDrawable, null, null,
                                                        null);
                                                button4.setOnClickListener(obclick);
                                                break;
                                        }
                                    }
                                    else {
                                        Toast.makeText(ImmuneActivity.this, "没有免疫信息", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                        }


                    }
                });
            }
        });
    }


    public  void initData(String s) {

    }



    @Override
    public void onClick(View view) {
        if (valueslist == null) {
            valueslist = new ArrayList<>();
        }
        Imm i = null;

        switch (view.getId()){
            case R.id.pet_mianyi_bing1:
                button1.setCompoundDrawables(leftDrawable, null, null,
                        null);
                for (Imm imm : valueslist) {
                    if (imm.getImmuneCode() == datalist.get(0)
                            .getImmuneCode()) {
                        i = imm;
                    }
                }
                if (i == null) {
                    i = new Imm();
                    i.setImmuneCode(datalist.get(0).getImmuneCode());
                    i.setImmuneName(datalist.get(0).getImmuneName());
                }
                break;
            case R.id.pet_mianyi_bing2:
                button2.setCompoundDrawables(leftDrawable, null, null,
                        null);
                for (Imm imm : valueslist) {
                    if (imm.getImmuneCode() == datalist.get(1)
                            .getImmuneCode()) {
                        i = imm;

                    }
                }
                if (i == null) {
                    i = new Imm();
                    i.setImmuneCode(datalist.get(1).getImmuneCode());

                    i.setImmuneName(datalist.get(1).getImmuneName());
                }
                break;
            case R.id.pet_mianyi_bing3:
                button3.setCompoundDrawables(leftDrawable, null, null,
                        null);
                for (Imm imm : valueslist) {
                    if (imm.getImmuneCode() == datalist.get(2)
                            .getImmuneCode()) {
                        i = imm;
                    }
                }
                if (i == null) {
                    i = new Imm();
                    i.setImmuneCode(datalist.get(2).getImmuneCode());
                    i.setImmuneName(datalist.get(2).getImmuneName());
                }
                break;
            case R.id.pet_mianyi_bing4:
                button4.setCompoundDrawables(leftDrawable, null, null,
                        null);
                for (Imm imm : valueslist) {
                    if (imm.getImmuneCode() == datalist.get(3)
                            .getImmuneCode()) {
                        i = imm;

                    }
                }
                if (i == null) {
                    i = new Imm();
                    i.setImmuneCode(datalist.get(3).getImmuneCode());
                    i.setImmuneName(datalist.get(3).getImmuneName());
                }
                break;

        }
        valueslist.add(i);
    }
    View.OnClickListener obclick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(ImmuneActivity.this, "图片只能上传一次", Toast.LENGTH_SHORT).show();
        }
    };
}
