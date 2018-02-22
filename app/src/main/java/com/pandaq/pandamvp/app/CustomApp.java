package com.pandaq.pandamvp.app;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * Created by huxinyu on 2018/1/22.
 * Email : panda.h@foxmail.com
 * Description :
 */

public class CustomApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new Handler().post(() -> {
            while (true) {
                try {
                    Looper.getMainLooper().loop();
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }
        });
    }
}
