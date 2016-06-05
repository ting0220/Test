package com.example.zhaoting.myapplication.view.comments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.adapter.CommentListAdapter;
import com.example.zhaoting.myapplication.bean.CommentBean;
import com.example.zhaoting.myapplication.presenter.CommentPresenter;
import com.example.zhaoting.myapplication.widget.OnRecyclerItemClickListener;
import com.example.zhaoting.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

/**
 * Created by zhaoting on 16/6/2.
 */
public class CommentActivity extends Activity implements CommentView, View.OnClickListener {
    private RecyclerView mRecyclerView;

    private int longComments;
    private int shortComments;
    private int id;
    private CommentPresenter mCommentPresenter = new CommentPresenter(this);
    private CommentListAdapter mAdapter;
    private List<CommentBean> mList = new ArrayList<>();
    private ImageView mCommentBack;
    private TextView mCommentCount;
    private ImageView mCommentWrite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        initViews();
        initDatas();
        initListeners();
    }

    private void initListeners() {
        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0) {
//                    Utils.getInstance().ToastShort(ing.valueOf(position) + "长评");
                } else {
                    if (longComments == 0) {
                        if (position == 1) {
//                            Utils.getInstance().ToastShort(String.valueOf(position));
                        } else if (position == 2) {
//                            Utils.getInstance().ToastShort(String.valueOf(position) + "短评");
                            if (shortComments != 0 && mAdapter.getItemCount() == longComments + 3) {
                                mCommentPresenter.getShortComment(id);
                            } else {
                                mList = new ArrayList<CommentBean>();
                                mAdapter.setList(mList);
                                mRecyclerView.smoothScrollToPosition(0);
                                Utils.getInstance().ToastShort("should fold");
                            }
//                            mCommentPresenter.getShortComment(id);
                        } else {
                            Utils.getInstance().ToastShort("短" + mList.get(position - 3).getContent());
                        }
                    } else {
                        if (position < longComments + 1) {
                            Utils.getInstance().ToastShort("长" + mList.get(position - 1).getContent());
                        } else if (position == longComments + 1) {
                            if (shortComments != 0 && mAdapter.getItemCount() == longComments + 2) {
                                mCommentPresenter.getShortComment(id);
                            } else {
                                for (int i = mAdapter.getItemCount() - 3; i > longComments - 1; i--) {
                                    mList.remove(i);
                                }
                                mAdapter.setList(mList);
                                mRecyclerView.smoothScrollToPosition(0);
                                Utils.getInstance().ToastShort("should fold");
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

        mCommentBack.setOnClickListener(this);
        mCommentWrite.setOnClickListener(this);

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
        mCommentCount.setText(String.valueOf(longComments+shortComments)+"条点评");
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_comment_recycler);
        mCommentBack = (ImageView) findViewById(R.id.id_comment_back);
        mCommentCount = (TextView) findViewById(R.id.id_comment_count);
        mCommentWrite = (ImageView) findViewById(R.id.id_comment_write);

        final LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new CommentListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

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
        if (longComments == 0) {
            mRecyclerView.smoothScrollToPosition(longComments + 2);
        } else {
            mRecyclerView.smoothScrollToPosition(longComments + 1);
        }


    }

    @Override
    public void onErrorLongComments() {

    }

    @Override
    public void onErrorShortComments() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_comment_back: {
                CommentActivity.this.finish();
            }
            break;
            case R.id.id_comment_write: {
                Utils.getInstance().ToastShort("click_write");
            }
            break;
        }
    }
}
