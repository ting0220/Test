package com.example.zhaoting.myapplication.presenter;

import com.example.zhaoting.myapplication.bean.HomeBean;
import com.example.zhaoting.myapplication.model.home.HomeListener;
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
        mHomeModel.getHomeList(new HomeListener() {
//            @Override
//            public void onTopSuccess(List<HomeBean.TopStoriesBean> mTopList) {
//                mHomeView.setTopView(mTopList);
//            }
//
//            @Override
//            public void onSuccess(List<HomeBean.StoriesBean> mList) {
//                mHomeView.setListView(mList);
//            }

            @Override
            public void onSuccess(HomeBean data) {
                mHomeView.setData(data);
            }

            @Override
            public void onError() {
                mHomeView.onError();
            }
        },url);
    }
}
