package com.pandaq.pandamvp.app;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;

import com.pandaq.appcore.cache.CacheTool;
import com.pandaq.appcore.http.ApiManager;
import com.pandaq.commonui.msgwindow.SnackerConfig;
import com.pandaq.pandamvp.BuildConfig;
import com.pandaq.pandamvp.entites.UserInfo;
import com.pandaq.pandamvp.net.ApiService;

/**
 * Created by huxinyu on 2018/3/30.
 * Email : panda.h@foxmail.com
 * Description :
 */
public class App extends Application {

    public static ApiManager<ApiService> sApiManager;
    private static App sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        initNet();
        SnackerConfig.getDefault()
                .setActionColor(Color.WHITE)
                .setActionColor(Color.RED)
                .setBackgroundColor(Color.GREEN);
    }

    // 初始化网络请求
    public static void initNet() {
        UserInfo userInfo = CacheTool.with(sApp)
                .open(Constant.Cache.CACHE_FILE_NAME)
                .getSerializable(Constant.Cache.CACHE_USEINFO_KEY);
        ApiManager.Builder builder = new ApiManager.Builder()
                .baseUrl(ApiService.BASE_URL)
                .debug(BuildConfig.DEBUG);
        if (userInfo != null) {
            builder.setHeader(ApiService.TOKEN_HEADER, userInfo.getToken());
        }
        sApiManager = builder.build();
    }

    public static App getGlobalApp() {
        return sApp;
    }

    /**
     * 获取应用的版本号
     *
     * @return 应用版本号
     */
    public int getAppVersion() {
        try {
            PackageInfo info = sApp.getPackageManager().getPackageInfo(sApp.getApplicationContext().getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
