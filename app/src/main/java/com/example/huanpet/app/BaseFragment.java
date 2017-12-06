package com.example.huanpet.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huanpet.R;

/**
 * Created by 阿禹 on 2017/12/6.
 */

public abstract class BaseFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(getFragmetId(), container, false);
        initView(view);
        initData();
        initAdapter();
        return view;
    }
    public abstract int getFragmetId();
    public abstract void initView(View view);
    public abstract void initData();
    public abstract void initAdapter();
}
