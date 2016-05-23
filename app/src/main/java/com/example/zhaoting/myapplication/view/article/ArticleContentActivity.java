package com.example.zhaoting.myapplication.view.article;

import com.example.zhaoting.myapplication.app.BaseActivity;

/**
 * Created by zhaoting on 16/5/23.
 */
public class ArticleContentActivity extends BaseActivity {
//    private CoordinatorLayout mCoordinatorLayout;
//    private AppBarLayout mAppBarLayout;
//    private CollapsingToolbarLayout mCollapsingToolbarLayout;
//    private Toolbar mToolbar;
//    private WebView mWebView;
//    private ImageView mTopImg;
//    private TextView mTopTitle;
//    private TextView mTopText;
//
//    private int id;
//    private ArticleContentPresenter mArticleContentPresenter = new ArticleContentPresenter(this);
//    String body = null;
//    String data = null;
//    String fileName = "article_content.css";


    @Override
    public int getFragmentContainerId() {
        return 0;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_article_content);
//        initViews();
//        initDatas();
//    }
//
//    private void initDatas() {
//        Bundle bundle = getIntent().getExtras();
//        id = bundle.getInt("id");
//        mArticleContentPresenter.getArticleContent(id);
//
//
//    }
//
//    private void initViews() {
//        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.id_article_content_coordinator_layout);
//        mAppBarLayout = (AppBarLayout) findViewById(R.id.id_article_content_appbar_layout);
//        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
//        mToolbar = (Toolbar) findViewById(R.id.id_article_content_toolbar);
//        mWebView = (WebView) findViewById(R.id.id_article_content_webview);
//        mTopImg = (ImageView) findViewById(R.id.id_article_content_top_img);
//        mTopTitle = (TextView) findViewById(R.id.id_article_content_top_title);
//        mTopText = (TextView) findViewById(R.id.id_article_content_top_text);
//
//        mToolbar.inflateMenu(R.menu.toolbar_menu_article_content);
//        mToolbar.setOnMenuItemClickListener(this);
//
//        mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//    }
//
//
//    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//        return false;
//    }
//
//    @Override
//    public void onSuccess(ArticleContentBean bean) {
//        Picasso.with(this).load(bean.getImage()).into(mTopImg);
//        mTopTitle.setText(bean.getTitle());
//        mTopText.setText(bean.getImage_source());
//        mArticleContentPresenter.getArticleCss(bean.getCss().get(0));
//        body = bean.getBody();
//    }
//
//    @Override
//    public void onError() {
//
//    }
//
//    @Override
//    public void onCssSuccess(String s) {
//        File file = new File(this.getFilesDir(), fileName);
//        try {
//            FileOutputStream outputStream = new FileOutputStream(file);
//            outputStream.write(s.getBytes());
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String css = "<link rel=\"stylesheet\" href=\"file:///" + this.getFilesDir() + "/" + fileName + "\"type=\"text/css\" />";
//        data = "<html><head>" + css + "</head><body>" + body + "</body></html>";
//        data = data.replace("<div class=\"img-place-holder\">", "");
//        mWebView.loadDataWithBaseURL("", data, "text/html", "UTF-8", null);
//    }
//
//    @Override
//    public void onCssError() {
//
//    }
}
