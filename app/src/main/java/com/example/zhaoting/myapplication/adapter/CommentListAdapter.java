package com.example.zhaoting.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.bean.CommentBean;
import com.example.zhaoting.myapplication.viewHolder.CommentListHolder;
import com.example.zhaoting.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zhaoting on 16/6/2.
 */
public class CommentListAdapter extends RecyclerView.Adapter<CommentListHolder> {
    private Context mContext;
    private List<CommentBean> mList;

    public CommentListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public CommentListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommentListHolder holder = new CommentListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_list, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(CommentListHolder holder, int position) {
        Picasso.with(mContext).load(mList.get(position).getAvatar()).into(holder.mCircleImageView);
        holder.mCommentOwner.setText(mList.get(position).getAuthor());
        holder.mCommentContent.setText(mList.get(position).getContent());
        String date = Utils.dateLongToString(mList.get(position).getTime());
        holder.mCommentDate.setText(date);
        holder.mCommentLike.setText(String.valueOf(mList.get(position).getLikes()));
    }

    @Override
    public int getItemCount() {
        return mList.size() + 2;
    }
}
