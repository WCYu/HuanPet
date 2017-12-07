package com.example.huanpet.view.main;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;
import com.example.huanpet.view.know.KnowActivity;
import com.example.huanpet.view.news.NewsActivity;
import com.example.huanpet.view.order.OrderActivity;
import com.example.huanpet.view.pet.PetActivity;
import com.example.huanpet.view.purse.PurseActivity;
import com.example.huanpet.view.set.SetActivity;
import com.example.huanpet.view.user.UserActivity;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout main_user;
    private LinearLayout main_news;
    private LinearLayout main_pet;
    private LinearLayout main_order;
    private LinearLayout main_purse;
    private LinearLayout main_know;
    private LinearLayout main_set;
    private Button main_btn;
    private CustomTool customTool;
    private SearchView searchView;
    private ImageView imageView;
    private DrawerLayout drawerLayout;
    private LinearLayout linearLayout;

    private RadioButton home_vicinity;
    private RadioButton home_pettype;
    private RadioButton home_screening;
    private RadioGroup home_group;
    private ListView home_list;

    @Override
    public int initLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        main_user = findViewById(R.id.main_user);
        main_news = findViewById(R.id.main_news);
        main_pet = findViewById(R.id.main_pet);
        main_order = findViewById(R.id.main_order);
        main_purse = findViewById(R.id.main_purse);
        main_know = findViewById(R.id.main_know);
        main_set = findViewById(R.id.main_set);
        main_btn = findViewById(R.id.main_btn);
        customTool = findViewById(R.id.home_custom);
        searchView = findViewById(R.id.home_search);
        imageView = findViewById(R.id.home_spreads);
        drawerLayout = findViewById(R.id.home_drawer);
        linearLayout = findViewById(R.id.home_hides);
        home_group = findViewById(R.id.home_group);
        home_vicinity = findViewById(R.id.home_vicinity);
        home_pettype = findViewById(R.id.home_pettype);
        home_screening = findViewById(R.id.home_screening);
        home_list = findViewById(R.id.home_list);
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
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(linearLayout);
            }
        });
        home_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.home_vicinity:
                        if (home_vicinity.isChecked()) {
                            View vicinityView = LayoutInflater.from(HomeActivity.this).inflate(R.layout.vicinity_item, null);
                            initPopu(vicinityView);
                        }
                        break;
                    case R.id.home_pettype:
                        if (home_pettype.isChecked()) {
                            View vicinityView = LayoutInflater.from(HomeActivity.this).inflate(R.layout.pettype_item, null);
                            initPopu(vicinityView);
                        }
                        break;
                    case R.id.home_screening:
                        if (home_screening.isChecked()) {
                            View vicinityView = LayoutInflater.from(HomeActivity.this).inflate(R.layout.screening_item, null);
                            initPopu(vicinityView);
                        }
                        break;
                }
            }
        });
    }

    private void initPopu(View vicinityView) {
        vicinityView.getBackground().setAlpha(200);
        PopupWindow popupWindow = new PopupWindow(vicinityView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.FILL_PARENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(home_vicinity, 0, 20);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        home_group.clearCheck();
                    }
                });
            }
        });
    }

    @Override
    public void setMyAppTitle() {
        customTool.setRightIcon(R.drawable.map);
        customTool.initViewsVisible(false, false, true, false);
        customTool.setOnRightImgClickLisrener(new CustomTool.OnRightImgClickListener() {
            @Override
            public void onRightImgClick(View v) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
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
