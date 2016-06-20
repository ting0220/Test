package com.example.zhaoting.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
     * 判断网络是否是2G、3G
     * 如果是返回true
     * 否则返回false
     */
    public boolean getNetType() {
        boolean flag;
        if (isNetConnected()) {

            ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo info = cm.getActiveNetworkInfo();
            Log.e("net", "getNetType: " + info.getType());
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                int sub = info.getSubtype();
                switch (sub) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                        flag = true;
                        break;
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                        flag = true;
                        break;

                    case TelephonyManager.NETWORK_TYPE_CDMA:
                        flag = true;
                        break;
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                        flag = true;
                        break;
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        flag = true;
                        break;//2G
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                        flag = true;
                        break;
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                        flag = true;
                        break;
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                        flag = true;
                        break;
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                        flag = true;
                        break;
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                        flag = true;
                        break;
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                        flag = true;
                        break;
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                        flag = true;
                        break;
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        flag = true;
                        break;//3G
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        flag = false;
                        break;//4G
                    case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                        flag = false;
                        break;
                    default:
                        flag = false;
                        break;
                }
            } else {
                flag = false;
            }
        } else {
            flag = false;
        }
        return flag;
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
            month = newDate.getMonth() + 1;
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

    public String dateLongToString(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
        Date dt = new Date(date);
        String dateString = sdf.format(dt);
        return dateString;
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

    public Bitmap takeScreenShot(Activity pActivity) {
        Bitmap bitmap = null;
        View view = pActivity.getWindow().getDecorView();
        // 设置是否可以进行绘图缓存
        view.setDrawingCacheEnabled(true);
        // 如果绘图缓存无法，强制构建绘图缓存
        view.buildDrawingCache();
        // 返回这个缓存视图
        bitmap = view.getDrawingCache();

        // 获取状态栏高度
        Rect frame = new Rect();
        // 测量屏幕宽和高
        view.getWindowVisibleDisplayFrame(frame);
        int stautsHeight = frame.top;

        int width = pActivity.getWindowManager().getDefaultDisplay().getWidth();
        int height = pActivity.getWindowManager().getDefaultDisplay().getHeight();
        // 根据坐标点和需要的宽和高创建bitmap
        bitmap = Bitmap.createBitmap(bitmap, 0, stautsHeight, width, height - stautsHeight);
        return bitmap;
    }

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

    /**
     * 下载图片
     */
    public void downImage(String url) {
        Bitmap bmp ;
        File appDir = new File(mContext.getApplicationContext().getCacheDir(), "splash");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "splash.jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getSplashPath() {
        if (splashPath == null) {
            splashPath = mContext.getApplicationContext().getCacheDir() + "splash/splash.png";
        }
        return splashPath;
    }
}
