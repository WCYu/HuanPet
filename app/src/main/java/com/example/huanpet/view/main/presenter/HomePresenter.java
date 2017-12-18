package com.example.huanpet.view.main.presenter;

import android.content.Context;
import android.widget.ImageView;

import com.example.huanpet.MainActivity;
import com.example.huanpet.app.BaseActivity;
import com.example.huanpet.view.main.model.HomeModel;
import com.example.huanpet.view.main.model.IHomeModel;
import com.example.huanpet.view.main.model.OnRegisterListener;
import com.example.huanpet.view.main.view.IHomeView;

import java.util.Map;

/**
 * Created by 阿禹 on 2017/12/13.
 */

public class HomePresenter implements IHomePresenter,OnRegisterListener {
    IHomeModel homeModel;
    IHomeView homeView;
    int i;
    public HomePresenter(IHomeView iHomeView){
        homeModel=new HomeModel();
        homeView=iHomeView;
    }
    @Override
    public void getData(String url, Map map,int i) {
        homeModel.registerData(url,map,this);
        this.i=i;
    }

    @Override
    public void transmitData(String data) {
        homeView.getData(data,i);
    }
}
