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

    public ToolBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {

        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);

    }


    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
                                  int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        Log.d("pre", "onNestedPreScroll: target.getY()" + target.getY());
        Log.d("pre", "onNestedPreScroll: target.getScrollY()" + target.getScrollY());
        Log.d("pre", "onNestedPreScroll: coordinatorLayout.getScrollY()" + coordinatorLayout.getScrollY());
        Log.d("pre", "onNestedPreScroll: coordinatorLayout.getY()" + coordinatorLayout.getY());
        Log.d("pre", "onNestedPreScroll: target.getPivotY()" + target.getPivotY());
        Log.d("pre", "onNestedPreScroll: dy==" + dy);
        if (target.getTop() == 0) {
            if (dy > 0) {
                child.setAlpha(0f);
                child.setVisibility(View.GONE);
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
