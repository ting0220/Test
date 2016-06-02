package com.example.zhaoting.myapplication.model.comment;

import android.os.Handler;
import android.os.Looper;

import com.example.zhaoting.myapplication.bean.CommentBean;
import com.example.zhaoting.myapplication.bean.CommentsBean;
import com.example.zhaoting.myapplication.okhttp.OkHttpUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by zhaoting on 16/6/2.
 */
public class CommentModelImpl implements CommentModel {

    @Override
    public void getShortComments(int id, final CommentsShortListener listener) {
        String url = "http://news-at.zhihu.com/api/4/story/" + String.valueOf(id) + "/short-comments";
        OkHttpUtil.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                listener.onShortCommentsError();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                CommentsBean bean = gson.fromJson(result, CommentsBean.class);
                final List<CommentBean> list = bean.getComments();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onShortCommentsSuccess(list);

                    }
                });
            }
        });
    }

    @Override
    public void getLongComments(int id, final CommentsLongListener listener) {
        String url = "http://news-at.zhihu.com/api/4/story/" + String.valueOf(id) + "/long-comments";
        OkHttpUtil.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                listener.onLongCommentsError();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                CommentsBean bean = gson.fromJson(result, CommentsBean.class);
                final List<CommentBean> list = bean.getComments();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onLongCommentsSuccess(list);

                    }
                });
            }
        });
    }
}
