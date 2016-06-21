package com.example.zhaoting.myapplication.model.home;

import android.os.Handler;
import android.os.Looper;

import com.example.zhaoting.myapplication.bean.HomeBean;
import com.example.zhaoting.myapplication.model.OnListener;
import com.example.zhaoting.myapplication.okhttp.NoConnected;
import com.example.zhaoting.myapplication.okhttp.OkHttpUtil;
import com.example.zhaoting.utils.IOUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by zhaoting on 16/4/29.
 */
public class HomeModelImpl implements HomeModel {
    int i = 0;

    @Override
    public void getHomeList(final OnListener mHomeListener, String url) {
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
                if (homeBean.getTop_stories() != null) {
                    IOUtils.getInstance().deleteFile();
                    IOUtils.getInstance().write2SDFromInput(result, "home" + String.valueOf(i));
                    i++;
                } else {
                    IOUtils.getInstance().write2SDFromInput(result, "home" + String.valueOf(i));
                    i++;
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        mHomeListener.onSuccess(homeBean);
//                        mHomeListener.onTopSuccess(mTopList);
                    }
                });
            }
        }, new NoConnected() {
            @Override
            public void noConnected() {
                mHomeListener.onNoConnected();
            }
        });
    }
}
