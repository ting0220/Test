package com.example.zhaoting.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhaoting on 16/4/20.
 */
public class Utils {
    public static String TAG;
    public static boolean DEBUG = false;
    private static Context mContext;


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
     * 获取屏幕宽度
     *
     * @return
     */
    public int getScreenWidth() {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public int getScreenHeight() {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        return dm.heightPixels;
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
     * 判断是否是wifi连接
     */
    public boolean isWifi() {
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return networkInfo.isConnected();
        //networkInfo.isAvailable()看网络是否可用，但是可能没有连接上
    }

    /**
     * 判断是否是数据网络连接
     */
    public boolean isMobile() {
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return networkInfo.isConnected();
    }

    /**
     * 判断网络是否连接
     */
    public boolean isNetConnected() {
        boolean isConnected = isMobile() || isWifi();
        return isConnected;
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

    /**
     * 判断是否是同一天
     *
     * @param data
     * @return
     */
    public boolean isToday(String data) {
        boolean b = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String today = sdf.format(new Date());
        if (today.equals(data)) {
            b = true;
        }
        return b;
    }

    /**
     * 输出星期几
     */
    public String getDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        int month = 0;
        int day = 0;
        String week = "";
        try {
            Date newDate = sdf.parse(date);
            month = newDate.getMonth()+1;
            day = newDate.getDate();
            if (newDate.getDay() == 0) {
                week += "天";
            } else if (newDate.getDay() == 1) {
                week += "一";
            } else if (newDate.getDay() == 2) {
                week += "二";
            } else if (newDate.getDay() == 3) {
                week += "三";
            } else if (newDate.getDay() == 4) {
                week += "四";
            } else if (newDate.getDay() == 5) {
                week += "五";
            } else if (newDate.getDay() == 6) {
                week += "六";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return String.valueOf(month) + "月" + String.valueOf(day) + "日 " + "星期" + week;
    }

}
