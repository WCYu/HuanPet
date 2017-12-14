package com.example.huanpet.view.order.presenter;

import com.example.huanpet.view.order.model.Callbacks;
import com.example.huanpet.view.order.model.IModel;
import com.example.huanpet.view.order.model.ModelIml;
import com.example.huanpet.view.order.order_fragment.AllFragment;
import com.example.huanpet.view.order.view.IView;

import java.util.Map;

/**
 * Created by 解亚鑫 on 2017/12/13.
 */

public class PersenterIml implements IPersenter ,Callbacks{
    IModel iModel;
    IView iView;

    public PersenterIml(AllFragment allFragment) {
        iModel = new ModelIml();
        iView = allFragment;
    }

    @Override
    public void getUrldata(String url, Map map) {
        iModel.sendUrl(url,map,this);
    }

    @Override
    public void sendData(String data) {
        iView.getData(data);
    }
}
