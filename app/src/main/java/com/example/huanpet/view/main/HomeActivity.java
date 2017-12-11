package com.example.huanpet.view.main;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.view.know.KnowActivity;
import com.example.huanpet.view.news.NewsActivity;
import com.example.huanpet.view.order.OrderActivity;
import com.example.huanpet.view.pet.PetActivity;
import com.example.huanpet.view.purse.PurseActivity;
import com.example.huanpet.view.set.SetActivity;
import com.example.huanpet.view.user.activity.UserActivity;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private android.support.v7.widget.Toolbar home_tool;
    private LinearLayout main_user;
    private LinearLayout main_news;
    private LinearLayout main_pet;
    private LinearLayout main_order;
    private LinearLayout main_purse;
    private LinearLayout main_know;
    private LinearLayout main_set;
    private Button main_btn;

    @Override
    public int initLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        home_tool=findViewById(R.id.home_tool);
        main_user=findViewById(R.id.main_user);
        main_news=findViewById(R.id.main_news);
        main_pet=findViewById(R.id.main_pet);
        main_order=findViewById(R.id.main_order);
        main_purse=findViewById(R.id.main_purse);
        main_know=findViewById(R.id.main_know);
        main_set=findViewById(R.id.main_set);
        main_btn=findViewById(R.id.main_btn);
    }

    @Override
    public void initAdapter() {
        main_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "设置", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        main_user.setOnClickListener(this);
        main_news.setOnClickListener(this);
        main_pet.setOnClickListener(this);
        main_order.setOnClickListener(this);
        main_purse.setOnClickListener(this);
        main_know.setOnClickListener(this);
        main_set.setOnClickListener(this);
    }

    @Override
    public void setMyAppTitle() {

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()) {
            case R.id.main_user:
                intent.setClass(HomeActivity.this, UserActivity.class);
                break;
            case R.id.main_news:
                intent.setClass(HomeActivity.this, NewsActivity.class);
                break;
            case R.id.main_pet:
                intent.setClass(HomeActivity.this, PetActivity.class);
                break;
            case R.id.main_order:
                intent.setClass(HomeActivity.this, OrderActivity.class);
                break;
            case R.id.main_purse:
                intent.setClass(HomeActivity.this, PurseActivity.class);
                break;
            case R.id.main_know:
                intent.setClass(HomeActivity.this, KnowActivity.class);
                break;
            case R.id.main_set:
                intent.setClass(HomeActivity.this, SetActivity.class);
                break;
        }
        startActivity(intent);
    }
}
