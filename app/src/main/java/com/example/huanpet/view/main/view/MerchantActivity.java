package com.example.huanpet.view.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.custom.PercentLinearLayout;

public class MerchantActivity extends BaseActivity implements View.OnClickListener {

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
                startActivity(in);
                break;
            case R.id.contact:

                break;
        }
    }
}
