package com.example.zhaoting.myapplication.presenter;

import com.example.zhaoting.myapplication.bean.HomeBean;
import com.example.zhaoting.myapplication.model.OnListener;
import com.example.zhaoting.myapplication.model.home.HomeModel;
import com.example.zhaoting.myapplication.model.home.HomeModelImpl;
import com.example.zhaoting.myapplication.view.home.HomeView;

/**
 * Created by zhaoting on 16/5/3.
 */
public class HomePresenter {
    private HomeModel mHomeModel;
    private HomeView mHomeView;

    public HomePresenter(HomeView homeView) {
        mHomeView = homeView;
        mHomeModel = new HomeModelImpl();
    }

    public void getHomeList(String url) {
        mHomeModel.getHomeList(new OnListener() {

            @Override
            public void onSuccess(Object s) {
                HomeBean bean = (HomeBean) s;
                mHomeView.setData(bean);
            }

            @Override
            public void onError() {
                mHomeView.onError();
            }

            @Override
            public void onNoConnected() {

            }
        }, url);
    }
}
