package com.example.huanpet.view.set;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;

public class SetActivity extends BaseActivity implements View.OnClickListener {

    private CustomTool set_custom;
    private RelativeLayout jilu;
    private RelativeLayout clear;
    private RelativeLayout pingfen;
    private RelativeLayout guanyu;
    private TextView version;
    private RelativeLayout sycnc;
    private TextView textView;
    private CheckBox box;

    @Override
    public int initLayoutID() {
        return R.layout.activity_set;
    }

    @Override
    public void initView() {
        textView = findViewById(R.id.textView1);
        set_custom = (CustomTool) findViewById(R.id.set_custom);
        jilu = (RelativeLayout) findViewById(R.id.jilu);
        clear = (RelativeLayout) findViewById(R.id.clear);
        pingfen = (RelativeLayout) findViewById(R.id.pingfen);
        guanyu = (RelativeLayout) findViewById(R.id.guanyu);
        version = (TextView) findViewById(R.id.version);
        sycnc = (RelativeLayout) findViewById(R.id.sycnc);
        box = findViewById(R.id.box);
        box.setOnClickListener(this);
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {
        set_custom.initViewsVisible(true,true,false,false);
        set_custom.setAppTitle("设置");
        set_custom.setReturnBtn("   ");
    }

    @Override
    public void initListener() {
        set_custom.setOnLeftButtonClickListener(new CustomTool.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void setMyAppTitle() {

    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.box:

                          Log.i("ischecked","111111111");
                  box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                      @Override
                      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                          Log.i("ischecked",isChecked+"");
                          if (isChecked){
                              box.setBackgroundResource(R.drawable.on);
                          }else {
                              box.setBackgroundResource(R.drawable.off);
                          }
                      }
                  });
                    break;
            }
    }
}
