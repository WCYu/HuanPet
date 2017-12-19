package com.example.huanpet.view.main.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.custom.PercentLinearLayout;
import com.example.huanpet.utils.UrlPath;
import com.example.huanpet.view.main.presenter.HomePresenter;

import java.util.HashMap;
import java.util.Map;

public class MerchantActivity extends BaseActivity implements View.OnClickListener,IHomeView {

    private PercentLinearLayout commentaries;
    private TextView address;
    private TextView merchants;
    private TextView contact;
    private Button appointment;
    private double score;
    private String address1;
    private String userImage;
    private String usersId;
    private String family;
    private ImageView user_icon;
    private TextView user_name;
    private RatingBar user_rating;
    private HomePresenter homePresenter;

    @Override
    public int initLayoutID() {
        return R.layout.activity_merchant;
    }

    public void initView() {
        commentaries = (PercentLinearLayout) findViewById(R.id.commentaries);
        address = (TextView) findViewById(R.id.address);
        merchants = (TextView) findViewById(R.id.merchants);
        contact = (TextView) findViewById(R.id.contact);
        appointment = (Button) findViewById(R.id.appointment);
        user_name=findViewById(R.id.merchant_username);
        user_icon=findViewById(R.id.merchant_usericon);
        user_rating=findViewById(R.id.merchant_userrating);
        Intent intent = getIntent();
        score = intent.getDoubleExtra("score", 0);
        address1 = intent.getStringExtra("address");
        userImage = intent.getStringExtra("userImage");
        usersId = intent.getStringExtra("usersId");
        family = intent.getStringExtra("family");

        homePresenter = new HomePresenter(this);
        Map param=new HashMap();
        param.put("usersId", "usersId");
        homePresenter.getData((UrlPath.TOTALPATH + UrlPath.PINGLUNPATH),param,0);

        appointment.setOnClickListener(this);
        contact.setOnClickListener(this);
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {
        address.setText(address1);
        merchants.setText(family);
        user_rating.setRating((float) score);
        user_name.setText(family);
        Glide.with(this).load(userImage).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(user_icon);
    }

    @Override
    public void initListener() {
        commentaries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MerchantActivity.this,CommentariesActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setMyAppTitle() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appointment:
                Intent in=new Intent(MerchantActivity.this,AppointmentActivity.class);
                in.putExtra("userId",usersId);
                in.putExtra("family",family);
                startActivity(in);
                break;
            case R.id.contact:
                View view = LayoutInflater.from(this).inflate(R.layout.contact_item,null);
                view.getBackground().setAlpha(200);
                TextView call=view.findViewById(R.id.make_a_call);
                TextView message=view.findViewById(R.id.send_a_message);
                TextView cancle=view.findViewById(R.id.cancel);
                final PopupWindow popupWindow=new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + "18317521412");
                        intent.setData(data);
                        startActivity(intent);
                    }
                });

                message.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                popupWindow.showAtLocation(contact, Gravity.CENTER,0,0);
                break;
        }
    }

    @Override
    public void getData(String data, int i) {
        Log.e("Tag---------",data);
    }
}
