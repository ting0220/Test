package com.example.zhaoting.myapplication.model.extraInfo;

import android.os.Handler;
import android.os.Looper;

import com.example.zhaoting.myapplication.bean.ExtraInfoBean;
import com.example.zhaoting.myapplication.model.OnListener;
import com.example.zhaoting.myapplication.okhttp.NoConnected;
import com.example.zhaoting.myapplication.okhttp.OkHttpUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by zhaoting on 16/6/1.
 */
public class ExtraInfoModelImpl implements ExtraInfoModel {
    @Override
    public void getExtraInfo(int id, final OnListener listener) {
        String url = "http://news-at.zhihu.com/api/4/story-extra/" + id;
        OkHttpUtil.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                listener.onError();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                final ExtraInfoBean bean = gson.fromJson(result, ExtraInfoBean.class);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onSuccess(bean);
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
