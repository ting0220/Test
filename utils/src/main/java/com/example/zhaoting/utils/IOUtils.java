package com.example.zhaoting.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zhaoting on 16/6/21.
 */
public class IOUtils {
    private String splashPath;
    private Context mContext;

    /**
     * 使用内部类的方式实现单例模式
     */
    private static class SingletonHolder {
        private static IOUtils instance = new IOUtils();
    }

    private IOUtils() {
    }

    public static IOUtils getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 初始化
     */
    public void init(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 下载图片
     */
    public void downImage(Bitmap bmp) {
        File appDir = new File(mContext.getApplicationContext().getExternalCacheDir(), "splash");
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

    /**
     * 获取广告图片
     *
     * @return
     */
    public String getSplashPath() {
        if (splashPath == null) {
            splashPath = mContext.getApplicationContext().getExternalCacheDir() + "/splash/splash.jpg";
        }
        return splashPath;
    }

    /**
     * 下载文件到sd卡
     */
    public void write2SDFromInput(String s, String name, String first) {
        File file2 = new File(mContext.getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), first);
        if (!file2.exists()) {
            file2.mkdir();
        }
        File file = new File(file2, name + ".txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(s.getBytes());
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    /**
//     * 在文件末尾添加文件
//     */
//    public void add2SdFromString(String s, String name) {
//        String str = readSDFile(name);
//        StringBuilder string = new StringBuilder(str);
//        string.delete(string.length() - 3, string.length() - 1);
//
//        string.append(s);
//        write2SDFromInput(string.toString(), name);
//    }

    /**
     * 读取sd卡中的文件
     */
    public String readSDFile(String fileName, String first) {
        String str = null;
        File file2 = new File(mContext.getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), first);
        if (!file2.exists()) {
            file2.mkdir();
        }
        File file = new File(file2, fileName + ".txt");
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] array = new byte[1024];
            int len = -1;
            while ((len = fis.read(array)) != -1) {
                bos.write(array, 0, len);
            }
            bos.close();
            fis.close();
            str = bos.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }

    /**
     * 删除某个文件夹下的文件
     * 针对home
     */
    public void deleteFile() {
        File file = new File(String.valueOf(mContext.getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "home"));
        if (file.exists() == false) {
            return;
        } else {
            if (file.isFile()) {
                file.delete();
                return;
            }
            if (file.isDirectory()) {
                File[] childFile = file.listFiles();
                if (childFile == null || childFile.length == 0) {
                    file.delete();
                    return;
                }
                for (File f : childFile) {
                    f.delete();
                }
                file.delete();
            }
        }
    }

    /**
     * 获取首页文字
     *
     * @return
     */
    public boolean isFileIsExist(String fileName, String first) {
        File file2 = new File(mContext.getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), first);
        if (!file2.exists()) {
            file2.mkdir();
        }
        File file = new File(file2, fileName + ".txt");
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }
}
