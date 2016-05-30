package com.example.zhaoting.myapplication.view.article;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.app.BaseFragment;
import com.example.zhaoting.myapplication.bean.ArticleContentBean;
import com.example.zhaoting.myapplication.presenter.ArticleContentPresenter;
import com.example.zhaoting.myapplication.view.main.MainActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by zhaoting on 16/5/13.
 */
public class ArticleContentFragment extends BaseFragment implements ArticleContentView {

    private CoordinatorLayout mCoordinatorLayout;
    private AppBarLayout mAppBarLayout;
    private WebView mWebView;
    private ImageView mTopImage;
    private TextView mTopTitle;
    private TextView mTopText;
    private LinearLayout mFooterLinear;

    private int id;
    private ArticleContentPresenter mArticleContentPresenter = new ArticleContentPresenter(this);
    String data = null;
    String fileName = "article_content.css";
    String body = null;
    private int oldChangeMenu;

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_article_content;
    }

    @Override
    protected void initViews() {
        mCoordinatorLayout = (CoordinatorLayout) mRootView.findViewById(R.id.id_article_content_coordinator_layout);
        mAppBarLayout = (AppBarLayout) mRootView.findViewById(R.id.id_article_content_appbar_layout);
        mWebView = (WebView) mRootView.findViewById(R.id.id_article_content_webview);
        mTopImage = (ImageView) mRootView.findViewById(R.id.id_article_content_top_img);
//        mTopTitle = (TextView) mRootView.findViewById(R.id.id_article_content_top_title);
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
    protected void initDatas() {
        id = getArguments().getInt("id");
        mArticleContentPresenter.getArticleContent(id);
        oldChangeMenu = ((MainActivity) getActivity()).isChangeMenu;
        ((MainActivity) getActivity()).isChangeMenu = 2;
        getActivity().invalidateOptionsMenu();
    }


    public void onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();//表示返回webView的上一页面
        } else if ((keyCode == KeyEvent.KEYCODE_BACK) && !mWebView.canGoBack()) {
            ((MainActivity) getActivity()).isChangeMenu = oldChangeMenu;
            getActivity().invalidateOptionsMenu();
        }
    }

    @Override
    public void onSuccess(ArticleContentBean bean) {
        Picasso.with(getActivity()).load(bean.getImage()).into(mTopImage);
//        mTopTitle.setText(bean.getTitle());
        mTopText.setText(bean.getImage_source());
        mArticleContentPresenter.getArticleCss(bean.getCss().get(0));
        body = bean.getBody();

    }


    @Override
    public void onError() {
    }

    @Override
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

    @Override
    public void onCssError() {
    }


}
