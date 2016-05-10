package com.example.zhaoting.myapplication.view.start;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.presenter.StartPresenter;
import com.example.zhaoting.myapplication.view.main.MainActivity;
import com.example.zhaoting.utils.Utils;
import com.squareup.picasso.Picasso;

/**
 * Created by zhaoting on 16/4/25.
 */
public class StartActivity extends AppCompatActivity implements StartView {
    private ImageView mImg;
    private TextView mText;

    private StartPresenter mStartPresenter = new StartPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initViews();
    }

    private void initViews() {
        mImg = (ImageView) findViewById(R.id.id_img);
        mText = (TextView) findViewById(R.id.id_text);
        mStartPresenter.login();
    }

    @Override
    public void setImg(String url) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(this).load(url).into(mImg);
        }
    }

    @Override
    public void setText(String text) {
        if (!TextUtils.isEmpty(text)) {
            mText.setText(text);
        }

    }

    @Override
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
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                StartActivity.this.finish();
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
    public void showFailedError() {
        Utils.getInstance().ToastShort("failed");
    }


}
