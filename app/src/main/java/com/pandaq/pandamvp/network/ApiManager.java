package com.pandaq.pandamvp.network;

import com.pandaq.pandamvp.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description :Api 请求管理类
 */
public class ApiManager {

    private static ApiManager sManager;
    private OkHttpClient mOkHttpClient;

    public static ApiManager getInstance() {
        if (sManager == null) {
            synchronized (ApiManager.class) {
                if (sManager == null) {
                    sManager = new ApiManager();
                }
            }
        }
        return sManager;
    }

    public ApiManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor);
        } else {

        }
    }

    /**
     * 重置 ApiManager
     */
    public void resetManager() {

    }
}
