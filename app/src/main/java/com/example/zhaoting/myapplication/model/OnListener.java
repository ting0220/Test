package com.example.zhaoting.myapplication.model;

/**
 * Created by zhaoting on 16/1/5.
 */
public interface OnListener {
    void onSuccess(Object s);

    void onError();

    void onNoConnected();
}
