package com.example.zhaoting.myapplication.presenter;

import com.example.zhaoting.myapplication.bean.CommentBean;
import com.example.zhaoting.myapplication.model.comment.CommentModel;
import com.example.zhaoting.myapplication.model.comment.CommentModelImpl;
import com.example.zhaoting.myapplication.model.comment.CommentsLongListener;
import com.example.zhaoting.myapplication.model.comment.CommentsShortListener;
import com.example.zhaoting.myapplication.view.comments.CommentView;

import java.util.List;

/**
 * Created by zhaoting on 16/6/2.
 */
public class CommentPresenter {
    private CommentView mCommentView;
    private CommentModel mCommentModel;

    public CommentPresenter(CommentView commentView) {
        mCommentView = commentView;
        mCommentModel = new CommentModelImpl();
    }

    public void getLongComments(int id) {
        mCommentModel.getLongComments(id, new CommentsLongListener() {
            @Override
            public void onLongCommentsSuccess(List<CommentBean> bean) {
                mCommentView.onSuccessLongComments(bean);
            }

            @Override
            public void onLongCommentsError() {
                mCommentView.onErrorLongComments();
            }
        });
    }

    public void getShortComment(int id) {
        mCommentModel.getShortComments(id, new CommentsShortListener() {
            @Override
            public void onShortCommentsSuccess(List<CommentBean> bean) {
                mCommentView.onSuccessShortComments(bean);
            }

            @Override
            public void onShortCommentsError() {
                mCommentView.onErrorShortComments();
            }
        });
    }
}
