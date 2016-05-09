package com.example.zhaoting.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.bean.HomeBean;
import com.example.zhaoting.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoting on 16/5/5.
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.HomeListHolder> {
    private List<HomeBean.StoriesBean> mList = new ArrayList<>();
    private Context mContext;


    public HomeListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public HomeListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HomeListHolder holder = new HomeListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_list, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(HomeListHolder holder, int position) {
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
    }

    public void setList(List<HomeBean.StoriesBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HomeListHolder extends RecyclerView.ViewHolder {
        TextView timeFlag;
        TextView articleTitle;
        ImageView articleImg;
        ImageView articleFlag;

        public HomeListHolder(View itemView) {
            super(itemView);
            timeFlag = (TextView) itemView.findViewById(R.id.id_home_list_time);
            articleTitle = (TextView) itemView.findViewById(R.id.id_home_card_view_text);
            articleImg = (ImageView) itemView.findViewById(R.id.id_home_card_view_img);
            articleFlag = (ImageView) itemView.findViewById(R.id.id_home_card_view_flag);
        }
    }
}
