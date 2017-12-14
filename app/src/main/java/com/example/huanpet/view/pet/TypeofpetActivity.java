package com.example.huanpet.view.pet;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.example.huanpet.R;
import com.example.huanpet.custom.EditTextWithDelete;
import com.example.huanpet.view.pet.Fragment.catFragment;
import com.example.huanpet.view.pet.Fragment.dogFragment;
import com.example.huanpet.view.pet.Fragment.petFragment;
import com.example.huanpet.view.pet.adapter.MyAdapter;
import com.example.huanpet.view.pet.adapter.SortAdapter;
import com.example.huanpet.view.pet.adapter.SortModel;
import com.example.huanpet.view.pet.adapter.TitleItemDecoration;
import com.zaaach.citypicker.utils.PinyinUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TypeofpetActivity extends AppCompatActivity {
    private List<SortModel> mDateList;
    private TitleItemDecoration mDecoration;
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
    private RecyclerView rv;
    private LinearLayoutManager manager;
    private SortAdapter mAdapter;

    private EditTextWithDelete searchEditText;

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
        rv = (RecyclerView) findViewById(R.id.rv);
        searchEditText = (EditTextWithDelete) findViewById(R.id.searchEditText);

    }
}
