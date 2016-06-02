package com.example.zhaoting.myapplication.view.comments;

import com.example.zhaoting.myapplication.bean.CommentBean;

import java.util.List;

/**
 * Created by zhaoting on 16/6/2.
 */
public interface CommentView {
    void onSuccessLongComments(List<CommentBean> list);

    void onSuccessShortComments(List<CommentBean> list);

    void onErrorLongComments();

    void onErrorShortComments();
}
