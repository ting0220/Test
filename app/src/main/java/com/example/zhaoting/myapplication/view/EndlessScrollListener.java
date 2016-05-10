package com.example.zhaoting.myapplication.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.events.ChangeToolbarTextEvent;
import com.example.zhaoting.utils.Utils;

import org.greenrobot.eventbus.EventBus;

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

    String info = "首页";

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
        View v = mLinearLayoutManager.findViewByPosition(firstVisibleItem);
        TextView view = (TextView) v.findViewById(R.id.id_home_list_time);

        if (view.getVisibility() == View.VISIBLE) {
            String text = view.getText().toString();
            if (!info.equals(text)) {
                if (v.getTop() == 0) {
                    EventBus.getDefault().post(new ChangeToolbarTextEvent(text));
                    info = text;
                }

            }
        }
        if (firstVisibleItem>1){
            firstVisibleItem=firstVisibleItem-1;
        }
        View vi = mLinearLayoutManager.findViewByPosition(firstVisibleItem-1);
        TextView viewv = (TextView) v.findViewById(R.id.id_home_list_time);

         if (viewv.getVisibility() == View.GONE) {
            String text = viewv.getText().toString();
            if (!info.equals(text)) {
                if (v.getBottom() == 0) {
                    EventBus.getDefault().post(new ChangeToolbarTextEvent(text));
                    info = text;
                }

            }
        }

// View v=mLinearLayoutManager.findViewByPosition(firstVisibleItem);
// TextView view= (TextView) v.findViewById(R.id.id_home_list_time);
// if (view.getVisibility()==View.VISIBLE){
// String text=view.getText().toString();
// if (!info.equals(text)){
// EventBus.getDefault().post(new ChangeToolbarTextEvent(text));
// info=text;
// Utils.getInstance().ToastShort(String.valueOf(v.getTop()));
// }
// }


    }

    public abstract void onLoadMore(int currentPage);


}
