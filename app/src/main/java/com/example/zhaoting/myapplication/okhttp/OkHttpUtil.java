package com.example.zhaoting.myapplication.okhttp;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

/**
 * Created by zhaoting on 16/4/28.
 */
public class OkHttpUtil {
    private static final OkHttpClient mOkHttpClient = new OkHttpClient();
    private Request mRequest;
    private Call mCall;


    /**
     * 使用内部类方式实现单例模式
     */
    private static class SingletonHolder {
        private static OkHttpUtil instance = new OkHttpUtil();
    }

    private OkHttpUtil() {
    }

    public static OkHttpUtil getInstance() {
        return SingletonHolder.instance;
    }


    public void get(String url, Callback responseCaliiback) {
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(responseCaliiback);
    }
}