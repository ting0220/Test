package com.example.zhaoting.myapplication.view.themeArticleContent;

import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.app.BaseFragment;
import com.example.zhaoting.myapplication.bean.ArticleContentBean;
import com.example.zhaoting.myapplication.presenter.ThemeArticleContentPresenter;
import com.example.zhaoting.myapplication.view.article.ArticleContentView;
import com.example.zhaoting.myapplication.view.main.MainActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by zhaoting on 16/5/24.
 */
public class ThemeArticleContentFragment extends BaseFragment implements ArticleContentView {
//    private ScrollView mScrollView;
    private WebView mWebView;
    private ImageView mFootImg;
    private TextView mFootTitle;
    private TextView mFootFocus;

    private int id;
    private ThemeArticleContentPresenter mThemeArticleContentPresenter = new ThemeArticleContentPresenter(this);
    private int oldChangeMenu;
    private String body;
    private String fileName = "article_content.css";
    private String data = null;

    @Override
    public void onSuccess(ArticleContentBean bean) {
        mThemeArticleContentPresenter.getArticleCss(bean.getCss().get(0));
        body = bean.getBody();
        Picasso.with(getActivity()).load(bean.getTheme().getThumbnail()).into(mFootImg);
        mFootTitle.setText(getResources().getString(R.string.article_from) + bean.getTheme().getName());

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

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_theme_article_content;
    }

    @Override
    protected void initViews() {
//        mScrollView= (ScrollView) mRootView.findViewById(R.id.id_theme_article_content_scroll);
        mWebView = (WebView) mRootView.findViewById(R.id.id_theme_article_content_webview);
        mFootFocus = (TextView) mRootView.findViewById(R.id.id_theme_article_content_focus);
        mFootImg = (ImageView) mRootView.findViewById(R.id.id_theme_article_content_img);
        mFootTitle = (TextView) mRootView.findViewById(R.id.id_theme_article_content_title);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
//        mWebView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    mScrollView.requestDisallowInterceptTouchEvent(true);
//                } else {
//                    mScrollView.requestDisallowInterceptTouchEvent(false);
//                }
//                return false;
//            }
//        });
    }

    @Override
    protected void initDatas() {
        id = getArguments().getInt("id");
        mThemeArticleContentPresenter.getArticleContent(id);
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
}
