package com.example.huanpet.view.pet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.huanpet.R;
import com.example.huanpet.utils.util.AppUtils;
import com.example.huanpet.utils.util.CJSON;
import com.example.huanpet.utils.util.PetInfo;
import com.example.huanpet.utils.util.TableUtils;
import com.lzy.okhttputils.callback.StringCallback;
import com.lzy.okhttputils.request.PostRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/12/14.
 */

class MyPetAdapter extends  BaseAdapter {

    private List<PetInfo> listpet;
    private Context mContext;
    private ListView mlv;

    public MyPetAdapter(List<PetInfo> list, Context context, ListView lv) {
        this.listpet = list;
        this.mContext = context;
        this.mlv = lv;
    }

    @Override
    public int getCount() {
        return listpet.size();
    }

    @Override
    public Object getItem(int position) {
        return listpet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder viewHolder = null;
        final PetInfo pet = listpet.get(position);
        viewHolder = new ViewHolder();

        view = LayoutInflater.from(mContext).inflate(
                R.layout.activity_pet_list_item, null);
        viewHolder.desc = (TextView) view.findViewById(R.id.pet_desc_show);
        viewHolder.name = (TextView) view.findViewById(R.id.pet_name_show);

        viewHolder.icon_sex = (ImageView) view.findViewById(R.id.pet_sex_show);
        viewHolder.del = (ImageView) view.findViewById(R.id.iv_delete);
        viewHolder.icon = (ImageView) view
                .findViewById(R.id.pet_photo_show);
        viewHolder.desc.setText(listpet.get(position).getPetInfo());
        viewHolder.name.setText(pet.getPetName());
        Glide.with(mContext).load(pet.getPetImage()).into(viewHolder.icon);

        viewHolder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(mContext)
                        .setTitle("系统提示")
                        // 设置对话框标题
                        .setMessage("确定要删除吗?")
                        // 设置显示的内容
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {// 添加确定按钮
                                    public void onClick(DialogInterface dialog,
                                                        int which) {// 确定按钮的响应事件
                                        String code = pet.getPetCode();
                                        Map<String, Object> map = new HashMap<String, Object>();
                                        map.put(TableUtils.PetInfo.PETCODE,
                                                code);
                                        map.put(TableUtils.PetInfo.ISUSE, "2");


                                        String url="http://123.56.150.230:8885/dog_family/petInfo/deletePetInfo.jhtml";
                                        String json = CJSON.toJSONMap(map);
                                        OkHttpClient ohc = new OkHttpClient();
                                        FormBody.Builder body = new FormBody.Builder();
                                        body.add(CJSON.DATA, json);
                                        Request request = new Request.Builder()
                                                .url(url)
                                                .post(body.build())
                                                .build();

                                        ohc.newCall(request).enqueue(new Callback() {
                                            @Override
                                            public void onFailure(Call call, IOException e) {

                                            }

                                            @Override
                                            public void onResponse(Call call, Response response) throws IOException {
                                                listpet.remove(position);
                                                mlv.setAdapter(new MyPetAdapter(
                                                        listpet, mContext, mlv));
                                            }
                                        });


                                    }
                                })
                        .setNegativeButton("取消",
                                new DialogInterface.OnClickListener() {// 添加返回按钮
                                    public void onClick(DialogInterface dialog,
                                                        int which) {// 响应事件
                                        dialog.dismiss();
                                    }
                                }).show();

            }

        });

        if (pet.getPetSex() == 0) {
            viewHolder.icon_sex.setImageResource(R.drawable.male);
        } else {
            viewHolder.icon_sex.setImageResource(R.drawable.female);
        }
        return view;
    }

    class ViewHolder {

        ImageView icon_sex;
        TextView name;
        TextView desc;
        ImageView del;
        public ImageView icon;
    }
}
