package com.example.huanpet.view.order.model;

import com.example.huanpet.app.AppService;
import com.example.huanpet.utils.OkhttpUtil;
import com.example.huanpet.utils.util.CJSON;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 解亚鑫 on 2017/12/13.
 */

public class ModelIml implements IModel  {
    private Callbacks callbacks;
    @Override
    public void sendUrl(String url, Map map, final Callbacks callbacks) {
        this.callbacks = callbacks;
       FormBody.Builder formbody =  new FormBody.Builder();
        String json = CJSON.toJSONMap(map);
       formbody.add("data",json);

        OkhttpUtil.getInstance().postJson(url, formbody.build(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String string = response.body().string();
                AppService.baseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                            callbacks.sendData(string);
                    }
                });
            }
        });
    }
}
