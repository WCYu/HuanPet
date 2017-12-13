package com.example.huanpet.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.huanpet.R;

/**
 * Created by Administrator on 2017/12/10.
 */

public class ClearEditText extends RelativeLayout{


    private Button button;
    private EditText editText;

    public ClearEditText(Context context) {
        super(context);
        initView();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    public void initView(){
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.edittext_can_clear,this);

        editText = contentView.findViewById(R.id.m_edit);

        button = findViewById(R.id.m_btn);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
            }
        });
    }

    public CharSequence getText(){
       return  editText.getText();
    }

    private void clearText(){
        editText.setText("");
    }

}
