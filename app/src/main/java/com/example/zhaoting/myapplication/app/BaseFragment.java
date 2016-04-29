package com.example.zhaoting.myapplication.app;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhaoting.myapplication.utils.SharedPManager;


/**
 * Created by zhaoting on 15/11/25.
 */
public abstract class BaseFragment extends Fragment implements View.OnTouchListener {
    public View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Context contextTheme = new ContextThemeWrapper(getActivity(), SharedPManager.getInstance().getTheme());
        LayoutInflater localInflater = inflater.cloneInContext(contextTheme);
        mRootView = localInflater.inflate(getFragmentLayout(), container, false);
        initViews();
        initDatas();
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //拦截触摸事件，防止泄漏下去
        view.setOnTouchListener(this);
    }

    public static <T extends BaseFragment> T newInstance(FragmentManager fragmentManager, Class<T> clazz, String tag) {
        T fragment = null;
        String fragmentTag = TextUtils.isEmpty(tag) ? clazz.getName() : tag;
        if (fragmentManager != null) {
            fragment = (T) fragmentManager.findFragmentByTag(fragmentTag);
        }
        if (fragment == null) {
            try {
                fragment = clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fragment;
    }

    /**
     * 用于一些打开次数比较少的页面
     * 有返回上一步
     */
    public <T extends BaseFragment> void replaceFragment(Class<T> clazz) {
        int layout = ((BaseActivity) getActivity()).getFragmentContainerId();
        if (layout == 0) {
            return;
        }
        T fragment = T.newInstance(getFragmentManager(), clazz, null);
        String tag = fragment.getClass().getName();
        getFragmentManager().beginTransaction()
                .replace(layout, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRootView = null;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }


    //抽象方法

    protected abstract int getFragmentLayout();

    protected abstract void initViews();

    protected abstract void initDatas();

}
