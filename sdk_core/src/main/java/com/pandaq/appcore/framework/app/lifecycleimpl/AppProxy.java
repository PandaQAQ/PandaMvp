package com.pandaq.appcore.framework.app.lifecycleimpl;

import android.app.Application;
import android.content.Context;

import com.pandaq.appcore.BuildConfig;
import com.pandaq.appcore.framework.app.lifecycle.IAppLifeCycle;
import com.pandaq.appcore.framework.app.lifecycle.ILifecycleInjector;
import com.pandaq.appcore.framework.app.lifecycle.ManifestParser;
import com.pandaq.appcore.utils.log.PLogger;
import com.pandaq.appcore.utils.system.AppUtils;
import com.pandaq.rxpanda.RxPanda;
import com.pandaq.rxpanda.log.HttpLoggingInterceptor;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by huxinyu on 2018/12/25.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :基础 module 中的 Application 生命周期实现类除了实现 module 自身生命参数初始化还需要扫描
 * Manifest 文件，注入其他 module 的生命周期方法
 */
public class AppProxy implements IAppLifeCycle {

    private List<IAppLifeCycle> mAppLifeCycles = new ArrayList<>();
    private List<Application.ActivityLifecycleCallbacks> mActivityLifeCycles = new ArrayList<>();
    private List<FragmentManager.FragmentLifecycleCallbacks> mFragmentLifecycleCallbacks = new ArrayList<>();

    public AppProxy(Application application) {
        AppUtils.init(application);
        List<ILifecycleInjector> injectors = new ManifestParser(application).parse();
        for (ILifecycleInjector injector : injectors) {
            // add other module's callback to callback list
            injector.injectAppLifeCycle(application, mAppLifeCycles);
            // add other module's callback to callback list
            injector.injectActivityLifeCycle(application, mActivityLifeCycles);
            // add other module's callback to callback list
            injector.injectFragmentLifeCycle(application, mFragmentLifecycleCallbacks);
        }
        // register fragment lifecycle callbacks
        mActivityLifeCycles.add(new DefaultActivityLifecycle(mFragmentLifecycleCallbacks));
    }

    @Override
    public void attachBaseContext(@NonNull Context base) {
        for (IAppLifeCycle appLifeCycle : mAppLifeCycles) {
            appLifeCycle.attachBaseContext(base);
        }
    }

    @Override
    public void onCreate(@NonNull Application application) {
        for (IAppLifeCycle appLifeCycle : mAppLifeCycles) {
            appLifeCycle.onCreate(application);
        }

        for (Application.ActivityLifecycleCallbacks callbacks : mActivityLifeCycles) {
            application.registerActivityLifecycleCallbacks(callbacks);
        }

        // 全局捕获 RxJava 的异常，避免因取消订阅，未捕获异常等导致的闪退
        RxJavaPlugins.setErrorHandler(PLogger::e);
        // 配置网络
        RxPanda.globalConfig()
                .interceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .trustAllHost(true).baseUrl(BuildConfig.API);
        //配置 X5 浏览服务
        // tbs 配置
        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {

            }

            @Override
            public void onInstallFinish(int i) {

            }

            @Override
            public void onDownloadProgress(int i) {
                PLogger.d("QbSdk","下载 "+i+ " %");
            }
        });
        QbSdk.initX5Environment(AppUtils.getContext(),null);
        HashMap<String, Object> setting = new HashMap<>();
        setting.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        setting.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
        QbSdk.initTbsSettings(setting);

    }

    @Override
    public void onTerminate(@NonNull Application application) {
        if (mAppLifeCycles != null) {
            for (IAppLifeCycle appLifeCycle : mAppLifeCycles) {
                appLifeCycle.onTerminate(application);
            }
        }

        if (mActivityLifeCycles != null) {
            for (Application.ActivityLifecycleCallbacks callbacks : mActivityLifeCycles) {
                application.unregisterActivityLifecycleCallbacks(callbacks);
            }
        }
        mAppLifeCycles = null;
        mActivityLifeCycles = null;
        mFragmentLifecycleCallbacks = null;
        AppUtils.release();
    }
}
