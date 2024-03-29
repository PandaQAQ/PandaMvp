package com.pandaq.app_launcher.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;

import com.pandaq.app_launcher.net.ApiService;
import com.pandaq.appcore.BuildConfig;
import com.pandaq.appcore.framework.app.lifecycle.IAppLifeCycle;
import com.pandaq.rxpanda.RxPanda;
import com.pandaq.rxpanda.converter.PandaConvertFactory;
import com.pandaq.rxpanda.log.HttpLoggingInterceptor;
import com.pandaq.uires.configs.UiConfigs;

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
        UiConfigs.Companion.snackbar()
                .setMsgColor(Color.parseColor("#ff00ff"))
                .setActionColor(Color.parseColor("#0000ff"))
                .setBackgroundColor(Color.parseColor("#ffff00"));
//        UiConfigs.Companion.toast();
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }

    // 初始化网络请求
    private void initNet(Application application) {
        RxPanda.init(application)
                .baseUrl(ApiService.BASE_URL)
                .netInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .apiSuccessCode("100L")
                .hosts("http://192.168.0.107:8080")
//                .apiDataClazz(WanApiData.class)
                .converterFactory(PandaConvertFactory.create())
                .connectTimeout(10000)
                .readTimeout(10000)
                .writeTimeout(10000)
                .debug(BuildConfig.IN_DEBUG);
    }
}
