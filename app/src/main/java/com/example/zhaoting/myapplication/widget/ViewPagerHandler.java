package com.example.zhaoting.myapplication.widget;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;

/**
 * Created by zhaoting on 16/6/16.
 */
public class ViewPagerHandler extends Handler {
    public static final int MSG_UPDATE_IMAGE = 0;// 更新图片
    public static final int MSG_STOP_UPDATE_IMAGE = 1;//停止更新图片
    public static final int MSG_RECOVER_UPDATE_IMAGE = 2;//恢复更新图片
    public static final int MSG_PAGE_CHANGED = 3;//记录手动滑动时的页数变化
    public static final int MSG_DELAY = 5000;//记录换页的时间间隔


    private int mCurrentItem;
    private boolean mIsStop;
    private ViewPager mViewPager;
    private int mSize;





    public ViewPagerHandler(int currentItem, boolean isStop, ViewPager viewPager, int size) {
        mCurrentItem = currentItem;
        mIsStop = isStop;
        mViewPager = viewPager;
        mSize = size;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        if (this.hasMessages(MSG_UPDATE_IMAGE)) {
            this.removeMessages(MSG_UPDATE_IMAGE);
        }
        switch (msg.what) {
            case MSG_UPDATE_IMAGE: {
                int n = (mCurrentItem + 1) % mSize;
                mViewPager.setCurrentItem(n);
                if (!mIsStop) {
                    this.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                }
            }
            break;
            case MSG_STOP_UPDATE_IMAGE: {

            }
            break;
            case MSG_RECOVER_UPDATE_IMAGE: {
                if (!mIsStop) {
                    this.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                }
            }
            break;
            case MSG_PAGE_CHANGED: {
                mCurrentItem = msg.arg1;
            }
            break;
        }
    }

    public void setStop(boolean isStop) {
        mIsStop = isStop;
    }
}
