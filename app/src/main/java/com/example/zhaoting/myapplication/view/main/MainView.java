package com.example.zhaoting.myapplication.view.main;

import com.example.zhaoting.myapplication.bean.DrawerBean;

import java.util.List;

/**
 * Created by zhaoting on 16/4/27.
 */
public interface MainView {
    void setDrawer(List<DrawerBean.OthersBean> mList);

    void onError();

    void onNoConnected();
}
