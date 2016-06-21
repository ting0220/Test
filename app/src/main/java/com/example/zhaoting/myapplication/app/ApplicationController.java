package com.example.zhaoting.myapplication.app;

import android.support.v7.app.AppCompatDelegate;

import com.example.zhaoting.myapplication.utils.SharedPManager;
import com.example.zhaoting.utils.IOUtils;
import com.example.zhaoting.utils.NetUtils;
import com.example.zhaoting.utils.Utils;

import org.litepal.LitePalApplication;

/**
 * Created by zhaoting on 16/4/25.
 */
public class ApplicationController extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        Utils.getInstance().init(this);
        IOUtils.getInstance().init(this);
        NetUtils.getInstance().init(this);
//        VolleyUtil.getInstance().init(this);
        SharedPManager.getInstance().init(this);
        int isDay = SharedPManager.getInstance().getTheme();
        if (isDay == 0) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

    }
}
