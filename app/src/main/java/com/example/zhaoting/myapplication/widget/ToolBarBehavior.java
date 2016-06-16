package com.example.zhaoting.myapplication.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
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

    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
                                  int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        if (target.getTop() == 0) {
            if (dy > 0) {
                child.setAlpha(0.00001f);
            } else {
                child.setVisibility(View.VISIBLE);
                child.setAlpha(1f);
            }
        } else {
            int x = target.getTop() - 110;
            float y = (float) x / 490;
            if (y > 0 && y < 1) {
                child.setVisibility(View.VISIBLE);
                child.setAlpha(y);
            } else if (y <= 0) {
                if (child.getVisibility() == View.VISIBLE) {
                    child.setAlpha(0.00001f);
                }
            } else if (y >= 1) {
                child.setVisibility(View.VISIBLE);
                child.setAlpha(1f);
            }
        }


    }

}
