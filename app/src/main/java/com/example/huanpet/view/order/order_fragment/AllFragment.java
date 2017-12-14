package com.example.huanpet.view.order.order_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.huanpet.R;
import com.example.huanpet.app.Api;
import com.example.huanpet.app.AppService;
import com.example.huanpet.app.BaseFragment;
import com.example.huanpet.view.order.presenter.PersenterIml;
import com.example.huanpet.view.order.view.IView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 解亚鑫 on 2017/12/7.
 */

public class AllFragment extends BaseFragment implements IView {
     ListView lv_all;

    @Override
    public int getFragmetId() {
        return R.layout.fragment_all;
    }

    @Override
    public void initView(View view) {
        int mPage = 1;
        lv_all = AppService.baseActivity.findViewById(R.id.lv_all);
        PersenterIml persenterIml = new PersenterIml(this);
        Map map = new HashMap<>();

//        map.put("coordX", 40.22);
//        map.put("coordY",116.23 );
//        map.put("isStandard",1);
//        map.put("beginIndex",0);
//        map.put("endIndex",10);
//        map.put("serviceCodes","");
//        map.put("orderId","HH54516451513212");
        map.put("petTypeCode","");
        persenterIml.getUrldata(Api.baseUrl+"petType/getPetTypesByVO.jhtml",map);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void getData(String data) {
//        Gson gson = new Gson();
//        gson.fromJson(data,)
        Log.i("tag", data + "");
    }


}
