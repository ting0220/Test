package com.example.zhaoting.myapplication.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by zhaoting on 16/5/30.
 */
public class ToolBarBehavior extends CoordinatorLayout.Behavior<View> {
    private int ScrollHeight;

    public ToolBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        if (target.getTop() < 600) {
            return false;
        } else {
            return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
        }
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
                                  int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        if (target.getTop() == 0) {
            if (dy > 0) {
                child.setAlpha(0f);
                child.setVisibility(View.GONE);
            } else {
                child.setVisibility(View.VISIBLE);
                child.setAlpha(1f);
            }
        } else {
            ScrollHeight += dy;
            if (ScrollHeight > 600) {
                ScrollHeight = 600;
            } else if (ScrollHeight < 0) {
                ScrollHeight = 0;
            }
            Log.d("pre", "onNestedPreScroll: "+String.valueOf(dy));
            int x = target.getTop() - 110;
            float y = (float) x / 490;
            if (y > 0 && y < 1) {
                child.setVisibility(View.VISIBLE);
                child.setAlpha(y);
            } else if (y <= 0) {
                if (child.getVisibility() == View.VISIBLE) {
                    child.setAlpha(0f);
                }
                child.setVisibility(View.GONE);
            } else if (y >= 1) {
                child.setVisibility(View.VISIBLE);
                child.setAlpha(1f);
            }
        }


    }

}
