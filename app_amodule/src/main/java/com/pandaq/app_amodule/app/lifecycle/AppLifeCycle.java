package com.pandaq.app_amodule.app.lifecycle;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;

import com.pandaq.appcore.framework.app.lifecycle.IAppLifeCycle;
import com.pandaq.appcore.utils.log.PLogger;
import com.pandaq.commonui.msgwindow.SnackerConfig;

import android.support.annotation.NonNull;

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
        PLogger.d("AppInit","----AModule");
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
