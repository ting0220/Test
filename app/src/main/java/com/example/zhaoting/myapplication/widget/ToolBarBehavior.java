package com.example.zhaoting.myapplication.widget;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * Created by zhaoting on 16/5/30.
 */
public class ToolBarBehavior extends CoordinatorLayout.Behavior<View> {
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
//        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        int leftScrolled = target.getScrollY();
        if (leftScrolled>0){
            child.setAlpha(0f);
            child.setVisibility(View.VISIBLE);
            child.animate().alpha(1f)
        }else{

        }
    }
}
