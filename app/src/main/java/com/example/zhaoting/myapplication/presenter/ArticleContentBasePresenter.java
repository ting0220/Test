package com.example.zhaoting.myapplication.presenter;

import com.example.zhaoting.myapplication.bean.ArticleContentBean;
import com.example.zhaoting.myapplication.bean.ExtraInfoBean;
import com.example.zhaoting.myapplication.model.OnListener;
import com.example.zhaoting.myapplication.model.article.ArticleContentModel;
import com.example.zhaoting.myapplication.model.article.ArticleContentModelImpl;
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
        mExtraInfoModel = new ExtraInfoModelImpl();
    }

    public void getArticleContent(int id) {
        mArticleContentModel.getArticleContent(id, new OnListener() {
            @Override
            public void onSuccess(Object s) {
                ArticleContentBean bean = (ArticleContentBean) s;
                mArticleContentView.onSuccess(bean);
            }

            @Override
            public void onError() {
                mArticleContentView.onError();
            }

            @Override
            public void onNoConnected() {

            }
        });
    }

    public void getArticleCss(String url) {
        mArticleContentModel.getArticleCss(url, new OnListener() {
            @Override
            public void onSuccess(Object s) {
                String str = (String) s;
                mArticleContentView.onCssSuccess(str);
            }

            @Override
            public void onError() {
                mArticleContentView.onCssError();
            }

            @Override
            public void onNoConnected() {

            }
        });

    }

    public void getExtraInfo(int id) {
        mExtraInfoModel.getExtraInfo(id, new OnListener() {
            @Override
            public void onSuccess(Object s) {
                ExtraInfoBean bean = (ExtraInfoBean) s;
                mArticleContentView.setToolBar(bean);
            }

            @Override
            public void onError() {

            }

            @Override
            public void onNoConnected() {

            }
        });
    }
}
