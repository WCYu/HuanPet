package com.example.huanpet.view.order.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 解亚鑫 on 2017/12/7.
 */

public class MyOrderAdapter extends FragmentPagerAdapter {
    List<String> tabs;
    List<Fragment> fragments;

    public MyOrderAdapter(FragmentManager fm, List<String> tabs, List<Fragment> fragments) {
        super(fm);
        this.tabs = tabs;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }
}
