package com.example.huanpet;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by 阿禹 on 2017/12/6.
 */

public class WelcomeAdapter extends PagerAdapter {
    ArrayList<ImageView> imageViews;
    Context context;

    public WelcomeAdapter(ArrayList<ImageView> imageViews, Context context) {
        this.imageViews = imageViews;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageViews.get(position));
        return imageViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViews.get(position));
    }
}
