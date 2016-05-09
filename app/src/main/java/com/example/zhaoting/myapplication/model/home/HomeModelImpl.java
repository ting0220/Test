package com.example.zhaoting.myapplication.model.home;

import android.os.Handler;
import android.os.Looper;

import com.example.zhaoting.myapplication.bean.HomeBean;
import com.example.zhaoting.myapplication.okhttp.OkHttpUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by zhaoting on 16/4/29.
 */
public class HomeModelImpl implements HomeModel {
    @Override
    public void getHomeList(final HomeListener mHomeListener,String url) {
        OkHttpUtil.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                mHomeListener.onError();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                final HomeBean homeBean = gson.fromJson(result, HomeBean.class);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        mHomeListener.onSuccess(homeBean);
//                        mHomeListener.onTopSuccess(mTopList);
                    }
                });
            }
        });
    }
}
