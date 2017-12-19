package com.example.huanpet.view.order.model;

import java.util.Map;

import javax.security.auth.callback.Callback;

/**
 * Created by 解亚鑫 on 2017/12/13.
 */

public interface IModel {
    void sendUrl(String url, Map map, Callbacks callbacks);
}
