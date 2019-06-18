package com.pandaq.appcore.utils.log;

import android.annotation.SuppressLint;
import android.util.Log;

import com.pandaq.appcore.BuildConfig;

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
        if (!BuildConfig.DEBUG) return;
        className = sElements[1].getFileName();
        lineNumber = sElements[1].getLineNumber();
    }

    public static void e(String message, Throwable e) {
        getMethodNames(new Throwable().getStackTrace());
        logThrowable(TAG, message, e);
    }


    public static void e(String tag, String message, Throwable e) {
        // Throwable instance must be created before any methods
        getMethodNames(new Throwable().getStackTrace());
        logThrowable(tag, message, e);
    }

    /**
     * 输出日志
     *
     * @param tag     标签
     * @param message 信息
     * @param e       错误
     */
    private static void logThrowable(String tag, String message, Throwable e) {
        if (!BuildConfig.DEBUG) return;
        Log.e(tag, createLog(message), e);
    }

    public static void e(String message) {
        getMethodNames(new Throwable().getStackTrace());
        logErr(TAG, message);
    }

    public static void e(String tag, String message) {
        // Throwable instance must be created before any methods
        getMethodNames(new Throwable().getStackTrace());
        logErr(tag, createLog(message));
    }

    private static void logErr(String tag, String message) {
        if (!BuildConfig.DEBUG) return;
        Log.e(tag, createLog(message));
    }

    public static void i(String message) {
        getMethodNames(new Throwable().getStackTrace());
        logI(TAG, message);
    }

    public static void i(String tag, String message) {
        getMethodNames(new Throwable().getStackTrace());
        logI(tag, createLog(message));
    }

    private static void logI(String tag, String message) {
        if (!BuildConfig.DEBUG) return;
        Log.i(tag, createLog(message));
    }

    public static void d(String message) {
        getMethodNames(new Throwable().getStackTrace());
        logD(TAG, message);
    }

    public static void d(String tag, String message) {
        getMethodNames(new Throwable().getStackTrace());
        logD(tag, createLog(message));
    }

    private static void logD(String tag, String message) {
        if (!BuildConfig.DEBUG) return;
        Log.d(tag, createLog(message));
    }

    public static void v(String message) {
        getMethodNames(new Throwable().getStackTrace());
        logV(TAG, message);
    }

    public static void v(String tag, String message) {
        getMethodNames(new Throwable().getStackTrace());
        logV(tag, createLog(message));
    }

    private static void logV(String tag, String message) {
        if (!BuildConfig.DEBUG) return;
        Log.v(tag, createLog(message));
    }

    public static void w(String message) {
        getMethodNames(new Throwable().getStackTrace());
        logW(TAG, message);
    }

    public static void w(String tag, String message) {
        getMethodNames(new Throwable().getStackTrace());
        logW(tag, createLog(message));
    }

    private static void logW(String tag, String message) {
        if (!BuildConfig.DEBUG) return;
        Log.w(tag, createLog(message));
    }

    public static void wtf(String message) {
        getMethodNames(new Throwable().getStackTrace());
        logWtf(TAG, message);
    }

    public static void wtf(String tag, String message) {
        getMethodNames(new Throwable().getStackTrace());
        logWtf(tag, createLog(message));
    }

    private static void logWtf(String tag, String message) {
        if (!BuildConfig.DEBUG) return;
        Log.wtf(tag, createLog(message));
    }

}
