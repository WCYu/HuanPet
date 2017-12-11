package com.example.huanpet.view.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;

public class ModifyTheNameActivity extends BaseActivity {


    private CustomTool customtool_name;
    private EditText customtool_edittext;

    @Override
    public int initLayoutID() {
        return R.layout.activity_modify_the_name;

    }

    @Override
    public void initView() {
        customtool_name = findViewById(R.id.customtool_name);
        customtool_name.setOnLeftButtonClickListener(new CustomTool.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View v) {
                Intent intentname = new Intent(ModifyTheNameActivity.this,UserActivity.class);
                startActivity(intentname);
            }
        });
        customtool_name.setAppTitle("名称");
        customtool_name.setRightTitle("提交");
        customtool_name.initViewsVisible(true,true,false,true);
        customtool_edittext = findViewById(R.id.customtool_edittext);
        customtool_name.setOnRightTitleClickListener(new CustomTool.OnRightTitleClickListener() {
            @Override
            public void onRightTitleClick(View v) {
                if (TextUtils.isEmpty(customtool_edittext.getText().toString())){
                    Toast.makeText(ModifyTheNameActivity.this, "名称不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = getIntent();
                    intent.putExtra("name",customtool_edittext.getText().toString());
                    setResult(0,intent);
                    finish();
                }

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }
}
