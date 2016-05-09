package com.example.zhaoting.myapplication.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zhaoting.utils.Utils;

/**
 * Created by zhaoting on 16/5/9.
 */
public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {
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

    public EndlessScrollListener(RecyclerView recyclerView) {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof LinearLayoutManager) {
            this.mLinearLayoutManager = (LinearLayoutManager) manager;
        } else {
            Utils.getInstance().ToastShort("Please change the layoutManager to LinearLayout");
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

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
    }

    public abstract void onLoadMore(int currentPage);
}
