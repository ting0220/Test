package com.example.zhaoting.myapplication.widget;

import android.content.Context;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.adapter.HomeTopViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoting on 16/6/16.
 */
public class AutoScrollViewPagerLayout extends RelativeLayout {
    private ViewPager mViewPager;
    private LinearLayout mLinearLayout;
    private TextView mTextView;

    private List<View> mList = new ArrayList<>();
    private Context mContext;
    private int mPointWidth;
    private ViewPagerHandler mViewPagetHandler;
    private PagerAdapter adapter;


    public AutoScrollViewPagerLayout(Context context) {
        this(context, null);
    }

    public AutoScrollViewPagerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.fragment_home_top, this, true);
        mViewPager = (ViewPager) findViewById(R.id.id_view_pager);
        mLinearLayout = (LinearLayout) findViewById(R.id.id_point_group);
        mTextView = (TextView) findViewById(R.id.id_orange_point);
        mContext = context;
    }

    public void setListViews(List<View> list) {
        mList = list;
        addPoints(list.size());

        //获取圆点间的间距
        mLinearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mLinearLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mPointWidth = mLinearLayout.getChildAt(1).getLeft() - mLinearLayout.getChildAt(0).getLeft();

            }
        });

        initAdapter(mViewPager, adapter);
        initHandler(mViewPager,mTextView, mViewPagetHandler, list.size());

    }

    private void initHandler(ViewPager viewPager, final TextView textView, ViewPagerHandler viewPagetHandler, int size) {
        viewPagetHandler = new ViewPagerHandler(0, false, viewPager, size);


        viewPagetHandler.sendEmptyMessage(viewPagetHandler.MSG_RECOVER_UPDATE_IMAGE);
        viewPagetHandler.sendEmptyMessageDelayed(viewPagetHandler.MSG_UPDATE_IMAGE, viewPagetHandler.MSG_DELAY);
        final ViewPagerHandler finalViewPagetHandler = viewPagetHandler;
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()

                                          {
                                              @Override
                                              public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                                  int len = (int) ((mPointWidth * positionOffset) + position * mPointWidth);
                                                  RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) textView.getLayoutParams();
                                                  params.leftMargin = len;
                                                  textView.setLayoutParams(params);
                                              }

                                              @Override
                                              public void onPageSelected(int position) {
                                                  Message msg = new Message();
                                                  msg.what = finalViewPagetHandler.MSG_PAGE_CHANGED;
                                                  msg.arg1 = position;
                                                  msg.arg2 = 0;
                                                  finalViewPagetHandler.sendMessage(msg);
                                              }

                                              @Override
                                              public void onPageScrollStateChanged(int state) {
                                                  switch (state) {
                                                      case ViewPager.SCROLL_STATE_IDLE: {
                                                          finalViewPagetHandler.sendEmptyMessageDelayed(finalViewPagetHandler.MSG_UPDATE_IMAGE, finalViewPagetHandler.MSG_DELAY);
                                                      }
                                                      break;
                                                      case ViewPager.SCROLL_STATE_DRAGGING: {
                                                          finalViewPagetHandler.sendEmptyMessage(finalViewPagetHandler.MSG_STOP_UPDATE_IMAGE);
                                                      }
                                                      break;
                                                  }

                                              }
                                          }

        );
    }


    private void initAdapter(ViewPager viewpager, PagerAdapter adapter) {
        adapter = new HomeTopViewPagerAdapter(mList);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);
    }


    private void addPoints(int size) {
        for (int i = 0; i < size; i++) {
            View point = new View(mContext);
            point.setBackgroundResource(R.color.gray);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
            if (i > 0) {
                params.leftMargin = 20;
            }
            point.setLayoutParams(params);
            mLinearLayout.addView(point);
        }
    }


    public void clearLinearLayout() {
        mLinearLayout.removeAllViews();
    }

    public void setStop(boolean isStop) {
        mViewPagetHandler.setStop(isStop);
    }
}
