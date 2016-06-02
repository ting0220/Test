package com.example.zhaoting.myapplication.model.comment;

/**
 * Created by zhaoting on 16/6/2.
 */
public interface CommentModel {
    void getShortComments(int id, CommentsShortListener listener);

    void getLongComments(int id, CommentsLongListener listener);
}
