package com.example.zhaoting.myapplication.presenter;

import com.example.zhaoting.myapplication.bean.ArticleContentBean;
import com.example.zhaoting.myapplication.bean.ExtraInfoBean;
import com.example.zhaoting.myapplication.model.article.ArticleContentListener;
import com.example.zhaoting.myapplication.model.article.ArticleContentModel;
import com.example.zhaoting.myapplication.model.article.ArticleContentModelImpl;
import com.example.zhaoting.myapplication.model.article.ArticleCssListener;
import com.example.zhaoting.myapplication.model.extraInfo.ExtraInfoListener;
import com.example.zhaoting.myapplication.model.extraInfo.ExtraInfoModel;
import com.example.zhaoting.myapplication.model.extraInfo.ExtraInfoModelImpl;
import com.example.zhaoting.myapplication.view.article.ArticleContentView;

/**
 * Created by zhaoting on 16/6/1.
 */
public class ArticleContentBasePresenter {
    private ArticleContentModel mArticleContentModel;
    private ExtraInfoModel mExtraInfoModel;
    private ArticleContentView mArticleContentView;

    public ArticleContentBasePresenter(ArticleContentView articleContentView) {
        mArticleContentView = articleContentView;
        mArticleContentModel = new ArticleContentModelImpl();
        mExtraInfoModel=new ExtraInfoModelImpl();
    }

    public void getArticleContent(int id) {
        mArticleContentModel.getArticleContent(id, new ArticleContentListener() {
            @Override
            public void onSuccess(ArticleContentBean bean) {
                mArticleContentView.onSuccess(bean);
            }

            @Override
            public void onError() {
                mArticleContentView.onError();
            }
        });
    }

    public void getArticleCss(String url) {
        mArticleContentModel.getArticleCss(url, new ArticleCssListener() {
            @Override
            public void onCssSuccess(String s) {
                mArticleContentView.onCssSuccess(s);
            }

            @Override
            public void onCssError() {
                mArticleContentView.onCssError();
            }
        });

    }

    public void getExtraInfo(int id) {
        mExtraInfoModel.getExtraInfo(id, new ExtraInfoListener() {
            @Override
            public void onSuccess(ExtraInfoBean bean) {
                mArticleContentView.setToolBar(bean);
            }

            @Override
            public void onError() {

            }
        });
    }
}
