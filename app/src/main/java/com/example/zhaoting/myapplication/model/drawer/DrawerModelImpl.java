package com.example.zhaoting.myapplication.model.drawer;

import android.os.Handler;
import android.os.Looper;

import com.example.zhaoting.myapplication.bean.DrawerBean;
import com.example.zhaoting.myapplication.model.OnListener;
import com.example.zhaoting.myapplication.okhttp.NoConnected;
import com.example.zhaoting.myapplication.okhttp.OkHttpUtil;
import com.example.zhaoting.utils.IOUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by zhaoting on 16/4/27.
 */
public class DrawerModelImpl implements DrawerModel {
    @Override
    public void getDrawer(final OnListener listener) {
        String url = "http://news-at.zhihu.com/api/4/themes";
//        Map<String, String> map = new HashMap<>();
//        VolleyUtil.getInstance().get(url,map,new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                listener.onSuccess(s);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                listener.onError();
//            }
//        }, "getDrawer");
        OkHttpUtil.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                listener.onError();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();
                IOUtils.getInstance().write2SDFromInput(result,"drawer");
                Gson gson = new Gson();
                DrawerBean drawerBean = gson.fromJson(result, DrawerBean.class);
                final List<DrawerBean.OthersBean> list = drawerBean.getOthers();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onSuccess(list);
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
