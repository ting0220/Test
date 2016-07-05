package com.example.zhaoting.myapplication.app;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by zhaoting on 15/11/25.
 */
public abstract class BaseFragment extends Fragment implements View.OnTouchListener {
    public View mRootView;
    protected Context mContext;

    private static Bundle mBundle;

    public Context getHoldingContext() {
        return mContext;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getFragmentLayout(), container, false);
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
        replaceFragment(clazz, null, null);
    }


    public <T extends BaseFragment> void replaceFragment(Class<T> clazz, String tag, Bundle bundle) {
        int layout = ((BaseActivity) getHoldingContext()).getFragmentContainerId();
        if (layout == 0) {
            return;
        }
        T fragment = T.newInstance(getFragmentManager(), clazz, null);
        if (tag == null) {
            tag = fragment.getClass().getName();
        }
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        if (((BaseActivity) getHoldingContext()).currentFragment != null) {
            if (tag.equals(((BaseActivity) getHoldingContext()).currentFragment.getTag())) {
                return;
            }
        }

        getFragmentManager().beginTransaction()
                .replace(layout, fragment, tag)
                .addToBackStack(tag)
                .commit();
        ((BaseActivity) getHoldingContext()).currentFragment = fragment;
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
