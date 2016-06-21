package com.example.zhaoting.utils;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhaoting on 16/6/21.
 */
public class DateUtils {
    private Context mContext;

    /**
     * 内部类的方式实现单例模式
     */
    private static class SingletonHolder {
        private static DateUtils instance = new DateUtils();
    }

    private DateUtils() {
    }

    public static DateUtils getInstance() {
        return SingletonHolder.instance;
    }

    public void init(Context context) {
        mContext = context.getApplicationContext();
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

    /**
     * 日期格式转换 date转换成String类型
     *
     * @param date
     * @return
     */
    public String dateLongToString(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
        Date dt = new Date(date);
        String dateString = sdf.format(dt);
        return dateString;
    }

}
