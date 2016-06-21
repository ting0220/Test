package com.example.zhaoting.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by zhaoting on 16/6/21.
 */
public class NetUtils {
    private Context mContext;

    /**
     * 使用内部类方式实现单例模式
     */
    private static class SingletonHolder {
        private static NetUtils instance = new NetUtils();
    }

    private NetUtils() {
    }

    public static NetUtils getInstance() {
        return SingletonHolder.instance;
    }


    public void init(Context context) {
        mContext = context.getApplicationContext();
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

}
