package com.example.zhaoting.myapplication.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhaoting.myapplication.R;

/**
 * Created by zhaoting on 16/6/3.
 */
public class CommentExtraHolder extends RecyclerView.ViewHolder {
    public  TextView mTextView;
    public ImageView mImageView;
    public TextView mLongLine;
//    public TextView mShortLine;

    public CommentExtraHolder(View itemView) {
        super(itemView);
        mTextView= (TextView) itemView.findViewById(R.id.id_text_short_comments);
        mImageView= (ImageView) itemView.findViewById(R.id.id_img_icon_fold);
        mLongLine= (TextView) itemView.findViewById(R.id.id_long_line);
//        mShortLine= (TextView) itemView.findViewById(R.id.id_short_line);
    }
}
