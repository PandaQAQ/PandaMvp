package com.pandaq.appcore.utils.logutils;

import android.util.Log;

import com.pandaq.appcore.BuildConfig;

/**
 * Created by huxinyu on 2018/9/5.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :日志打印封装
 */
public class PLogger {
    private static String className;//类名
    private static int lineNumber;//行数

    private PLogger() {
        /* Protect from instantiations */
    }

    private static String createLog(String log) {
        return "(" + className + ":" + lineNumber + ")" + "  Log : " + log;
    }

    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        lineNumber = sElements[1].getLineNumber();
    }


    public static void e(String message) {
        if (!BuildConfig.DEBUG)
            return;
        // Throwable instance must be created before any methods
        getMethodNames(new Throwable().getStackTrace());
    }


    public static void i(String message) {
        if (!BuildConfig.DEBUG)
            return;
        getMethodNames(new Throwable().getStackTrace());
    }

    public static void d(String message) {
        if (!BuildConfig.DEBUG)
            return;
        getMethodNames(new Throwable().getStackTrace());
    }

    public static void v(String message) {
        if (!BuildConfig.DEBUG)
            return;
        getMethodNames(new Throwable().getStackTrace());
    }

    public static void w(String message) {
        if (!BuildConfig.DEBUG)
            return;
        getMethodNames(new Throwable().getStackTrace());
    }

    public static void wtf(String message) {
        if (!BuildConfig.DEBUG)
            return;
        getMethodNames(new Throwable().getStackTrace());
    }
}
