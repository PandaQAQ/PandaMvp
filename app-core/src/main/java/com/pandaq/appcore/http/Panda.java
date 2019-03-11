package com.pandaq.appcore.http;

import com.pandaq.appcore.http.config.HttpGlobalConfig;
import com.pandaq.appcore.http.requests.okhttp.GetRequest;
import com.pandaq.appcore.http.requests.okhttp.PostRequest;
import com.pandaq.appcore.http.requests.retrofit.RetrofitRequest;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import retrofit2.Retrofit;

/**
 * Created by huxinyu on 2019/1/9.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :http request tool class
 */
public class Panda {

    private static OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
    private static OkHttpClient okHttpClient;

    public static HttpGlobalConfig globalConfig() {
        return HttpGlobalConfig.getInstance();
    }

    /**
     * normal get request
     */
    public static GetRequest get(String url) {
        return new GetRequest(url);
    }

    /**
     * normal post request
     */
    public static PostRequest post(String url) {
        return new PostRequest(url);
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
        // 默认加密套件
        ConnectionSpec cs = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2).build();
        List<ConnectionSpec> specs = new ArrayList<>();
        specs.add(cs);
        specs.add(ConnectionSpec.COMPATIBLE_TLS);
        specs.add(ConnectionSpec.CLEARTEXT);
        okHttpBuilder.connectionSpecs(specs);
        return okHttpBuilder;
    }

    public static Retrofit.Builder getRetrofitBuilder() {
        return retrofitBuilder;
    }

    /**
     * 获取到请求管理器
     *
     * @return requestManager
     */
    public static RequestManager manager() {
        return RequestManager.get();
    }
}
