package com.example.zhaoting.myapplication.view.article;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.bean.ArticleContentBean;
import com.example.zhaoting.myapplication.bean.ExtraInfoBean;
import com.example.zhaoting.myapplication.view.comments.CommentActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by zhaoting on 16/6/1.
 */
public class ThemeArticleCActivity extends ArticleContentBaseActivity implements View.OnClickListener {

    private WebView mWebView;
    private ImageView mFootImg;
    private TextView mFootTitle;
    private TextView mFootFocus;
    private ImageView mBack;
    private TextView mPraise;
    private TextView mComment;
    private ImageView mCollect;
    private ImageView mShare;

    private ExtraInfoBean mExtraInfoBean;
    private ArticleContentBean mArticleContentBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_content);
        initViews();
        initDatas();
    }

    @Override
    public int getFragmentContainerId() {
        return 0;
    }

    private void initViews() {
        mWebView = (WebView) findViewById(R.id.id_article_content_webview);
        mFootImg = (ImageView) findViewById(R.id.id_theme_article_content_img);
        mFootTitle = (TextView) findViewById(R.id.id_theme_article_content_title);
        mFootFocus = (TextView) findViewById(R.id.id_theme_article_content_focus);
        mWebView.getSettings().setJavaScriptEnabled(true);

        mBack = (ImageView) findViewById(R.id.id_menu_back);
        mPraise = (TextView) findViewById(R.id.id_menu_praise);
        mComment = (TextView) findViewById(R.id.id_menu_comment);
        mCollect = (ImageView) findViewById(R.id.id_menu_collect);
        mShare = (ImageView) findViewById(R.id.id_menu_share);

        mBack.setOnClickListener(this);
        mPraise.setOnClickListener(this);
        mCollect.setOnClickListener(this);
        mComment.setOnClickListener(this);
        mShare.setOnClickListener(this);
    }

    @Override
    public void setToolBarData(ExtraInfoBean bean) {
        this.mExtraInfoBean=bean;
        mPraise.setText(bean.getPopularity()+"");
        mComment.setText(bean.getComments()+"");
    }

    @Override
    public void setContentData(ArticleContentBean bean, String data) {
        this.mArticleContentBean=bean;
        Picasso.with(this).load(bean.getTheme().getThumbnail()).into(mFootImg);
        mFootTitle.setText(getResources().getString(R.string.article_from) + bean.getTheme().getName());
        mWebView.loadDataWithBaseURL("", data, "text/html", "UTF-8", null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_menu_back: {
                ThemeArticleCActivity.this.finish();
            }
            break;
            case R.id.id_menu_praise: {
            }
            break;
            case R.id.id_menu_comment: {
                Bundle bundle=new Bundle();
                bundle.putInt("longComments",mExtraInfoBean.getLong_comments());
                bundle.putInt("shortComments",mExtraInfoBean.getShort_comments());
                bundle.putInt("id",mArticleContentBean.getId());
                Intent intent=new Intent(ThemeArticleCActivity.this, CommentActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            break;
            case R.id.id_menu_collect: {
            }
            break;
            case R.id.id_menu_share: {
            }
            break;
        }
    }


}
