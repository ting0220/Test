package com.example.zhaoting.myapplication.view.article;

import com.example.zhaoting.myapplication.bean.ArticleContentBean;
import com.example.zhaoting.myapplication.bean.ExtraInfoBean;

/**
 * Created by zhaoting on 16/5/23.
 */
public interface ArticleContentView {
    void onSuccess(ArticleContentBean bean);

    void onError();

    void onCssSuccess(String s);

    void onCssError();

    void setToolBar(ExtraInfoBean bean);
}
