package com.example.zhaoting.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.myapplication.bean.LoginMessage;

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
     */
    public void setTheme(int theme){
        getSharedPreferences().edit().putInt("theme",theme).commit();
    }

    public int getTheme(){
        return getSharedPreferences().getInt("theme", R.style.AppBaseTheme_Light);
    }

}
