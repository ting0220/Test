package com.example.zhaoting.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.bean.StoriesBean;
import com.example.zhaoting.myapplication.viewHolder.ListHolder;
import com.example.zhaoting.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoting on 16/5/5.
 */
public class HomeListAdapter extends RecyclerView.Adapter<ListHolder> {
    private List<StoriesBean> mList = new ArrayList<>();
    private Context mContext;


    public HomeListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListHolder holder = new ListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_list, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
        holder.articleTitle.setText(mList.get(position).getTitle());
        Picasso.with(mContext).load(mList.get(position).getImages().get(0)).into(holder.articleImg);
        if (Utils.getInstance().isToday(mList.get(position).getDate())) {
            holder.timeFlag.setText(R.string.today_news);
        } else {
            holder.timeFlag.setText(Utils.getInstance().getDate(mList.get(position).getDate()));
        }
        if (mList.get(position).isMultipic()) {
            holder.articleFlag.setVisibility(View.VISIBLE);
        } else {
            holder.articleFlag.setVisibility(View.GONE);
        }
        if (position == 0) {
            holder.timeFlag.setVisibility(View.VISIBLE);
        } else {
            if (mList.get(position).getDate().equals(mList.get(position - 1).getDate())) {
                holder.timeFlag.setVisibility(View.GONE);
            } else {
                holder.timeFlag.setVisibility(View.VISIBLE);
            }
        }
//        String title = "首页";
//        String info=holder.timeFlag.getText().toString();
//        if (!info.equals(title)) {
//
//        }
    }

    public void setList(List<StoriesBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }



}
