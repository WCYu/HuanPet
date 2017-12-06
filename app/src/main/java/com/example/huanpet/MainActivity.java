package com.example.huanpet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.PreferencesUtil;
import com.example.huanpet.view.main.HomeActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager welcome_pager;
    private Button start_btn;
    int [] ints={R.drawable.pager01,R.drawable.pager02,R.drawable.pager03};
    private ArrayList<ImageView> imageViews;

    @Override
    public int initLayoutID() {

        return R.layout.activity_main;
    }

    public void initListener() {
        welcome_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==2){
                    start_btn.setVisibility(View.VISIBLE);
                }else {
                    start_btn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void setMyAppTitle() {

    }

    public void initAdapter() {
        WelcomeAdapter welcomeAdapter=new WelcomeAdapter(imageViews,this);
        welcome_pager.setAdapter(welcomeAdapter);
    }

    public void initData() {
        imageViews = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView imageView=new ImageView(this);
            Glide.with(this).load(ints[i]).into(imageView);
            imageViews.add(imageView);
        }
    }

    public void initView() {
        if(PreferencesUtil.getInstance().getWelcomeId()!=0){
            Intent in = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(in);
            finish();
        }
        welcome_pager = (ViewPager) findViewById(R.id.welcome_pager);
        start_btn = (Button) findViewById(R.id.start_btn);
        start_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_btn:
                PreferencesUtil.getInstance().setWelcomeId(1);
                Intent in = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(in);
                finish();
                break;
        }
    }
}
