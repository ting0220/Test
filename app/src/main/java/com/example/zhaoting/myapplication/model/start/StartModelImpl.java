package com.example.zhaoting.myapplication.model.start;

import android.os.Handler;
import android.os.Looper;

import com.example.zhaoting.myapplication.bean.StartBean;
import com.example.zhaoting.myapplication.model.OnListener;
import com.example.zhaoting.myapplication.okhttp.NoConnected;
import com.example.zhaoting.myapplication.okhttp.OkHttpUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by zhaoting on 16/4/25.
 */
public class StartModelImpl implements StartModel {
    @Override
    public void start(String width, String height, final OnListener listener) {
        String url = "http://news-at.zhihu.com/api/4/start-image/" + width + "*" + height;
//        Map<String,String> map=new HashMap<>();
//        VolleyUtil.getInstance().get(url, map,new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                listener.onSuccess(s);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                listener.onError();
//            }
//        }, "start");
        OkHttpUtil.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                listener.onError();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                final StartBean startBean = gson.fromJson(result, StartBean.class);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onSuccess(startBean);
                    }
                });

            }
        }, new NoConnected() {
            @Override
            public void noConnected() {
                listener.onNoConnected();
            }
        });
    }

}
