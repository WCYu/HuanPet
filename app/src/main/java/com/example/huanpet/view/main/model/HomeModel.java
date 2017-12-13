package com.example.huanpet.view.main.model;

import com.example.huanpet.app.AppService;
import com.example.huanpet.utils.OkhttpUtil;
import com.example.huanpet.utils.util.CJSON;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * Created by 阿禹 on 2017/12/13.
 */

public class HomeModel implements IHomeModel {

    @Override
    public void registerData(String url, Map map, final OnRegisterListener onRegisterListener) {
        FormBody.Builder builder = new FormBody.Builder();
        String josn = CJSON.toJSONMap(map);
        builder.add("data",josn);

        OkhttpUtil.getInstance().postJson(url, builder.build(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                AppService.baseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onRegisterListener.transmitData(string);
                    }
                });
            }
        });
    }
}
