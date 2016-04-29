package com.example.zhaoting.myapplication.presenter.start;

import android.os.Handler;

import com.example.zhaoting.myapplication.bean.StartBean;
import com.example.zhaoting.myapplication.model.start.StartListener;
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
        mStartModel.start(width, height, new StartListener() {
            @Override
            public void onSuccess(StartBean s) {
                mStartView.setImg(s.getImg());
                mStartView.setText(s.getText());
                mStartView.toMainActivity();

            }

            @Override
            public void onError() {
                mStartView.showFailedError();
            }
        });
    }

}
