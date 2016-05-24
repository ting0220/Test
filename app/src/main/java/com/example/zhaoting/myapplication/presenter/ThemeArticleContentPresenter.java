package com.example.zhaoting.myapplication.presenter;

import com.example.zhaoting.myapplication.bean.ArticleContentBean;
import com.example.zhaoting.myapplication.model.article.ArticleContentListener;
import com.example.zhaoting.myapplication.model.article.ArticleContentModel;
import com.example.zhaoting.myapplication.model.article.ArticleContentModelImpl;
import com.example.zhaoting.myapplication.model.article.ArticleCssListener;
import com.example.zhaoting.myapplication.view.themeArticleContent.ThemeArticleContentFragment;

/**
 * Created by zhaoting on 16/5/24.
 */
public class ThemeArticleContentPresenter {
    private ArticleContentModel mArticleContentModel;
    private ThemeArticleContentFragment mThemeArticleContentFragment;

    public ThemeArticleContentPresenter(ThemeArticleContentFragment themeArticleContentFragment) {
        mThemeArticleContentFragment = themeArticleContentFragment;
        mArticleContentModel=new ArticleContentModelImpl();
    }

    public void getArticleContent(int id){
        mArticleContentModel.getArticleContent(id, new ArticleContentListener() {
            @Override
            public void onSuccess(ArticleContentBean bean) {
                mThemeArticleContentFragment.onSuccess(bean);
            }

            @Override
            public void onError() {
                mThemeArticleContentFragment.onError();
            }
        });
    }

    public void getArticleCss(String url) {
        mArticleContentModel.getArticleCss(url, new ArticleCssListener() {
            @Override
            public void onCssSuccess(String s) {
                mThemeArticleContentFragment.onCssSuccess(s);
            }

            @Override
            public void onCssError() {
                mThemeArticleContentFragment.onCssError();
            }
        });

    }
}
