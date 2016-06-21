package com.example.zhaoting.myapplication.okhttp;

import android.text.TextUtils;
import android.util.Log;

import com.example.zhaoting.utils.NetUtils;
import com.example.zhaoting.utils.Utils;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhaoting on 16/4/28.
 */
public class OkHttpUtil {
    private static final OkHttpClient mOkHttpClient = new OkHttpClient();
    private Request mRequest;
    private Call mCall;
    private NoConnected mNoConnected;


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

    public void get(String url, Map<String, String> map, Callback responseCallback, NoConnected mNoConnected) {
        StringBuilder builder = new StringBuilder(url);
        builder.append("?");
        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        for (int i = 0; i < map.size(); i++) {
            String key = iterator.next();
            String value = map.get(key);
            if (!TextUtils.isEmpty(value)) {
                builder.append(key);
                builder.append("=");
                builder.append(map.get(key));
            }
            if (!TextUtils.isEmpty(value) && i < map.size() - 1) {
                builder.append("&");
            }
        }
        Log.i("tag", builder.toString());
        get(builder.toString(), responseCallback, mNoConnected);

    }


    public void get(String url, Callback responseCallback, NoConnected mNoConnected) {
        if (NetUtils.getInstance().isNetConnected()) {
            Request request = new Request.Builder().url(url).build();
            mOkHttpClient.newCall(request).enqueue(responseCallback);
        } else {
            Utils.getInstance().ToastShort("网络连接错误，请检查您的网络设置");
            mNoConnected.noConnected();
        }
    }

    /**
     * 需要根据与后台的约定添加
     *
     * @param url
     * @param body             用于提交的键值对的添加
     * @param responseCallback 如果头部还需要什么需要通过addHeader()的方式添加
     */
    public void post(String url, RequestBody body, Callback responseCallback) {
        Request request = new Request.Builder().url(url)
                .addHeader("Accept", "application/json")
                .post(body).build();
        mOkHttpClient.newCall(request).enqueue(responseCallback);
    }

}