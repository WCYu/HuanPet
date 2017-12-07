package com.example.huanpet.view.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.huanpet.MainActivity;
import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;

public class UserActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout Head_portrait;
    private LinearLayout Name;
    private LinearLayout Gender;
    private LinearLayout Date_of_birth;
    private LinearLayout Mobile_phone;
    private LinearLayout WeChat;
    private LinearLayout QQ;
    private LinearLayout Contact_address;
    @Override
    public int initLayoutID() {
        return R.layout.activity_user;
    }

    @Override
    public void initView() {
        Head_portrait = (LinearLayout) findViewById(R.id.Head_portrait);
        Name = (LinearLayout) findViewById(R.id.Name);
        Gender = (LinearLayout) findViewById(R.id.Gender);
        Date_of_birth = (LinearLayout) findViewById(R.id.Date_of_birth);
        Mobile_phone = (LinearLayout) findViewById(R.id.Mobile_phone);
        WeChat = (LinearLayout) findViewById(R.id.WeChat);
        QQ = (LinearLayout) findViewById(R.id.QQ);
        Contact_address = (LinearLayout) findViewById(R.id.Contact_address);

        Head_portrait.setOnClickListener(this);
        Name.setOnClickListener(this);
        Gender.setOnClickListener(this);
        Date_of_birth.setOnClickListener(this);
        Mobile_phone.setOnClickListener(this);
        WeChat.setOnClickListener(this);
        QQ.setOnClickListener(this);
        Contact_address.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Head_portrait:
                options();
                break;


            case R.id.Name:

                break;


            case R.id.Gender:

                break;


            case R.id.Date_of_birth:

                break;


            case R.id.Mobile_phone:

                break;


            case R.id.WeChat:

                break;


            case R.id.QQ:

                break;


            case R.id.Contact_address:

                break;


        }
    }

    private void options() {
//        ActionSheetDialog mDialog = new ActionSheetDialog(this).builder();  
//        mDialog.setTitle("选择");  
//        mDialog.setCancelable(false);  
//        mDialog.addSheetItem("拍照", SheetItemColor.Blue, new OnSheetItemClickListener() {  
//            @Override  
//            public void onClick(int which) {  
//                PhotoUtil.photograph(MainActivity.this);  
//            }  
//        }).addSheetItem("从相册选取", SheetItemColor.Blue, new OnSheetItemClickListener() {  
//            @Override  
//            public void onClick(int which) {  
//                PhotoUtil.selectPictureFromAlbum(MainActivity.this);  
//            }  
//        }).show(); 

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
