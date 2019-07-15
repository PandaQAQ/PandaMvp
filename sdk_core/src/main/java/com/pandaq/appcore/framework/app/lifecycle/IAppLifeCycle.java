package com.pandaq.appcore.framework.app.lifecycle;

import android.app.Application;
import android.content.Context;

import android.support.annotation.NonNull;

/**
 * Created by huxinyu on 2018/12/25.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : Application lifecycle methods
 */
public interface IAppLifeCycle {

    void attachBaseContext(@NonNull Context base);

    void onCreate(@NonNull Application application);

    void onTerminate(@NonNull Application application);
}
