package com.pandaq.pandamvp.net;

import com.pandaq.pandamvp.BuildConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description :Api 请求管理类
 */
public class ApiManager {

    private static ApiManager sManager;
    private OkHttpClient mOkHttpClient;
    private ApiService sApiService;

    public static synchronized ApiManager getInstance() {
        if (sManager == null) {
            sManager = new ApiManager();
        }
        return sManager;
    }

    private ApiManager() {
    }

    /**
     * API 请求接口提供
     *
     * @return API 接口对象
     */
    public ApiService getApiService() {
        if (mOkHttpClient == null) {
            mOkHttpClient = getOkHttpClient();
        }
        if (sApiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(mOkHttpClient)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            sApiService = retrofit.create(ApiService.class);
        }
        return sApiService;
    }

    /**
     * 获取参数配置后的请求客户端
     *
     * @return OkhttpClient
     */
    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        CommonIntercepter intercepter = new CommonIntercepter.Builder()
                .addHeaders(headers())
                .build();
        builder.addNetworkInterceptor(intercepter);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor);
        }
        builder = builder.connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(20000, TimeUnit.MILLISECONDS)
                .writeTimeout(20000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true);
        return builder.build();
    }

    /**
     * 配置请求头信息
     *
     * @return 请求头 key map
     */
    private Map<String, String> headers() {
        Map<String, String> headers = new HashMap<>();
        headers.put("token", "9527");
        headers.put("header1", "value");
        return headers;
    }

    private Map<String, String> pubParams() {
        Map<String, String> headers = new HashMap<>();
        headers.put("param1", "value");
        headers.put("param2", "value");
        return headers;
    }

    /**
     * 重置 ApiManager
     */
    public void reset() {
        mOkHttpClient = null;
        sApiService = null;
    }
}
