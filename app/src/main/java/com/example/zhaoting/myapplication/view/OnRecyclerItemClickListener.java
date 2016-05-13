package com.example.zhaoting.myapplication.view;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhaoting on 16/5/13.
 */
public abstract class OnRecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private GestureDetectorCompat mGestureDetectorCompat;
    private RecyclerView mRecyclerView;


    public OnRecyclerItemClickListener(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mGestureDetectorCompat = new GestureDetectorCompat(recyclerView.getContext(), new ItemTouchHelperGestureListener());
    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public abstract void onItemClick(View view, int position);

    public abstract void onItemLongClick(View view, int position);

    public class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View child = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
//                RecyclerView.ViewHolder vh = mRecyclerView.getChildViewHolder(child);
//                onItemClick(vh);
                onItemClick(child, mRecyclerView.getChildAdapterPosition(child));
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            View child = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                onItemLongClick(child, mRecyclerView.getChildAdapterPosition(child));


            }
        }

//    /*用户按下屏幕就会触发*/
//    @Override
//    public boolean onDown(MotionEvent e) {
//        return super.onDown(e);
//    }
//
//    /*如果是按下的时间超过瞬间，而且在按下的时候没有松开或者是拖动的，那么onShowPress就会执行*/
//    @Override
//    public void onShowPress(MotionEvent e) {
//        super.onShowPress(e);
//    }
//
//    /*在屏幕上拖动事件*/
//    @Override
//    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//        return super.onScroll(e1, e2, distanceX, distanceY);
//    }
//
//    /*滑屏，用户按下触摸屏、快速移动后松开*/
//    @Override
//    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//        return super.onFling(e1, e2, velocityX, velocityY);
//    }
//
//    /*单机事件。用来判定该次点击是singleTap而不是DoubleTap，
//      如果连续点击两次就是DoubleTap手势，如果只点击一次，
//      系统等待一段时间后没有收到第二次点击则判定该次点击为SingleTap而不是DoubleTap，
//      然后触发SingleTapConfirmed事件*/
//    @Override
//    public boolean onSingleTapConfirmed(MotionEvent e) {
//        return super.onSingleTapConfirmed(e);
//    }
//
//    /*双击事件*/
//    @Override
//    public boolean onDoubleTap(MotionEvent e) {
//        return super.onDoubleTap(e);
//    }
//
//    /*双击间隔中发生的动作。指触发onDoubleTap以后，在双击之间发生的其他动作*/
//    @Override
//    public boolean onDoubleTapEvent(MotionEvent e) {
//        return super.onDoubleTapEvent(e);
//    }
    }

}
