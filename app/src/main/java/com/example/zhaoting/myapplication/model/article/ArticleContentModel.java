package com.example.zhaoting.myapplication.model.article;

import com.example.zhaoting.myapplication.model.OnListener;

/**
 * Created by zhaoting on 16/5/16.
 */
public interface ArticleContentModel {
    void getArticleContent(int id, OnListener listener);

    void getArticleCss(String url, OnListener listener);
}
