package com.example.zhaoting.myapplication.presenter.main;

import com.example.zhaoting.myapplication.bean.DrawerBean;
import com.example.zhaoting.myapplication.model.drawer.DrawerListener;
import com.example.zhaoting.myapplication.model.drawer.DrawerModel;
import com.example.zhaoting.myapplication.model.drawer.DrawerModelImpl;
import com.example.zhaoting.myapplication.view.main.MainView;

import java.util.List;

/**
 * Created by zhaoting on 16/4/27.
 */
public class MainPresenter {
    private DrawerModel mDrawerModel;
    private MainView mMainView;

    public MainPresenter(MainView mainView) {
        mMainView = mainView;
        mDrawerModel = new DrawerModelImpl();
    }

    public void getDrawerList() {
        mDrawerModel.getDrawer(new DrawerListener() {
            @Override
            public void onSuccess(List<DrawerBean.OthersBean> s) {
                mMainView.setDrawer(s);
            }

            @Override
            public void onError() {

            }
        });
    }

}
