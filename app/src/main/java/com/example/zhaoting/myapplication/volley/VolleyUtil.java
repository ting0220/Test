package com.example.zhaoting.myapplication.volley;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhaoting on 16/4/21.
 */
public class VolleyUtil {
    private static final String TAG = "TAG";
    private static Context mContext;
    private static RequestQueue reqQueue;


    /**
     * 使用内部类方式实现单例模式
     */
    private static class SingletonHolder {
        private static VolleyUtil instance = new VolleyUtil();
    }

    private VolleyUtil() {
    }

    public static VolleyUtil getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 初始化
     */
    public void init(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 用于返回全局RequestQueue对象，如果为空则创建它
     */
    public static RequestQueue getRequestQueue() {
        if (reqQueue == null) {
            reqQueue = Volley.newRequestQueue(mContext);
        }
        return reqQueue;
    }

    /**
     * 将Request对象添加进RequestQueue，由于Request有StringRequest,JsonObjectRequest...等多种类型，所以需要用到＊泛型
     */
    public  <T> void addToRequestQueue(Request<T> req, String tag) {
        //如果tag为空的话，就用默认TAG
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    /**
     * 通过各Request对象的Tag属性取消请求
     */
    public  void cancelPendingrequesy(Object tag) {
        if (reqQueue != null) {
            reqQueue.cancelAll(tag);
        }
    }

    public  void get(String url, final Map<String, String> map, Response.Listener<String> listener, Response.ErrorListener errorListener, String tag) {
        StringBuilder builder = new StringBuilder(url);
//        builder.append("?");
        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        for (int i = 0; i < map.size(); i++) {
            String key = iterator.next();
            String value = map.get(key);
            if (!TextUtils.isEmpty(value)) {
                builder.append(key);
                builder.append("=");
                builder.append(value);
            }
            if (!TextUtils.isEmpty(value) && i < map.size() - 1) {
                builder.append("&");
            }
        }
        HttpRequest request = new HttpRequest(Request.Method.GET, builder.toString(), listener, errorListener);
        addToRequestQueue(request, tag);
    }




    //根据URL获取所需请求的的cache数据
    public  String getCathe(String url, final Map<String, String> map) {
        String data = null;
        StringBuilder builder = new StringBuilder(url);
        builder.append("?");
        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        for (int i = 0; i < map.size(); i++) {
            String key = iterator.next();
            String value = map.get(key);
            if (!TextUtils.isEmpty(value)) {
                builder.append(key);
                builder.append("=");
                builder.append(value);
            }
            if (!TextUtils.isEmpty(value) && i < map.size() - 1) {
                builder.append("&");
            }
        }
        Cache cache = getRequestQueue().getCache();
        Cache.Entry entry = cache.get(builder.toString());
        if (entry != null) {
            try {
                data = new String(entry.data, "Utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

}
