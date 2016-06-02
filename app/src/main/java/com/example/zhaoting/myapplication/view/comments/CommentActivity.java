package com.example.zhaoting.myapplication.view.comments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.adapter.CommentListAdapter;
import com.example.zhaoting.myapplication.bean.CommentBean;
import com.example.zhaoting.myapplication.presenter.CommentPresenter;

import java.util.List;

/**
 * Created by zhaoting on 16/6/2.
 */
public class CommentActivity extends Activity implements CommentView {
    private RecyclerView mRecyclerView;

    private int longComments;
    private int shortComments;
    private CommentPresenter mCommentPresenter=new CommentPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        initViews();
        initDatas();
    }

    private void initDatas() {
        longComments = getIntent().getExtras().getInt("longComments");
        shortComments = getIntent().getExtras().getInt("shortComments");
        if (longComments == 0) {

        }
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_comment_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        CommentListAdapter adapter = new CommentListAdapter(this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onSuccessLongComments(List<CommentBean> list) {

    }

    @Override
    public void onSuccessShortComments(List<CommentBean> list) {

    }

    @Override
    public void onErrorLongComments() {

    }

    @Override
    public void onErrorShortComments() {

    }
}
