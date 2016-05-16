package com.example.zhaoting.myapplication.model.article;

import android.os.Handler;
import android.os.Looper;

import com.example.zhaoting.myapplication.bean.ArticleContentBean;
import com.example.zhaoting.myapplication.okhttp.OkHttpUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by zhaoting on 16/5/16.
 */
public class ArticleContentModelImpl implements ArticleContentModel {
    @Override
    public void getArticleContent(int id, final ArticleContentListener listener) {
        String url = "http://news-at.zhihu.com/api/4/news/" + String.valueOf(id);
        OkHttpUtil.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                listener.onError();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                final ArticleContentBean bean = gson.fromJson(result, ArticleContentBean.class);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onSuccess(bean);
                    }
                });

            }
        });
    }

    @Override
    public void getArticleCss(String url, final ArticleCssListener listener) {
        OkHttpUtil.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                listener.onCssError();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String result = response.body().string();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onCssSuccess(result);
                    }
                });
            }
        });
    }
}
