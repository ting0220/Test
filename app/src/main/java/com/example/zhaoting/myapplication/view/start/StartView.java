package com.example.zhaoting.myapplication.view.start;

/**
 * Created by zhaoting on 16/4/25.
 */
public interface StartView {
    void setImg(String url);
    void setText(String text);
    void toMainActivity();
    void showFailedError();
}
