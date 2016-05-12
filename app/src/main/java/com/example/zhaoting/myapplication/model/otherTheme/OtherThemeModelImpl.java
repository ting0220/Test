package com.example.zhaoting.myapplication.model.otherTheme;

import android.os.Handler;
import android.os.Looper;

import com.example.zhaoting.myapplication.bean.OtherThemeBean;
import com.example.zhaoting.myapplication.okhttp.OkHttpUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by zhaoting on 16/5/12.
 */
public class OtherThemeModelImpl implements OtherThemeModel {
    @Override
    public void getOtherTheme(String s, final OtherThemeListener listener) {
        String url = "http://news-at.zhihu.com/api/4/theme/" + s;
        OkHttpUtil.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                listener.onError();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();

                final OtherThemeBean otherThemeBean = gson.fromJson(result, OtherThemeBean.class);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onSuccess(otherThemeBean);
//                        mHomeListener.onTopSuccess(mTopList);
                    }
                });
            }
        });
    }
}
