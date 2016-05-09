package com.example.zhaoting.myapplication.view.home;

import com.example.zhaoting.myapplication.bean.HomeBean;

/**
 * Created by zhaoting on 16/5/3.
 */
public interface HomeView {


    void onRefreshList();
    void setData(HomeBean data);

    void onError();
}
