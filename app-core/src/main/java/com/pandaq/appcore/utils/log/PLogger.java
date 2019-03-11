package com.pandaq.appcore.utils.log;

import android.annotation.SuppressLint;
import android.util.Log;

import com.pandaq.appcore.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

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


    public static void e(String message) {
        if (!BuildConfig.DEBUG)
            return;
        // Throwable instance must be created before any methods
        getMethodNames(new Throwable().getStackTrace());
        Log.e(TAG, createLog(message));
    }


    public static void i(String message) {
        if (!BuildConfig.DEBUG)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.i(TAG, createLog(message));
    }

    /**
     * debug 类型的数据使用 json 格式化后输出（非 json 按默认格式输出）
     *
     * @param message
     */
    public static void d(String message) {
        if (!BuildConfig.DEBUG)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.d(TAG, createLog(message));
    }

    public static void v(String message) {
        if (!BuildConfig.DEBUG)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.v(TAG, createLog(message));
    }

    public static void w(String message) {
        if (!BuildConfig.DEBUG)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.w(TAG, createLog(message));
    }

    public static void wtf(String message) {
        if (!BuildConfig.DEBUG)
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.wtf(TAG, createLog(message));
    }


    private static void printLine(boolean isTop) {
        if (isTop) {
            Log.d(TAG, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(TAG, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }


    /**
     * 格式化 json 后输出日志（网络日志拦截器信息打印）
     *
     * @param msg 格式化前 json 数据
     */
    public static void logJson(String msg) {
        String message;
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(2);//最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
                // 输出 json 格式数据
                printLine(true);
                message = LINE_SEPARATOR + message;
                String[] lines = message.split(LINE_SEPARATOR);
                for (String line : lines) {
                    if (!line.isEmpty()) {
                        Log.d(TAG, "║ " + line);
                    }
                }
                printLine(false);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(2);
                // 输出 json 格式数据
                printLine(true);
                message = LINE_SEPARATOR + message;
                String[] lines = message.split(LINE_SEPARATOR);
                for (String line : lines) {
                    if (!line.isEmpty()) {
                        Log.d(TAG, "║ " + line);
                    }
                }
                printLine(false);
            } else {
                message = msg;
                Log.d(TAG, message);
            }
        } catch (JSONException e) {
            message = msg;
            Log.d(TAG, message);
        }
    }
}
