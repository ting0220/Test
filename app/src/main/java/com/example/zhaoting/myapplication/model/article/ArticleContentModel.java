package com.example.zhaoting.myapplication.model.article;

/**
 * Created by zhaoting on 16/5/16.
 */
public interface ArticleContentModel {
    void getArticleContent(int id, ArticleContentListener listener);

    void getArticleCss(String url, ArticleCssListener listener);
}
