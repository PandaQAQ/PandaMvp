package com.pandaq.pandamvp.app.lifecycle;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;

import com.pandaq.appcore.cache.CacheTool;
import com.pandaq.appcore.framework.app.lifecycle.IAppLifeCycle;
import com.pandaq.appcore.network.Panda;
import com.pandaq.appcore.network.config.HttpGlobalConfig;
import com.pandaq.appcore.network.interceptor.HttpLoggingInterceptor;
import com.pandaq.appcore.utils.log.PLogger;
import com.pandaq.commonui.msgwindow.SnackerConfig;
import com.pandaq.pandamvp.BuildConfig;
import com.pandaq.pandamvp.R;
import com.pandaq.pandamvp.app.Constant;
import com.pandaq.pandamvp.entites.UserInfo;
import com.pandaq.pandamvp.net.ApiService;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import androidx.annotation.NonNull;

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
        PLogger.d("AppInit","----APPModule");
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }

    // 初始化网络请求
    private void initNet(Application application) {
        UserInfo userInfo = CacheTool.with(application)
                .open(Constant.Cache.CACHE_FILE_NAME)
                .getSerializable(Constant.Cache.CACHE_USEINFO_KEY);
        HttpGlobalConfig config = Panda.globalConfig()
                .baseUrl(ApiService.BASE_URL)
                .netInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .apiSuccessCode(100L)
                .connectTimeout(10000)
                .readTimeout(10000)
                .writeTimeout(10000)
                .debug(BuildConfig.DEBUG);

        if (userInfo != null) {
            config.addGlobalHeader(ApiService.TOKEN_HEADER, userInfo.getToken());
        }
    }
}
