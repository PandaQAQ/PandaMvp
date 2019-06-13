package com.pandaq.appcore.network.log;

import android.util.Log;

import com.pandaq.appcore.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by huxinyu on 2019/6/13.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :日志暂存对象
 */
public class LogEntity {

    public LogEntity() {
        addLog(" ");
        addLog("╔════════════════════════  HTTP REQUEST START  ══════════════════════════");
        addLog("");
    }

    private final String tag = "RxPanda";
    private List<String> logs = new ArrayList<>();
    private final String LINE_SEPARATOR = Objects.requireNonNull(System.getProperty("line.separator"));

    /**
     * 临时保存日志
     *
     * @param log 日志
     */
    public void addLog(String log) {
        if (log == null) return;
        if (log.equals(" ") || log.startsWith("{")
                || log.startsWith("╔") || log.startsWith("╚")) {
            logs.add(log);
        } else {
            logs.add("║" + log);
        }
    }

    /**
     * 输出日志到控制台
     */
    public void printLog() {
        addLog("");
        addLog("╚════════════════════════  HTTP REQUEST END  ═══════════════════════════");
        addLog(" ");
        for (String log : logs) {
            logJson(log);
        }
        logs.clear();
        logs = null;
    }

    /**
     * 格式化 json 后输出日志（网络日志拦截器信息打印）
     *
     * @param msg 格式化前 json 数据
     */
    private void logJson(String msg) {
        String message;
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(2);//最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(2);
            } else {
                message = msg;
                if (BuildConfig.DEBUG) {
                    Log.d(tag, message);
                }
                return;
            }
        } catch (JSONException e) {
            message = msg;
            if (BuildConfig.DEBUG) {
                Log.d(tag, message);
            }
            return;
        }
        // 输出 json 格式数据
        printLine(true);
        message = LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            if (!line.isEmpty() && BuildConfig.DEBUG) {
                Log.d(tag, "║ " + line);
            }
        }
        printLine(false);
    }

    /**
     * 打印日志分割线
     *
     * @param isTop 是否为顶部分割线
     */
    private void printLine(boolean isTop) {
        if (isTop) {
            if (BuildConfig.DEBUG) {
                Log.d(tag, "║");
                Log.d(tag, "║——————————————————JSON START——————————————————");
            }
        } else {
            if (BuildConfig.DEBUG) {
                Log.d(tag, "║——————————————————JSON END———————————————————");
                Log.d(tag, "║");
            }
        }
    }
}
