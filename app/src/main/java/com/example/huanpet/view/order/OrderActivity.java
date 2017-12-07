package com.example.huanpet.view.order;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.CustomTool;
import com.example.huanpet.view.order.adapter.MyOrderAdapter;
import com.example.huanpet.view.order.order_fragment.AllFragment;
import com.example.huanpet.view.order.order_fragment.EvaluateFragment;
import com.example.huanpet.view.order.order_fragment.FosterFragment;
import com.example.huanpet.view.order.order_fragment.WaitSureFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends BaseActivity {

    private CustomTool custom_order;
    private ViewPager order_vp;
    private List<String> tabs;
    private List<Fragment> fragments;
    private TabLayout order_tab;
    @Override
    public int initLayoutID() {
        return R.layout.activity_order;
    }

    @Override
    public void initView() {
            custom_order = findViewById(R.id.custom_order);
            order_vp = findViewById(R.id.order_vp);
            order_tab = findViewById(R.id.order_tab);
    }

    @Override
    public void initAdapter() {
        order_tab.setupWithViewPager(order_vp);
        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(getSupportFragmentManager(), tabs, fragments);
        order_vp.setAdapter(myOrderAdapter);
    }

    @Override
    public void initData() {
        tabs = new ArrayList<String>();
        tabs.add("全部");
        tabs.add("待确认");
        tabs.add("寄养中");
        tabs.add("待评价");
        fragments = new ArrayList<Fragment>();
        fragments.add(new AllFragment());
        fragments.add(new EvaluateFragment());
        fragments.add(new FosterFragment());
        fragments.add(new WaitSureFragment());
    }

    @Override
    public void initListener() {
        custom_order.setOnLeftButtonClickListener(new CustomTool.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setMyAppTitle() {
        custom_order.initViewsVisible(true,true,false,false);
        custom_order.setReturnBtn(" ");
        custom_order.setAppTitle("我的订单");
    }


}
