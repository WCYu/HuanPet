package com.example.huanpet.view.pet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huanpet.R;
import com.example.huanpet.utils.util.PetInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/12/20.
 */

class PetAdapter extends BaseAdapter {
    List<PetInfo> petInfos;
    Context context;
    private ViewHolder holder;

    public PetAdapter(List<PetInfo> petInfos, Context context) {
        this.petInfos = petInfos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return petInfos.size();
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.author.setText(petInfos.get(position).getPetName());
        holder.name.setText((int) petInfos.get(position).getPetWeight());
        holder.img.setText(petInfos.get(position).getPetInfo());

        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView img;
        public TextView name;
        public TextView author;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.img = (TextView) rootView.findViewById(R.id.cn);
            this.name = (TextView) rootView.findViewById(R.id.zt);
            this.author = (TextView) rootView.findViewById(R.id.jj);
        }
    }
}
