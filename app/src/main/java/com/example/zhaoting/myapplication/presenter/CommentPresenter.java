package com.example.zhaoting.myapplication.presenter;

import com.example.zhaoting.myapplication.bean.CommentBean;
import com.example.zhaoting.myapplication.model.OnListener;
import com.example.zhaoting.myapplication.model.comment.CommentModel;
import com.example.zhaoting.myapplication.model.comment.CommentModelImpl;
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
        mCommentModel.getLongComments(id, new OnListener() {


            @Override
            public void onSuccess(Object s) {
                List<CommentBean> bean = (List<CommentBean>) s;
                mCommentView.onSuccessLongComments(bean);
            }

            @Override
            public void onError() {
                mCommentView.onErrorLongComments();
            }

            @Override
            public void onNoConnected() {
                mCommentView.onLongNoConnected();
            }

        });
    }

    public void getShortComment(int id) {
        mCommentModel.getShortComments(id, new OnListener() {


            @Override
            public void onSuccess(Object s) {
                List<CommentBean> bean = (List<CommentBean>) s;
                mCommentView.onSuccessShortComments(bean);
            }

            @Override
            public void onError() {
                mCommentView.onErrorShortComments();

            }

            @Override
            public void onNoConnected() {
                mCommentView.onShortNoConnected();

            }
        });
    }
}
