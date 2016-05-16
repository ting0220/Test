package com.example.zhaoting.myapplication.model.article;

import com.example.zhaoting.myapplication.bean.ArticleContentBean;

/**
 * Created by zhaoting on 16/5/16.
 */
public interface ArticleContentListener {
    void onSuccess(ArticleContentBean bean);

    void onError();

}
