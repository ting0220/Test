package com.example.zhaoting.myapplication.presenter;

import com.example.zhaoting.myapplication.bean.OtherThemeBean;
import com.example.zhaoting.myapplication.model.OnListener;
import com.example.zhaoting.myapplication.model.otherTheme.OtherThemeModel;
import com.example.zhaoting.myapplication.model.otherTheme.OtherThemeModelImpl;
import com.example.zhaoting.myapplication.view.otherTheme.OtherThemeView;

/**
 * Created by zhaoting on 16/5/12.
 */
public class OtherThemePresenter {
    private OtherThemeModel mOtherThemeModel;
    private OtherThemeView mOtherThemeView;

    public OtherThemePresenter(OtherThemeView otherThemeView) {
        mOtherThemeView = otherThemeView;
        mOtherThemeModel = new OtherThemeModelImpl();
    }

    public void getOtherThemeList(String s) {
        mOtherThemeModel.getOtherTheme(s, new OnListener() {

            @Override
            public void onSuccess(Object s) {
                OtherThemeBean bean = (OtherThemeBean) s;
                mOtherThemeView.setData(bean);


            }

            @Override
            public void onError() {
                mOtherThemeView.onError();
            }

            @Override
            public void onNoConnected() {
                mOtherThemeView.onNoConnected();

            }
        });
    }
}
