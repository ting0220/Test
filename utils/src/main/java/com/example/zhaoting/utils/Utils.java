package com.example.zhaoting.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by zhaoting on 16/4/20.
 */
public class Utils {
    public static String TAG;
    public static boolean DEBUG = false;
    private static Context mContext;


    private int mScreenWidth = 0;
    private int mScreenHeight = 0;
    private String splashPath;


    /**
     * 使用内部类的方式实现单例模式
     */
    private static class SingletonHolder {
        private static Utils instance = new Utils();
    }

    private Utils() {
    }

    public static Utils getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 初始化
     */
    public void init(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 设置是否显示Log
     *
     * @param isDebug
     * @param TAG
     */
    public void setDebug(boolean isDebug, String TAG) {
        Utils.TAG = TAG;
        Utils.DEBUG = isDebug;
    }


    public void Log(String text) {
        if (DEBUG) {
            Log.i(TAG, text);
        }
    }

    /**
     * Toast显示
     *
     * @param text
     */
    public void ToastShort(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

    public void ToastLong(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();
    }

    /**
     * 关闭键盘
     */
    public void closeInputMethod(Activity act) {
        View view = act.getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) mContext
                    .getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 获取app版本号
     */
    public int getAppVersionCode() {

        try {
            PackageManager mPackageManager = mContext.getPackageManager();
            PackageInfo mPackageInfo = mPackageManager.getPackageInfo(mContext.getPackageName(), 0);
            return mPackageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }

    }

    /**
     * 获取app版本名
     */
    public String getAppVersionName() {
        try {
            PackageManager mPackageManager = mContext.getPackageManager();
            PackageInfo mPackageInfo = mPackageManager.getPackageInfo(mContext.getPackageName(), 0);
            return mPackageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * url转换成bitmap
     */
    public Bitmap urlToBitmap(String s) {
        try {
            URL url = new URL(s);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream baos = new BufferedInputStream(is);
            return BitmapFactory.decodeStream(baos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * dp转换成px
     *
     * @param dp
     * @return
     */
    public int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * px转换为dp
     *
     * @param px
     * @return
     */
    public int px2dp(float px) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }



    public boolean getTheme() {
        int currentNightMode = mContext.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO: {
                return true;
            }
            case Configuration.UI_MODE_NIGHT_YES: {
                return false;
            }
            case Configuration.UI_MODE_NIGHT_UNDEFINED: {
                return true;
            }
            default:
                return true;
        }
    }

    /**
     * 截屏
     *
     * @param activity
     * @return
     */
    public Bitmap getScreenShot(AppCompatActivity activity) {
        View targetView = activity.getWindow().getDecorView().getRootView();
        targetView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        targetView.setDrawingCacheEnabled(true);
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        int height = dm.heightPixels;
        int width = dm.widthPixels;
        Bitmap bmp = Bitmap.createBitmap(targetView.getDrawingCache(), 0, statusBarHeight, width, height - statusBarHeight);
        return bmp;
    }

    /**
     * 截屏后放置到指定视图
     *
     * @param activity
     * @param resId
     * @param imgId
     * @return
     */
    public View setMode(AppCompatActivity activity, int resId, int imgId) {
        Bitmap bmp = getScreenShot(activity);
        LayoutInflater inflater = LayoutInflater.from(activity);
        final View screenShot = inflater.inflate(resId, null);
        ImageView img = (ImageView) screenShot.findViewById(imgId);
        img.setImageBitmap(bmp);
        return screenShot;
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public int getScreenWidth() {
        if (mScreenWidth == 0) {
            DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
            mScreenWidth = dm.widthPixels;
        }
        return mScreenWidth;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public int getScreenHeight() {
        if (mScreenHeight == 0) {
            DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
            mScreenHeight = dm.heightPixels;
        }
        return mScreenHeight;
    }


}
