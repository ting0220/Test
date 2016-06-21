package com.example.zhaoting.myapplication.view.start;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.app.BaseActivity;
import com.example.zhaoting.myapplication.bean.StartBean;
import com.example.zhaoting.myapplication.presenter.StartPresenter;
import com.example.zhaoting.myapplication.view.main.MainActivity;
import com.example.zhaoting.utils.IOUtils;
import com.example.zhaoting.utils.Utils;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by zhaoting on 16/4/25.
 */
public class StartActivity extends BaseActivity implements StartView {
    private ImageView mImg;
    private TextView mText;

    private StartPresenter mStartPresenter = new StartPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initViews();
    }

    @Override
    public int getFragmentContainerId() {
        return 0;
    }

    private void initViews() {
        mImg = (ImageView) findViewById(R.id.id_img);
        mText = (TextView) findViewById(R.id.id_text);
        mStartPresenter.login();
    }

    public void toMainActivity() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mImg, "scaleY", 1f, 1.1f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mImg, "scaleX", 1f, 1.1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(animator1).with(animator2);
        animSet.setDuration(2000);
        animSet.start();
        animator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                jumpActivity(MainActivity.class, true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


    }


    @Override
    public void onSuccess(StartBean s) {
        if (!TextUtils.isEmpty(s.getImg())) {
            Picasso.with(this).load(s.getImg()).into(mImg);
        }
        if (!TextUtils.isEmpty(s.getText())) {
            mText.setText(s.getText());
        }
        final String url = s.getImg();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bmp = Utils.getInstance().urlToBitmap(url);
                IOUtils.getInstance().downImage(bmp);
            }
        }).start();
        toMainActivity();
    }

    @Override
    public void onError() {
        File file = new File(IOUtils.getInstance().getSplashPath());
        if (file.exists()) {
            Picasso.with(this).load(file).into(mImg);
        } else {
            mImg.setImageResource(R.mipmap.ic_launcher);
        }
        toMainActivity();
    }

    @Override
    public void onNoConnected() {
        File file = new File(IOUtils.getInstance().getSplashPath());
        if (file.exists()) {
            Picasso.with(this).load(file).into(mImg);
        } else {
               mImg.setImageResource(R.mipmap.ic_launcher);
        }
        toMainActivity();
    }


}
