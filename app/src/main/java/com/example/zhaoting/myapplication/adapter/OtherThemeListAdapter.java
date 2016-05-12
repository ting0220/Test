package com.example.zhaoting.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.bean.StoriesBean;
import com.example.zhaoting.myapplication.viewHolder.ListHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoting on 16/5/12.
 */
public class OtherThemeListAdapter extends RecyclerView.Adapter<ListHolder> {
    private List<StoriesBean> mList = new ArrayList<>();
    private Context mContext;

    public OtherThemeListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListHolder holder = new ListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_list, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
        holder.timeFlag.setVisibility(View.GONE);
        if (mList.get(position).getImages() != null) {
            Picasso.with(mContext).load(mList.get(position).getImages().get(0)).into(holder.articleImg);
            if (mList.get(position).isMultipic()) {
                holder.articleFlag.setVisibility(View.VISIBLE);
            } else {
                holder.articleFlag.setVisibility(View.GONE);
            }
        } else {
            holder.articleImg.setVisibility(View.GONE);
            holder.articleFlag.setVisibility(View.GONE);
        }
        holder.articleTitle.setText(mList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(List<StoriesBean> list) {
        if (list != null) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

}
