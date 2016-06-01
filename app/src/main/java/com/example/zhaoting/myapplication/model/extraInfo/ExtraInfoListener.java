package com.example.zhaoting.myapplication.model.extraInfo;

import com.example.zhaoting.myapplication.bean.ExtraInfoBean;

/**
 * Created by zhaoting on 16/6/1.
 */
public interface ExtraInfoListener {
    void onSuccess(ExtraInfoBean bean);

    void onError();
}
