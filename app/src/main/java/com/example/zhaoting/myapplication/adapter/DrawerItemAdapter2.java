package com.example.zhaoting.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.bean.DrawerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoting on 16/4/27.
 */
public class DrawerItemAdapter2 extends RecyclerView.Adapter<DrawerItemAdapter2.DrawerItemHolder> {
    private List<DrawerBean.OthersBean> mList = new ArrayList<>();

    @Override
    public DrawerItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DrawerItemHolder holder = new DrawerItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drawer_normal, parent, false));
        return holder;
    }

    public void setList(List<DrawerBean.OthersBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(DrawerItemHolder holder, int position) {
        holder.tv.setText(mList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class DrawerItemHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public DrawerItemHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_drawer_item_text);
        }
    }
}
