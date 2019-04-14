package com.example.brett_zhu.himalaya.utils;

import android.util.Log;

/**
 * @author brett-zhu
 * created at 2019/4/14 16:33
 */
public class LogUtil {
    public static String sTAG = "LogUtil";

    //控制是否要输出Log
    public static boolean sIsRelease = false;

    /**
     * 如果是要发布了，可以在application里面把这个release一下，这里就没有log输出了
     */

    public static void init(String baseTag, boolean isRelease) {
        sTAG = baseTag;
        sIsRelease = isRelease;
    }

    public static void d(String TAG, String content) {
        if (!sIsRelease) {
            Log.d("[" + sTAG + "]" + TAG, content);
        }
    }

    public static void v(String TAG, String content) {
        if (!sIsRelease) {
            Log.d("[" + sTAG + "]" + TAG, content);
        }
    }

    public static void i(String TAG, String content) {
        if (!sIsRelease) {
            Log.d("[" + sTAG + "]" + TAG, content);
        }
    }

    public static void e(String TAG, String content) {
        if (!sIsRelease) {
            Log.d("[" + sTAG + "]" + TAG, content);
        }
    }

}
