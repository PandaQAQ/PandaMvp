package com.pandaq.appcore.utils.log;

import android.annotation.SuppressLint;
import android.util.Log;

import com.pandaq.appcore.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import androidx.annotation.NonNull;

/**
 * Created by huxinyu on 2018/9/5.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :日志打印封装
 */

@SuppressLint("LogNotTimber")
public class PLogger {
    private static String className;//类名
    private static int lineNumber;//行数
    private static String TAG = "Log";

    private PLogger() {
        /* Protect from instantiations */
    }

    private static String createLog(String log) {
        return "(" + className + ":" + lineNumber + ")" + "  Message : " + log;
    }

    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        lineNumber = sElements[1].getLineNumber();
    }

    public static void e(String message, Throwable e) {
        e(TAG, message, e);
    }

    public static void e(String tag, String message, Throwable e) {
        if (!BuildConfig.DEBUG)
            return;
        // Throwable instance must be created before any methods
        getMethodNames(new Throwable().getStackTrace());
        Log.e(tag, createLog(message), e);
    }

    public static void e(String message) {
        e(TAG, message);
    }

    public static void e(String tag, String message) {
        if (!BuildConfig.DEBUG)
            return;
        // Throwable instance must be created before any methods
        getMethodNames(new Throwable().getStackTrace());
        Log.e(tag, createLog(message));
    }


    public static void i(String message) {
        i(TAG, message);
    }

    public static void i(String tag, String message) {
        if (!BuildConfig.DEBUG)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.i(tag, createLog(message));
    }

    public static void d(String message) {
        d(TAG, message);
    }

    public static void d(String tag, String message) {
        if (!BuildConfig.DEBUG)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.d(tag, createLog(message));
    }

    public static void v(String message) {
        v(TAG, message);
    }

    public static void v(String tag, String message) {
        if (!BuildConfig.DEBUG)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.v(tag, createLog(message));
    }

    public static void w(String message) {
        w(TAG, message);
    }

    public static void w(String tag, String message) {
        if (!BuildConfig.DEBUG)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.w(tag, createLog(message));
    }

    public static void wtf(String message) {
        wtf(TAG, message);
    }

    public static void wtf(String tag, String message) {
        if (!BuildConfig.DEBUG)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.wtf(tag, createLog(message));
    }

}
