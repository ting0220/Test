package com.example.zhaoting.myapplication.presenter;

import com.example.zhaoting.myapplication.bean.ArticleContentBean;
import com.example.zhaoting.myapplication.model.article.ArticleContentListener;
import com.example.zhaoting.myapplication.model.article.ArticleContentModel;
import com.example.zhaoting.myapplication.model.article.ArticleContentModelImpl;
import com.example.zhaoting.myapplication.model.article.ArticleCssListener;
import com.example.zhaoting.myapplication.view.article.ArticleContentActivity;

/**
 * Created by zhaoting on 16/5/30.
 */
public class ArticleContentActivityPresenter {
    private ArticleContentModel mArticleContentModel;
    private ArticleContentActivity mArticleContentActivity;

    public ArticleContentActivityPresenter(ArticleContentActivity articleContentActivity) {
        mArticleContentActivity = articleContentActivity;
        mArticleContentModel = new ArticleContentModelImpl();
    }

    public void getArticleContent(int id) {
        mArticleContentModel.getArticleContent(id, new ArticleContentListener() {
            @Override
            public void onSuccess(ArticleContentBean bean) {
                mArticleContentActivity.onSuccess(bean);
            }

            @Override
            public void onError() {
                mArticleContentActivity.onError();

            }
        });
    }

    public void getArticleCss(String url) {
        mArticleContentModel.getArticleCss(url, new ArticleCssListener() {
            @Override
            public void onCssSuccess(String s) {
                mArticleContentActivity.onCssSuccess(s);
            }

            @Override
            public void onCssError() {
                mArticleContentActivity.onCssError();
            }
        });

    }
}
