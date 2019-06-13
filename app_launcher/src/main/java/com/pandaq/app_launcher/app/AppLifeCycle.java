package com.pandaq.app_launcher.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;

import com.pandaq.app_launcher.BuildConfig;
import com.pandaq.app_launcher.net.ApiService;
import com.pandaq.appcore.cache.CacheTool;
import com.pandaq.appcore.framework.app.lifecycle.IAppLifeCycle;
import com.pandaq.appcore.network.RxPanda;
import com.pandaq.appcore.network.config.HttpGlobalConfig;
import com.pandaq.appcore.network.interceptor.HttpLoggingInterceptor;
import com.pandaq.appcore.utils.log.PLogger;
import com.pandaq.appcore.utils.system.AppUtils;
import com.pandaq.commonui.msgwindow.SnackerConfig;

import androidx.annotation.NonNull;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by huxinyu on 2018/12/25.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :this module' appLifeCycle impl.the methods will called in global application
 */
public class AppLifeCycle implements IAppLifeCycle {

    @Override
    public void attachBaseContext(@NonNull Context base) {

    }

    @Override
    public void onCreate(@NonNull Application application) {
        initNet(application);
        SnackerConfig.getDefault()
                .setActionColor(Color.RED)
                .setBackgroundColor(Color.GREEN);
        PLogger.d("AppInit", "----APPModule");
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }

    // 初始化网络请求
    private void initNet(Application application) {
        RxPanda.globalConfig()
                .baseUrl(ApiService.BASE_URL)
                .netInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .apiSuccessCode(100L)
                .converterFactory(GsonConverterFactory.create())
                .connectTimeout(10000)
                .readTimeout(10000)
                .writeTimeout(10000)
                .debug(BuildConfig.DEBUG);
    }
}
