package com.example.zhaoting.myapplication.view.start;

import com.example.zhaoting.myapplication.bean.StartBean;

/**
 * Created by zhaoting on 16/4/25.
 */
public interface StartView {

    void onSuccess(StartBean s);

    void onError();
    void onNoConnected();
}
