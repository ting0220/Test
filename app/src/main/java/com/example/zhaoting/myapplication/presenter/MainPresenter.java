package com.example.zhaoting.myapplication.presenter;

import com.example.zhaoting.myapplication.bean.DrawerBean;
import com.example.zhaoting.myapplication.model.OnListener;
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
        mDrawerModel.getDrawer(new OnListener() {

            @Override
            public void onSuccess(Object s) {
                List<DrawerBean.OthersBean> bean= (List<DrawerBean.OthersBean>) s;
                mMainView.setDrawer(bean);
            }

            @Override
            public void onError() {
                mMainView.onError();

            }

            @Override
            public void onNoConnected() {
                mMainView.onNoConnected();

            }
        });
    }

}
