package com.example.zhaoting.myapplication.view.setting;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.SettingView;
import com.example.zhaoting.myapplication.app.BaseActivity;

/**
 * Created by zhaoting on 16/6/8.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private SettingView mDownAuto;
    private SettingView mNoPicture;
    private SettingView mBigSize;
    private SettingView mPushInfo;
    private SettingView mCommentShare;
    private SettingView mClearCache;
    private SettingView mUpdate;
    private SettingView mFeedback;
    private Toolbar mToolBar;

    @Override
    public int getFragmentContainerId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initViews();
        initDatas();
        initListeners();
    }

    private void initDatas() {

    }

    private void initListeners() {
        mDownAuto.setOnClickListener(this);
        mNoPicture.setOnClickListener(this);
        mBigSize.setOnClickListener(this);
        mPushInfo.setOnClickListener(this);
        mCommentShare.setOnClickListener(this);
        mClearCache.setOnClickListener(this);
        mUpdate.setOnClickListener(this);
        mFeedback.setOnClickListener(this);

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.this.finish();
            }
        });
    }

    private void initViews() {
        mDownAuto = (SettingView) findViewById(R.id.id_setting_down_auto);
        mNoPicture = (SettingView) findViewById(R.id.id_setting_no_picture);
        mBigSize = (SettingView) findViewById(R.id.id_setting_big_size);
        mPushInfo = (SettingView) findViewById(R.id.id_setting_push_info);
        mCommentShare = (SettingView) findViewById(R.id.id_setting_comment_share);
        mClearCache = (SettingView) findViewById(R.id.id_setting_clear_cache);
        mUpdate = (SettingView) findViewById(R.id.id_setting_update);
        mFeedback = (SettingView) findViewById(R.id.id_setting_feedback);
        mToolBar = (Toolbar) findViewById(R.id.id_setting_toolbar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_setting_down_auto: {
            }
            break;
            case R.id.id_setting_no_picture: {
            }
            break;
            case R.id.id_setting_big_size: {
            }
            break;
            case R.id.id_setting_push_info: {
            }
            break;
            case R.id.id_setting_comment_share: {
            }
            break;
            case R.id.id_setting_clear_cache: {
            }
            break;
            case R.id.id_setting_update: {
            }
            break;
            case R.id.id_setting_feedback: {
            }
            break;
        }

    }
}
