package com.example.zhaoting.myapplication.model.start;

import com.example.zhaoting.myapplication.bean.StartBean;

/**
 * Created by zhaoting on 16/4/28.
 */
public interface StartListener {
    void onSuccess(StartBean s);
    void onError();
}
