package com.example.zhaoting.myapplication.app;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.example.zhaoting.myapplication.utils.SharedPManager;
import com.example.zhaoting.utils.Utils;

/**
 * Created by zhaoting on 16/4/25.
 */
public class ApplicationController extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        Utils.getInstance().init(this);
//        VolleyUtil.getInstance().init(this);
        SharedPManager.getInstance().init(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }
}
