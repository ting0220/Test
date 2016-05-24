package com.example.zhaoting.myapplication.widget;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.example.zhaoting.myapplication.view.themeArticleContent.ThemeArticleContentFragment;

/**
 * Created by zhaoting on 16/5/24.
 */
public class MyScrollView extends ScrollView {
    private GestureDetectorCompat mDetector;// 手势滑动
    private ThemeArticleContentFragment mFragment;

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MyScrollView(Context context) {
        super(context);
        init(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * fragment中必须加上setFragment的函数，不然左右滑动将不起作用
     *
     * @param fragment
     */
    public void setFragment(ThemeArticleContentFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mDetector.onTouchEvent(ev);
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        mDetector.onTouchEvent(ev);
        return super.onInterceptTouchEvent(ev);
    }

    public void init(Context context) {
        mDetector = new GestureDetectorCompat(context, new MyGestureListener());
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";
        private static final int FILING_MAX_HEIGHT = 40;// 最大高度
        private static final int FLING_MIN_DISTANCE = 30;// 最小宽度
        private static final int FLING_MIN_VELOCITY = 10;// 最小速度
        protected MotionEvent mLastOnDownEvent = null;

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            mLastOnDownEvent = e;
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            // e1可能为空
            if (e1 == null) {
                e1 = mLastOnDownEvent;
            }
            if (e1 == null || e2 == null) {
                return false;
            }

            float dX = e2.getX() - e1.getX();
            float dY = e2.getY() - e1.getY();

            if (Math.abs(dY) < FILING_MAX_HEIGHT && Math.abs(velocityX) >= FLING_MIN_VELOCITY && Math.abs(dX) >= FLING_MIN_DISTANCE) {
                if (dX > 0) {// right
//                    LogUtil.e("MyScrollView", "right");
                    // 关闭文章内页
//                    mFragment.finish();
                } else {// left
//                    LogUtil.e("MyScrollView", "left");
                    // 跳到评论列表
//                    mFragment.getCommentList();
                }
                return true;
            }
            return false;
        }
    }
}
