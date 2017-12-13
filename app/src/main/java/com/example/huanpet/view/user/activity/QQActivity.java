package com.example.huanpet.view.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;

public class QQActivity extends BaseActivity {

    private CustomTool customtool_QQ;
    private EditText QQ_edittext;
    @Override
    public int initLayoutID() {
        return R.layout.activity_qq;
    }

    @Override
    public void initView() {
        customtool_QQ = findViewById(R.id.customtool_QQ);
        customtool_QQ.setOnLeftButtonClickListener(new CustomTool.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View v) {
                Intent intentname = new Intent(QQActivity.this,UserActivity.class);
                startActivity(intentname);
            }
        });
        customtool_QQ.setAppTitle("QQ");
        customtool_QQ.setRightTitle("提交");
        customtool_QQ.initViewsVisible(true,true,false,true);
        QQ_edittext = findViewById(R.id.QQ_edittext);
        customtool_QQ.setOnRightTitleClickListener(new CustomTool.OnRightTitleClickListener() {
            @Override
            public void onRightTitleClick(View v) {
                if (TextUtils.isEmpty(QQ_edittext.getText().toString())){
                    Toast.makeText(QQActivity.this, "QQ号不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = getIntent();
                    intent.putExtra("QQ",QQ_edittext.getText().toString());
                    setResult(3,intent);
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
}
