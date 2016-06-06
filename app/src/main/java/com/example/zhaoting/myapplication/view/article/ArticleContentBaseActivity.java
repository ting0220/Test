package com.example.zhaoting.myapplication.view.article;

import com.example.zhaoting.myapplication.app.BaseActivity;
import com.example.zhaoting.myapplication.bean.ArticleContentBean;
import com.example.zhaoting.myapplication.bean.ExtraInfoBean;
import com.example.zhaoting.myapplication.presenter.ArticleContentBasePresenter;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by zhaoting on 16/6/1.
 */
public abstract class ArticleContentBaseActivity extends BaseActivity implements ArticleContentView {
    private int id;
    private String fileName = "article_content.css";
    private ArticleContentBean bean;
    private String data;
    private ArticleContentBasePresenter mArticleContentBasePresenter = new ArticleContentBasePresenter(this);


    protected void initDatas() {
        id = getIntent().getExtras().getInt("id");
        mArticleContentBasePresenter.getArticleContent(id);
        mArticleContentBasePresenter.getExtraInfo(id);

    }

    @Override
    public void onSuccess(ArticleContentBean bean) {
        this.bean = bean;
        mArticleContentBasePresenter.getArticleCss(bean.getCss().get(0));
    }

    @Override
    public void onError() {

    }

    @Override
    public void onCssSuccess(String s) {
        File file = new File(this.getFilesDir(), fileName);
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(s.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String css = "<link rel=\"stylesheet\" href=\"file:///" + this.getFilesDir() + "/" + fileName + "\"type=\"text/css\" />";
        data = "<html><head>" + css + "</head><body>" + bean.getBody() + "</body></html>";
        data = data.replace("<div class=\"img-place-holder\">", "");
        setContentData(bean,data);
    }


    @Override
    public void onCssError() {

    }

    @Override
    public void setToolBar(ExtraInfoBean bean) {
        setToolBarData(bean);
    }


    public abstract void setToolBarData(ExtraInfoBean bean);

    public abstract void setContentData(ArticleContentBean bean,String data);


}
