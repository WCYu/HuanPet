package com.example.huanpet.view.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huanpet.R;
import com.example.huanpet.entity.HomePetBean;

import java.util.ArrayList;

/**
 * Created by 阿禹 on 2017/12/14.
 */

public class HomePetAdapter extends BaseAdapter {
    ArrayList<HomePetBean.DescBean> descBeans;
    Context context;

    public HomePetAdapter(ArrayList<HomePetBean.DescBean> descBeans, Context context) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.home_pet_item, null);
        ViewHolder vh=new ViewHolder(convertView);
        vh.pet_name.setText(descBeans.get(position).getTypeName());
        vh.pet_price.setText(descBeans.get(position).getPetPrice()+"￥");
        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView pet_icon;
        public TextView pet_name;
        public TextView pet_price;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.pet_icon = (ImageView) rootView.findViewById(R.id.pet_icon);
            this.pet_name = (TextView) rootView.findViewById(R.id.pet_name);
            this.pet_price = (TextView) rootView.findViewById(R.id.pet_price);
        }

    }
}
