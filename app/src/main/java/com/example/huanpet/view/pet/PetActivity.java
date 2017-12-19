package com.example.huanpet.view.pet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.example.huanpet.R;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.utils.OkhttpUtil;
import com.example.huanpet.utils.PreferencesUtil;
import com.example.huanpet.utils.util.CJSON;
import com.example.huanpet.utils.util.PetInfo;
import com.example.huanpet.utils.util.ToastUtil;
import com.example.huanpet.view.pet.View.TipPop;
import com.google.gson.Gson;

import java.io.IOException;
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
import okhttp3.Response;

public class PetActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv_back;
    private ListView mlv_pet;
    private Button btn_addpet;
    List<PetInfo> petInfos;
    private TipPop tipPop;
    private Button btn_addPet;


    @Override
    public int initLayoutID() {
        return R.layout.activity_pet;
    }

    @Override
    public void initView() {
        tipPop = TipPop.getInstance(this);
        mlv_pet = (ListView) findViewById(R.id.lv_pet);
        iv_back = (ImageView) findViewById(R.id.iv_back);


        btn_addPet = (Button) findViewById(R.id.btn_addPet);
        btn_addPet.setOnClickListener(this);
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_addpet = (Button) findViewById(R.id.btn_addPet);
        btn_addpet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
              /*  if (petInfos.size() >= 6) {
                    ToastUtil.show("最多6只狗!");
                } else {*/
               // Toast.makeText(PetActivity.this, "tiaozhuan", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(PetActivity.this, PetAddActivity.class);
                startActivity(intent);
            }
        });

        initListView();
    }

    @Override
    public void setMyAppTitle() {

    }

    @Override
    protected void onResume() {

        // TODO Auto-generated method stub
        super.onResume();


        initListView();

    }


    /**
     * 查询宠物列表
     */
    private void initListView() {
        Map<String, Object> mmMap = new HashMap<>();
        mmMap.put("userId ", PreferencesUtil.getInstance().getUserId());

        final String json = CJSON.toJSONMap(mmMap);
        Log.e("TAGG", json);
        FormBody.Builder body = new FormBody.Builder();
        body.add(CJSON.DATA, json);
        FormBody formBody = body.build();
        OkhttpUtil.getInstance().postJson("http://123.56.150.230:8885/dog_family/" + "petInfo/getPetInfoByUserId.jhtml", formBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {



             /*  String string = response.body().string();
                Gson gson=new Gson();
                PetInfo petInfo = gson.fromJson(string, PetInfo.class);

                MyPetAdapter adapter = new MyPetAdapter(petInfos, PetActivity.this, mlv_pet);
                mlv_pet.setAdapter(adapter);
                mlv_pet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                         Intent intent=new Intent(PetActivity.this,PetSelectOneActivity.class);
                          intent.putExtra("PetCode",petInfos.get(position).getPetCode());
                          startActivity(intent);
                    }
                });*/

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}


