package com.example.zhaoting.myapplication.presenter;

import android.os.Handler;

import com.example.zhaoting.myapplication.bean.StartBean;
import com.example.zhaoting.myapplication.model.OnListener;
import com.example.zhaoting.myapplication.model.start.StartModel;
import com.example.zhaoting.myapplication.model.start.StartModelImpl;
import com.example.zhaoting.myapplication.view.start.StartView;
import com.example.zhaoting.utils.Utils;


/**
 * Created by zhaoting on 16/4/25.
 */
public class StartPresenter {
    private StartModel mStartModel;
    private StartView mStartView;
    private Handler mHandler = new Handler();


    public StartPresenter(StartView startView) {
        mStartView = startView;
        mStartModel = new StartModelImpl();
    }

    public void login() {
        String width = String.valueOf(Utils.getInstance().getScreenWidth());
        String height = String.valueOf(Utils.getInstance().getScreenHeight());
        mStartModel.start(width, height, new OnListener() {
            @Override
            public void onSuccess(Object s) {
                StartBean bean = (StartBean) s;
                mStartView.onSuccess(bean);
            }

            @Override
            public void onError() {
                mStartView.onError();
            }

            @Override
            public void onNoConnected() {
                mStartView.onNoConnected();
            }
        });
    }

}
