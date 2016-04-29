package com.example.zhaoting.myapplication.model.drawer;

import com.example.zhaoting.myapplication.bean.DrawerBean;

import java.util.List;

/**
 * Created by zhaoting on 16/4/28.
 */
public interface DrawerListener {
    void onSuccess(List<DrawerBean.OthersBean> s);
    void onError();
}
