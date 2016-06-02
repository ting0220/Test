package com.example.zhaoting.myapplication.model.comment;

import com.example.zhaoting.myapplication.bean.CommentBean;

import java.util.List;

/**
 * Created by zhaoting on 16/6/2.
 */
public interface CommentsLongListener {
    void onLongCommentsSuccess(List<CommentBean> bean);

    void onLongCommentsError();
}
