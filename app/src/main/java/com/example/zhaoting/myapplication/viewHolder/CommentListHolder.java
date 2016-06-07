package com.example.zhaoting.myapplication.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.zhaoting.myapplication.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zhaoting on 16/6/2.
 */
public class CommentListHolder extends RecyclerView.ViewHolder {
    public CircleImageView mCircleImageView;
    public TextView mCommentOwner;
    public TextView mCommentLike;
    public TextView mCommentContent;
    public TextView mCommentDate;
    public TextView mCommentReply;
    public TextView mCommentReplyFold;

    public CommentListHolder(View itemView) {
        super(itemView);
        mCircleImageView = (CircleImageView) itemView.findViewById(R.id.id_comment_photo);
        mCommentOwner = (TextView) itemView.findViewById(R.id.id_comment_name);
        mCommentLike = (TextView) itemView.findViewById(R.id.id_comment_like);
        mCommentContent = (TextView) itemView.findViewById(R.id.id_comment_content);
        mCommentDate = (TextView) itemView.findViewById(R.id.id_comment_time);
        mCommentReply = (TextView) itemView.findViewById(R.id.id_comment_reply);
        mCommentReplyFold = (TextView) itemView.findViewById(R.id.id_comment_reply_visible);
    }
}
