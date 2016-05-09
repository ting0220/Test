package com.example.zhaoting.myapplication.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhaoting on 16/5/3.
 */
public class HomeTopViewPagerAdapter extends PagerAdapter {
    private List<View> mListViews;

    public HomeTopViewPagerAdapter(List<View> listViews) {
        mListViews = listViews;
    }



    @Override
    public int getCount() {
        return mListViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mListViews.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mListViews.get(position));
        return mListViews.get(position);
    }
}
