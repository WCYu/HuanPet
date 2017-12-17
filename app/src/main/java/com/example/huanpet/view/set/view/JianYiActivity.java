package com.example.huanpet.view.set.view;


import android.text.Editable;
import android.text.TextWatcher;

import android.util.Log;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;

/**
 * Created by 解亚鑫 on 2017/12/14.
 */

public class JianYiActivity extends BaseActivity implements TextWatcher{
    private CustomTool jianyi_custom;
    private EditText jianyi_edit;
    private TextView jianyi_text_count;
    private int page = 1;
    private CharSequence temp;//监听前的文本
    private int editStart;//光标开始位置
    private int editEnd;//光标结束位置
    private final int charMaxNum = 250;

    @Override
    public int initLayoutID() {
        return R.layout.activity_jianyi;
    }

    @Override
    public void initView() {
        jianyi_custom = findViewById(R.id.jianyi_custom);
        jianyi_edit = findViewById(R.id.jianyi_edit);
        jianyi_text_count = findViewById(R.id.jianyi_text_count);
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {
        jianyi_custom.initViewsVisible(true,true,false,true);
        jianyi_custom.setAppTitle("产品建议");
        jianyi_custom.setRightTitle("发送");
        jianyi_custom.setReturnBtn(" ");


    }

    @Override
    public void initListener() {
        jianyi_custom.setOnLeftButtonClickListener(new CustomTool.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View v) {
                finish();
            }
        });
        jianyi_custom.setOnRightTitleClickListener(new CustomTool.OnRightTitleClickListener() {
            @Override
            public void onRightTitleClick(View v) {

            }
        });
    jianyi_edit.addTextChangedListener(this);
    }

    @Override
    public void setMyAppTitle() {

    }

//Edit监听
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        temp = s;
//        if (count!=0){
//            page = temp.length()+1;
//        }
//        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
    }
    //Edit监听
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        page=start;
        jianyi_text_count.setText(page+1+"/250");
//        if (page==250){
//            jianyi_text_count.setText(250+"/250");
//        }
//        jianyi_edit.setText("还能输入" + (charMaxNum - s.length()) + "字符");
        Log.i("count",s+"----"+start+"------"+before+"-------"+count);
    }
//Edit监听
    @Override
    public void afterTextChanged(Editable s) {
        editStart = jianyi_edit.getSelectionStart();
        editEnd = jianyi_edit.getSelectionEnd();
        if (temp.length() > charMaxNum) {
            Toast.makeText(getApplicationContext(), "你输入的字数已经超过了限制！", Toast.LENGTH_LONG).show();
            s.delete(editStart - 1, editEnd);
            int tempSelection = editStart;
            jianyi_edit.setText(s);
            Log.i("sssss", s.toString().trim());
            jianyi_edit.setSelection(tempSelection);
        }
    }
}
