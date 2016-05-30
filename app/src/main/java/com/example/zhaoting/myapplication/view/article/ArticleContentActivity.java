package com.example.zhaoting.myapplication.view.article;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.bean.ArticleContentBean;
import com.example.zhaoting.myapplication.presenter.ArticleContentActivityPresenter;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by zhaoting on 16/5/30.
 */
public class ArticleContentActivity extends Activity implements ArticleContentView {
    private CoordinatorLayout mCoordinatorLayout;
    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolBar;
    private ImageView mTopImg;
    private TextView mTopTitle;
    private TextView mTopText;
    private WebView mWebView;

    private int id;
    private String body;
    String fileName = "article_content.css";
    private String data;
    private ArticleContentActivityPresenter marticleContentActivityPresenter = new ArticleContentActivityPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_content);
        initViews();
        initDatas();
    }

    private void initDatas() {
        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        marticleContentActivityPresenter.getArticleContent(id);
    }

    private void initViews() {
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.id_article_content_coordinator_layout);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.id_article_content_appbar_layout);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mToolBar = (Toolbar) findViewById(R.id.id_article_content_toolbar);
        mTopImg = (ImageView) findViewById(R.id.id_article_content_top_img);
        mTopTitle = (TextView) findViewById(R.id.id_article_content_top_title);
        mTopText = (TextView) findViewById(R.id.id_article_content_top_text);
        mWebView = (WebView) findViewById(R.id.id_article_content_webview);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onSuccess(ArticleContentBean bean) {
        Picasso.with(this).load(bean.getImage()).into(mTopImg);
        mTopTitle.setText(bean.getTitle());
        mTopText.setText(bean.getImage_source());
        marticleContentActivityPresenter.getArticleCss(bean.getCss().get(0));
        body = bean.getBody();
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
        data = "<html><head>" + css + "</head><body>" + body + "</body></html>";
        data = data.replace("<div class=\"img-place-holder\">", "");
        mWebView.loadDataWithBaseURL("", data, "text/html", "UTF-8", null);

    }

    @Override
    public void onCssError() {

    }
}
