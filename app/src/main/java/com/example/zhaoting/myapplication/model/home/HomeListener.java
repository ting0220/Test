package com.example.zhaoting.myapplication.model.home;

import com.example.zhaoting.myapplication.bean.HomeBean;

/**
 * Created by zhaoting on 16/4/29.
 */
public interface HomeListener {
//    void onTopSuccess(List<HomeBean.TopStoriesBean> mTopList);

    void onSuccess(HomeBean mData);

    void onError();
}
