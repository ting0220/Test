package com.example.zhaoting.myapplication.view.article;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.app.BaseFragment;
import com.example.zhaoting.myapplication.bean.ArticleContentBean;
import com.example.zhaoting.myapplication.presenter.ArticleContentPresenter;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by zhaoting on 16/5/13.
 */
public class ArticleContentFragment extends BaseFragment {
    private CoordinatorLayout mCoordinatorLayout;
    private AppBarLayout mAppBarLayout;
    //    private Toolbar mToolbar;
    private WebView mWebView;
    private ImageView mTopImage;
    private TextView mTopTitle;
    private TextView mTopText;

    private int id;
    private ArticleContentPresenter mArticleContentPresenter = new ArticleContentPresenter(this);
    String data = null;
    String fileName = "article_content.css";
    String body = null;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_article_content;
    }

    @Override
    protected void initViews() {
        setHasOptionsMenu(true);
        mCoordinatorLayout = (CoordinatorLayout) mRootView.findViewById(R.id.id_article_content_coordinator_layout);
        mAppBarLayout = (AppBarLayout) mRootView.findViewById(R.id.id_article_content_appbar_layout);
//        mToolbar = (Toolbar) mRootView.findViewById(R.id.id_article_content_toolbar);
        mWebView = (WebView) mRootView.findViewById(R.id.id_article_content_webview);
        mTopImage = (ImageView) mRootView.findViewById(R.id.id_article_content_top_img);
        mTopTitle = (TextView) mRootView.findViewById(R.id.id_article_content_top_title);
        mTopText = (TextView) mRootView.findViewById(R.id.id_article_content_top_text);


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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.toolbar_menu_article_content,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    protected void initDatas() {
        id = getArguments().getInt("id");
        mArticleContentPresenter.getArticleContent(id);

    }

    public void onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();//表示返回webView的上一页面
        }
    }

    public void onSuccess(ArticleContentBean bean) {
        Picasso.with(getActivity()).load(bean.getImage()).into(mTopImage);
        mTopTitle.setText(bean.getTitle());
        mTopText.setText(bean.getImage_source());
        mArticleContentPresenter.getArticleCss(bean.getCss().get(0));
        body = bean.getBody();

    }


    public void onError() {
    }

    public void onCssSuccess(String s) {
        File file = new File(getActivity().getFilesDir(), fileName);
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(s.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String css = "<link rel=\"stylesheet\" href=\"file:///" + getActivity().getFilesDir() + "/" + fileName + "\"type=\"text/css\" />";
        data = "<html><head>" + css + "</head><body>" + body + "</body></html>";
        data = data.replace("<div class=\"img-place-holder\">", "");
        mWebView.loadDataWithBaseURL("", data, "text/html", "UTF-8", null);

    }

    public void onCssError() {
    }


}
