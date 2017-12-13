package com.example.huanpet.view.pet;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.huanpet.R;
import com.example.huanpet.view.pet.Fragment.catFragment;
import com.example.huanpet.view.pet.Fragment.dogFragment;
import com.example.huanpet.view.pet.Fragment.petFragment;
import com.example.huanpet.view.pet.adapter.MyAdapter;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;


public class TypeofpetActivity extends AppCompatActivity {

    private ArrayList<String> titleList = new ArrayList<String>() {{
        add("狗");
        add("猫");
        add("小宠");

    }};

    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>() {{
        add(new dogFragment());
        add(new catFragment());
        add(new petFragment());
    }};

    private TabLayout tlMain;
    private ViewPager vpMain;

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typeofpet);
        initView();
        adapter = new MyAdapter(getSupportFragmentManager(), titleList, fragmentList);
        vpMain.setAdapter(adapter);
        tlMain.setupWithViewPager(vpMain, true);
        tlMain.setTabsFromPagerAdapter(adapter);
    }

    private void initView() {
        tlMain = (TabLayout) findViewById(R.id.tablayout);
        vpMain = (ViewPager) findViewById(R.id.vp);
    }

}



