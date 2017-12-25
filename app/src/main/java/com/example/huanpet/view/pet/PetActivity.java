package com.example.huanpet.view.pet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.OkhttpUtil;
import com.example.huanpet.utils.PreferencesUtil;
import com.example.huanpet.utils.util.CJSON;
import com.example.huanpet.utils.util.PetInfo;
import com.example.huanpet.utils.util.TableUtils;
import com.example.huanpet.utils.util.ToastUtil;
import com.example.huanpet.view.pet.View.TipPop;
import com.example.huanpet.view.pet.View.Uuu;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PetActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv_back;
    private ListView mlv_pet;
    private Button btn_addpet;
    List<PetInfo> petInfos;

    @Override
    public void initView() {


        mlv_pet = (ListView)this.findViewById(R.id.lv_pet);
        iv_back = (ImageView)this.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              finish();
            }
        });
        btn_addpet = (Button)this.findViewById(R.id.btn_addPet);
        btn_addpet.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent in=new Intent(PetActivity.this,PetAddActivity.class);
                startActivity(in);
            }
        });
        initListView();
    }




    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        initListView();

    }


    private void initListView() {
        HashMap mmMap = new HashMap();
        mmMap.put("userId", PreferencesUtil.getInstance().getUserId());

        String url="http://123.56.150.230:8885/dog_family/petInfo/getPetInfoByUserId.jhtml";
        String json = CJSON.toJSONMap(mmMap);
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
                String string = response.body().string();

                if(CJSON.getRET(string).booleanValue()) {
                    PetActivity.this.petInfos = CJSON.parseArray(CJSON.getDESC(string), PetInfo.class);


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            MyPetAdapter adapter = new MyPetAdapter(PetActivity.this.petInfos, PetActivity.this, PetActivity.this.mlv_pet);
                            PetActivity.this.mlv_pet.setAdapter(adapter);
                            PetActivity.this.mlv_pet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(PetActivity.this, PetSelectOneActivity.class);
                                    intent.putExtra("PetCode", ((PetInfo)PetActivity.this.petInfos.get(position)).getPetCode());
                                    PetActivity.this.startActivity(intent);
                                }
                            });
                        }
                    });

                }
            }
        });




    }

    @Override
    public int initLayoutID() {
        return R.layout.activity_pet;
    }



    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void setMyAppTitle() {

    }
}
