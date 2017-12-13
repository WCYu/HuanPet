package com.example.pettest;

import java.util.Map;
import java.util.Set;

import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by 阿禹 on 2017/12/6.
 */

public class OkhttpUtil {
    private static OkhttpUtil okhttpUtil;
    private OkHttpClient okHttpClient;
    private OkhttpUtil(){
        okHttpClient=new OkHttpClient();
    }
    public static OkhttpUtil getInstance(){
        if(okhttpUtil==null){
            synchronized (OkhttpUtil.class){
                if(okhttpUtil==null){
                    okhttpUtil=new OkhttpUtil();
                }
            }
        }
        return okhttpUtil;
    }
    public void get(String url, Map<String,String> map, Callback callback){
        Headers.Builder headers = new Headers.Builder();
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        for(Map.Entry<String, String> maps:entrySet){
            String key = maps.getKey();
            String value = map.get(key);
            headers.add(key,value) ;
        }

        Request request = new Request.Builder().headers(headers.build()).url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
    public void post(String url, Map<String,String> map, RequestBody requestBody, Callback callback){
        Headers.Builder headers = new Headers.Builder();
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        for(Map.Entry<String, String> maps:entrySet){
            String key = maps.getKey();
            String value = map.get(key);
            headers.add(key,value) ;
        }

        Request request = new Request.Builder().post(requestBody).headers(headers.build()).url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
