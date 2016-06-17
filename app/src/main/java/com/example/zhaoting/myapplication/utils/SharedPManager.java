package com.example.zhaoting.myapplication.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.WindowManager;

import com.example.zhaoting.myapplication.bean.LoginMessage;
import com.example.zhaoting.utils.Utils;

/**
 * Created by zhaoting on 16/4/21.
 */
public class SharedPManager {
    private Context mContext;

    /**
     * 使用内部类的方式实现单例模式
     */
    private static class SingletonHolder {
        private static SharedPManager instance = new SharedPManager();
    }

    private SharedPManager() {
    }

    public static SharedPManager getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 初始化
     */
    public void init(Context context) {
        mContext = context.getApplicationContext();
    }


    public SharedPreferences getSharedPreferences() {
        return mContext.getSharedPreferences("zt", Context.MODE_PRIVATE);
    }

    /**
     * UUID
     */
    public void setDeviceId(String value) {
        getSharedPreferences().edit().putString("deviceId", value).commit();
    }

    public String getDeviceId() {
        String deviceId = getSharedPreferences().getString("deviceId", null);
        if (deviceId == null) {
            deviceId = java.util.UUID.randomUUID().toString();
            SharedPManager.getInstance().setDeviceId(deviceId);
        }
        return deviceId;
    }

    /**
     * 登录后接口返回的数据，用于后边的请求
     * access_token user_unique_key expiration
     */
    public void setLoginMessage(LoginMessage message) {
        getSharedPreferences().edit().putString("access_token", message.getAccessToken()).commit();
        getSharedPreferences().edit().putString("user_unique_key", message.getUserUniqueKey()).commit();
        getSharedPreferences().edit().putLong("expiration", message.getExpiration()).commit();
    }

    /**
     * 登出后删除LoginMessage
     */
    public void deleteLoginMessage() {
        getSharedPreferences().edit().remove("access_token").commit();
        getSharedPreferences().edit().remove("user_unique_key").commit();
        getSharedPreferences().edit().remove("expiration").commit();
    }

    public String getAccessToken() {
        return getSharedPreferences().getString("access_token", "");
    }

    public String getUserUniqueKey() {
        return getSharedPreferences().getString("user_unique_key", "");
    }

    public long getExpiration() {
        return getSharedPreferences().getLong("expiration", 0);
    }

    /**
     * theme
     * 0表示白天模式
     */
    public void setTheme(int theme) {
        getSharedPreferences().edit().putInt("theme", theme).commit();
    }

    public int getTheme() {
        return getSharedPreferences().getInt("theme", 0);
    }


    /**
     * 判断2g或者3g设置是否选中
     *
     * @param checked
     */
    public void set2gOr3gChecked(boolean checked) {
        getSharedPreferences().edit().putBoolean("noPicture", checked).commit();
    }

    public boolean get2gOr3gChecked() {
        return getSharedPreferences().getBoolean("noPicture", false);
    }


    public void setMode(AppCompatActivity activity, int resId, int imgId) {
        final AppCompatActivity localActivity = activity;
        final View view = Utils.getInstance().setMode(activity, resId, imgId);

        final WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        params.format = PixelFormat.RGBA_8888;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        params.alpha = 1f;
        windowManager.addView(view, params);

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        animator.setDuration(500);


        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                int isDay = getTheme();
                if (isDay == 0) {
                    localActivity.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    setTheme(1);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            localActivity.recreate();
                        }
                    }, 100);
                } else {
                    localActivity.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    setTheme(0);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            localActivity.recreate();
                        }
                    }, 100);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                windowManager.removeView(view);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }
}
