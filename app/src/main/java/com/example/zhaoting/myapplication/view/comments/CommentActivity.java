package com.example.zhaoting.myapplication.view.comments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.adapter.CommentListAdapter;
import com.example.zhaoting.myapplication.bean.CommentBean;
import com.example.zhaoting.myapplication.presenter.CommentPresenter;
import com.example.zhaoting.myapplication.widget.OnRecyclerItemClickListener;
import com.example.zhaoting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoting on 16/6/2.
 */
public class CommentActivity extends Activity implements CommentView {
    private RecyclerView mRecyclerView;

    private int longComments;
    private int shortComments;
    private int id;
    private CommentPresenter mCommentPresenter = new CommentPresenter(this);
    private CommentListAdapter mAdapter;
    private List<CommentBean> mList = new ArrayList<>();

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
        id = getIntent().getExtras().getInt("id");
        mAdapter.setLongSize(longComments);
        mAdapter.setShortSize(shortComments);
        if (longComments != 0) {
            mCommentPresenter.getLongComments(id);
        }
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_comment_recycler);
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new CommentListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0) {
//                    Utils.getInstance().ToastShort(String.valueOf(position) + "长评");
                } else {
                    if (longComments == 0) {
                        if (position == 1) {
//                            Utils.getInstance().ToastShort(String.valueOf(position));
                        } else {
//                            Utils.getInstance().ToastShort(String.valueOf(position) + "短评");
                            if (shortComments != 0&&mAdapter.getItemCount()==longComments+2) {
                                mCommentPresenter.getShortComment(id);
                            }else{
                             
                            }
//                            mCommentPresenter.getShortComment(id);
                        }
                    } else {
                        if (position < longComments + 1) {
                            Utils.getInstance().ToastShort("长" + mList.get(position - 1).getContent());
                        } else if (position == longComments + 1) {
//                            Utils.getInstance().ToastShort(String.valueOf(position) + "短评");
                            if (shortComments != 0) {
                                mCommentPresenter.getShortComment(id);
                            }

                        } else {
                            Utils.getInstance().ToastShort("短" + mList.get(position - 2).getContent());
                        }
                    }
                }

            }

            @Override
            public void onItemLongClick(View view, int position) {
                Utils.getInstance().ToastShort(String.valueOf(position) + "long");

            }
        });
    }

    @Override
    public void onSuccessLongComments(List<CommentBean> list) {
        mList = list;
        mAdapter.setList(list);
    }

    @Override
    public void onSuccessShortComments(List<CommentBean> list) {
        mList.addAll(list);
        mAdapter.setList(mList);
        mRecyclerView.smoothScrollToPosition(longComments+1);

    }

    @Override
    public void onErrorLongComments() {

    }

    @Override
    public void onErrorShortComments() {

    }
}
