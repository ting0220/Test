package com.example.zhaoting.myapplication.app;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.zhaoting.myapplication.R;


/**
 * Created by zhaoting on 15/11/25.
 * 此页的Log打印信息是为了以后友盟页面统计而写的，其中true的表示这个页面的打开，false表示这个页面的关闭
 */
public abstract class BaseActivity extends AppCompatActivity {
    //还没有想到什么时候用，如果最后没用就删除两个定义的变量
    protected BaseFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        int themeId = SharedPManager.getInstance().getTheme();
//        setTheme(themeId);
    }

    public BaseFragment getCurrentFragment() {
        return currentFragment;
    }

    /**
     * 首页替换Fragment
     */
    public <T extends BaseFragment> void changeFragment(T fragment) {
        changeFragment(fragment, fragment.getClass().getName());
    }

    public <T extends BaseFragment> void changeFragment(T fragment, String tag) {
        changeFragment(fragment, tag, getFragmentContainerId());
    }

    public <T extends BaseFragment> void changeFragment(T fragment, String tag, int res) {
        changeFragment(fragment, tag, res, null);
    }

    public <T extends BaseFragment> void changeFragment(T fragment, String tag, int res, Bundle bundle) {
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        if (res == 0) {
            res = getFragmentContainerId();
        }
        if (fragment.equals(currentFragment)) {
            return;
        }
        if (tag == null) {
            tag = fragment.getClass().getName();
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragment_fade_out);

        if (!fragment.isAdded()) {
            transaction.add(res, fragment, tag);
        }
        if (fragment.isHidden()) {
            transaction.show(fragment);
        }
        if (currentFragment != null && currentFragment.isVisible()) {
            transaction.hide(currentFragment);
        }
        currentFragment = fragment;
        transaction.commit();
    }


    public <T extends BaseFragment> T newInstanceFragment(Class<T> clazz){
        return T.newInstance(getSupportFragmentManager(),clazz,clazz.getName());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    //获取fragment所在布局
    public abstract int getFragmentContainerId();
}






