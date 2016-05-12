package com.example.zhaoting.myapplication.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhaoting.myapplication.R;

/**
 * Created by zhaoting on 16/5/12.
 */
public class ListHolder extends RecyclerView.ViewHolder {
    public TextView timeFlag;
    public TextView articleTitle;
    public ImageView articleImg;
    public ImageView articleFlag;

    public ListHolder(View itemView) {
        super(itemView);
        timeFlag = (TextView) itemView.findViewById(R.id.id_home_list_time);
        articleTitle = (TextView) itemView.findViewById(R.id.id_home_card_view_text);
        articleImg = (ImageView) itemView.findViewById(R.id.id_home_card_view_img);
        articleFlag = (ImageView) itemView.findViewById(R.id.id_home_card_view_flag);
    }
}
