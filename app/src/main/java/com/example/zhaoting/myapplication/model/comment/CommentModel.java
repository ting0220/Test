package com.example.zhaoting.myapplication.model.comment;

import com.example.zhaoting.myapplication.model.OnListener;

/**
 * Created by zhaoting on 16/6/2.
 */
public interface CommentModel {
    void getShortComments(int id, OnListener listener);

    void getLongComments(int id, OnListener listener);
}
