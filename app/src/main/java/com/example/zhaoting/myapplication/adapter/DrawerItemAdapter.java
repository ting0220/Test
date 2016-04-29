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
public class DrawerItemAdapter extends RecyclerView.Adapter<DrawerItemAdapter.DrawerItemHolder> {
    private List<DrawerBean.OthersBean> mList = new ArrayList<>();

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_FIRST = 1;
    private static final int TYPE_NORMAL = 2;

    private View mHeaderView;
    private View mFirstView;

    private onItemClickListener mListener;
    private onItemClickListener mHeaderLineOneListener;
    private onItemClickListener mHeaderCollectListener;
    private onItemClickListener mHeaderDownListener;
    private onItemClickListener mFirstListener;


    public void setonItemClickListener(onItemClickListener li) {
        mListener = li;
    }

    public void setHeaderLineOneListener(onItemClickListener headerListener) {
        mHeaderLineOneListener = headerListener;
    }

    public void setHeaderCollectListener(onItemClickListener headerCollectListener) {
        mHeaderCollectListener = headerCollectListener;
    }

    public void setHeaderDownListener(onItemClickListener headerDownListener) {
        mHeaderDownListener = headerDownListener;
    }

    public void setFirstListener(onItemClickListener firstListener) {
        mFirstListener = firstListener;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public void setFirstView(View firstView) {
        mFirstView = firstView;
        notifyItemInserted(1);
    }


    public View getHeaderView() {
        return mHeaderView;
    }

    public View getFirstView() {
        return mFirstView;
    }

    public void setList(List<DrawerBean.OthersBean> datas) {
        mList.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == 1) {
            return TYPE_FIRST;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public DrawerItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new DrawerItemHolder(mHeaderView);
        } else if (mFirstView != null && viewType == TYPE_FIRST) {
            return new DrawerItemHolder(mFirstView);
        } else {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drawer_normal, parent, false);
            return new DrawerItemHolder(layout);
        }
    }

    @Override
    public void onBindViewHolder(final DrawerItemHolder holder, final int position) {
        int type = getItemViewType(position);
        switch (type) {
            case TYPE_HEADER: {
                if (mHeaderLineOneListener == null) return;
                holder.itemView.findViewById(R.id.id_line_one).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mHeaderLineOneListener.onItemClick(position, null);
                    }
                });
                holder.itemView.findViewById(R.id.id_header_collect).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mHeaderCollectListener.onItemClick(position, null);
                    }
                });
                holder.itemView.findViewById(R.id.id_header_down).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mHeaderDownListener.onItemClick(position, null);
                    }
                });


            }
            break;
            case TYPE_FIRST: {
                if (mFirstListener == null) return;
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mFirstListener.onItemClick(position, null);

                    }
                });
            }
            break;
            case TYPE_NORMAL: {
                holder.tv.setText(mList.get(position - 2).getName());
                if (mListener == null) return;
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onItemClick(position - 2, mList.get(position - 2));

                    }
                });
            }
            break;
        }

    }

    @Override
    public int getItemCount() {
        int count = mList.size() + 2;
        return count;
    }

    public class DrawerItemHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public DrawerItemHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView) {
                return;
            } else if (itemView == mFirstView) {
                return;
            } else {
                tv = (TextView) itemView.findViewById(R.id.id_drawer_item_text);
            }
        }
    }

    public interface onItemClickListener {
        void onItemClick(int position, DrawerBean.OthersBean data);
    }
}
