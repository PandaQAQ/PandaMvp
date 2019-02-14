package com.pandaq.appcore.http;

import com.pandaq.appcore.http.config.HttpGlobalConfig;
import com.pandaq.appcore.http.requests.okhttp.GetRequest;
import com.pandaq.appcore.http.requests.okhttp.PostRequest;
import com.pandaq.appcore.http.requests.retrofit.RetrofitRequest;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by huxinyu on 2019/1/9.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
public class PandaHttp {

    private static OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
    private static OkHttpClient okHttpClient;

    public static HttpGlobalConfig globalConfig() {
        return HttpGlobalConfig.getInstance();
    }

    /**
     * normal get request
     */
    public static GetRequest get() {
        return new GetRequest();
    }

    /**
     * normal post request
     */
    public static PostRequest post() {
        return new PostRequest();
    }

    /**
     * retrofit request
     */
    public static RetrofitRequest retrofit() {
        return new RetrofitRequest();
    }

    public static OkHttpClient getOkhttpClient() {
        if (okHttpClient == null) {
            okHttpClient = getOkHttpBuilder().build();
        }
        return okHttpClient;
    }

    public static OkHttpClient.Builder getOkHttpBuilder() {
        return okHttpBuilder;
    }

    public static Retrofit.Builder getRetrofitBuilder() {
        return retrofitBuilder;
    }
}
