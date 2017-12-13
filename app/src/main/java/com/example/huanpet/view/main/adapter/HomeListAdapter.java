package com.example.huanpet.view.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.huanpet.R;
import com.example.huanpet.entity.HomeBean;

import java.util.ArrayList;

/**
 * Created by 阿禹 on 2017/12/13.
 */

public class HomeListAdapter extends BaseAdapter {
    ArrayList<HomeBean.DescBean> descBeans;
    Context context;

    public HomeListAdapter(ArrayList<HomeBean.DescBean> descBeans, Context context) {
        this.descBeans = descBeans;
        this.context = context;
    }

    @Override
    public int getCount() {
        return descBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=LayoutInflater.from(context).inflate(R.layout.home_list_item, null);
        ViewHolder vh=new ViewHolder(convertView);
        vh.homeitem_hom.setText(descBeans.get(position).getFamily());
        vh.homeitem_address.setText(descBeans.get(position).getAddress());
        vh.homeitem_distance.setText(descBeans.get(position).getDistance()+"");
        Glide.with(context).load(descBeans.get(position).getUserImage()).into(vh.homeitem_icon);
        vh.homeitem_bar.setRating((float) descBeans.get(position).getScore());

        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView homeitem_icon;
        public TextView homeitem_hom;
        public RatingBar homeitem_bar;
        public TextView homeitem_address;
        public TextView homeitem_distance;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.homeitem_icon = (ImageView) rootView.findViewById(R.id.homeitem_icon);
            this.homeitem_hom = (TextView) rootView.findViewById(R.id.homeitem_hom);
            this.homeitem_bar = (RatingBar) rootView.findViewById(R.id.homeitem_bar);
            this.homeitem_address = (TextView) rootView.findViewById(R.id.homeitem_address);
            this.homeitem_distance = (TextView) rootView.findViewById(R.id.homeitem_distance);
        }

    }
}
