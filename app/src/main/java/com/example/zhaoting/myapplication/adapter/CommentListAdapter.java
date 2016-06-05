package com.example.zhaoting.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.bean.CommentBean;
import com.example.zhaoting.myapplication.viewHolder.CommentEmptyHolder;
import com.example.zhaoting.myapplication.viewHolder.CommentExtraHolder;
import com.example.zhaoting.myapplication.viewHolder.CommentListHolder;
import com.example.zhaoting.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoting on 16/6/2.
 */
public class CommentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<CommentBean> mList = new ArrayList<>();
    private int longSize = 0;
    private int shortSize = 0;

    public CommentListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            CommentExtraHolder holder = new CommentExtraHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_extra, parent, false));
            return holder;
        } else if (viewType == 1) {
            return new CommentEmptyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_empty, parent, false));
        } else {
            CommentListHolder holder = new CommentListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_list, parent, false));
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommentExtraHolder) {
            if (position == 0) {
                ((CommentExtraHolder) holder).mTextView.setText(String.valueOf(longSize) + "条长评");
                ((CommentExtraHolder) holder).mImageView.setVisibility(View.GONE);
            } else {
                ((CommentExtraHolder) holder).mTextView.setText(String.valueOf(shortSize) + "条短评");
                ((CommentExtraHolder) holder).mImageView.setVisibility(View.VISIBLE);
            }
        } else if (holder instanceof CommentListHolder) {
            if (mList.size() != 0) {
                if (position < longSize + 1) {
                    Picasso.with(mContext).load(mList.get(position - 1).getAvatar()).into(((CommentListHolder) holder).mCircleImageView);
                    ((CommentListHolder) holder).mCommentOwner.setText(mList.get(position - 1).getAuthor());
                    ((CommentListHolder) holder).mCommentContent.setText(mList.get(position - 1).getContent());
                    String date = Utils.getInstance().dateLongToString(mList.get(position - 1).getTime());
                    ((CommentListHolder) holder).mCommentDate.setText(date);
                    ((CommentListHolder) holder).mCommentLike.setText(String.valueOf(mList.get(position - 1).getLikes()));
                } else {
                    if (longSize == 0) {
                        Picasso.with(mContext).load(mList.get(position - 3).getAvatar()).into(((CommentListHolder) holder).mCircleImageView);
                        ((CommentListHolder) holder).mCommentOwner.setText(mList.get(position - 3).getAuthor());
                        ((CommentListHolder) holder).mCommentContent.setText(mList.get(position - 3).getContent());
                        String date = Utils.getInstance().dateLongToString(mList.get(position - 3).getTime());
                        ((CommentListHolder) holder).mCommentDate.setText(date);
                        ((CommentListHolder) holder).mCommentLike.setText(String.valueOf(mList.get(position - 3).getLikes()));
                    }else{
                        Picasso.with(mContext).load(mList.get(position - 2).getAvatar()).into(((CommentListHolder) holder).mCircleImageView);
                        ((CommentListHolder) holder).mCommentOwner.setText(mList.get(position - 2).getAuthor());
                        ((CommentListHolder) holder).mCommentContent.setText(mList.get(position - 2).getContent());
                        String date = Utils.getInstance().dateLongToString(mList.get(position - 2).getTime());
                        ((CommentListHolder) holder).mCommentDate.setText(date);
                        ((CommentListHolder) holder).mCommentLike.setText(String.valueOf(mList.get(position - 2).getLikes()));

                    }
                }
            }
        } else {

        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            if (longSize == 0) {
                if (position == 1) {
                    return 1;
                } else if (position == 2) {
                    return 0;
                }
            } else {
                if (position == longSize + 1) {
                    return 0;
                } else {
                    return 2;
                }
            }
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        if (longSize == 0 && mList.size() == 0) {
            return 3;
        } else {
            if (longSize == 0) {
                return mList.size() + 3;
            } else {
                return mList.size() + 2;
            }
        }
    }

    public void setLongSize(int longSize) {
        this.longSize = longSize;
    }

    public void setShortSize(int shortSize) {
        this.shortSize = shortSize;
    }

    public void setList(List<CommentBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

}
