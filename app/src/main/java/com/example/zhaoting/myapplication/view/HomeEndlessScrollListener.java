package com.example.zhaoting.myapplication.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.events.ChangeToolbarTextEvent;
import com.example.zhaoting.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoting on 16/5/9.
 */
public abstract class HomeEndlessScrollListener extends RecyclerView.OnScrollListener {
    /*The minimum amount of items to have below your current scroll position before loading more*/
    private int visibleThreshold = 5;

    /* The current offset index of data you have loaded*/
    private int currentPage = 0;
    /* The total number of items in the dataset after the last load*/
    private int previousTotalItemCount = 0;
    /* True if we are still waiting for the last set of data to load*/
    private boolean loading = true;

    int firstVisibleItem, visibleItemCount, totalItemCount;

    private LinearLayoutManager mLinearLayoutManager;

    String info = "首页";
    List<String> infos = new ArrayList<>();

    public HomeEndlessScrollListener(RecyclerView recyclerView) {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof LinearLayoutManager) {
            this.mLinearLayoutManager = (LinearLayoutManager) manager;
        } else {
            Utils.getInstance().ToastShort("Please change the layoutManager to LinearLayout");
        }
        infos.add(info);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        if (!loading && (totalItemCount < previousTotalItemCount)) {
            this.currentPage = 0;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }

        if (loading) {
            if (totalItemCount > previousTotalItemCount) {
                loading = false;
                previousTotalItemCount = totalItemCount;
            }
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }

        View v = mLinearLayoutManager.findViewByPosition(firstVisibleItem);
        TextView view = (TextView) v.findViewById(R.id.id_home_list_time);

        String text = view.getText().toString();
        if (dy > 0) {
            if (v.getTop() >= -10 && v.getTop() <= 10) {
                if (!info.equals(text)) {
                    EventBus.getDefault().post(new ChangeToolbarTextEvent(text));
                    info = text;
                    infos.add(info);
                }
            }
        } else if (dy < 0) {
            if (firstVisibleItem != 0) {
                if (v.getBottom() >= -10 && v.getBottom() <= 10) {

                    if (!info.equals(text)) {
                        EventBus.getDefault().post(new ChangeToolbarTextEvent(text));
                        infos.remove(info);
                        info = text;
                    }
                }
            } else {
                if (v.getTop() >= 0) {
                    if (infos.size() > 1) {
                        infos.remove(infos.size() - 1);
                    }
                    info = infos.get(infos.size() - 1);
                    EventBus.getDefault().post(new ChangeToolbarTextEvent(info));
                }
            }
        }

    }

    public abstract void onLoadMore(int currentPage);


}
