package com.pandaq.appcore.http.requests;

import android.text.TextUtils;

import com.pandaq.appcore.BuildConfig;
import com.pandaq.appcore.http.Panda;
import com.pandaq.appcore.http.config.CONFIG;
import com.pandaq.appcore.http.config.HttpGlobalConfig;
import com.pandaq.appcore.http.ssl.SSLManager;
import com.pandaq.appcore.utils.CastUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by huxinyu on 2019/1/9.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :base http request
 */
public class Request<T extends Request> {

    // local basUrl
    private String baseUrl;
    // local readTimeout
    private Long readTimeout;
    // local writeTimeout
    private Long writeTimeout;
    // local connectTimeout
    private Long connectTimeout;
    // local retryCount
    private int retryCount = 1;

    private Map<String, String> headers = new LinkedHashMap<>();
    private List<Interceptor> interceptors = new ArrayList<>();
    private List<Interceptor> networkInterceptors = new ArrayList<>();

    // default global config
    private HttpGlobalConfig mGlobalConfig;

    protected Retrofit retrofit;

    /**
     * set request base url
     *
     * @param baseUrl base url
     * @return request
     */
    public T baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return CastUtils.cast(this);
    }

    /**
     * 重试次数
     *
     * @param retryCount 次数
     * @return 请求体
     */
    public T retry(int retryCount) {
        this.retryCount = retryCount;
        return CastUtils.cast(this);
    }

    /**
     * 添加 header map
     *
     * @param headers 请求头 map
     * @return 请求体
     */
    public T addHeaders(@NonNull Map<String, String> headers) {
        this.headers.putAll(headers);
        return CastUtils.cast(this);
    }

    /**
     * 添加 header map
     *
     * @param key   请求头 key
     * @param value 请求头 value
     * @return 请求体
     */
    public T addHeader(@NonNull String key, String value) {
        this.headers.put(key, value);
        return CastUtils.cast(this);
    }

    /**
     * 设置 header map，会覆盖之前的 header
     *
     * @param headers 请求头 map
     * @return 请求体
     */
    public T resetHeader(@NonNull Map<String, String> headers) {
        this.headers.clear();
        this.headers.putAll(headers);
        return CastUtils.cast(this);
    }

    /**
     * 移除指定 header
     *
     * @param key 请求头 key
     * @return 请求体
     */
    public T removeHeader(String key) {
        this.headers.remove(key);
        return CastUtils.cast(this);
    }

    /**
     * 移除全部 header
     *
     * @return 请求体
     */
    public T cleanHeader() {
        this.headers.clear();
        return CastUtils.cast(this);
    }

//    ##################### interceptors ####################

    public T interceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
        return CastUtils.cast(this);
    }

    public T netInterceptor(Interceptor interceptor) {
        networkInterceptors.add(interceptor);
        return CastUtils.cast(this);
    }

    public T clearInterceptor() {
        interceptors.clear();
        return CastUtils.cast(this);
    }

    public T clearNetInterceptor() {
        networkInterceptors.clear();
        return CastUtils.cast(this);
    }


    public T readTimeout() {
        return CastUtils.cast(this);
    }

    public T writeTimeout() {
        return CastUtils.cast(this);
    }

    public T connectTimeout() {
        return CastUtils.cast(this);
    }

    /**
     * 使用全局配置覆盖当前配置
     */
    private void resetGlobalParams() {
        mGlobalConfig = Panda.globalConfig();
        OkHttpClient.Builder builder = Panda.getOkHttpBuilder();

        // http client config
        if (mGlobalConfig.getConnectionPool() == null) {
            mGlobalConfig.connectionPool(new ConnectionPool(CONFIG.DEFAULT_MAX_IDLE_CONNECTIONS,
                    CONFIG.DEFAULT_KEEP_ALIVE_DURATION, TimeUnit.MILLISECONDS));
        }
        mGlobalConfig.connectionPool(mGlobalConfig.getConnectionPool());

        if (mGlobalConfig.getHostnameVerifier() == null) {
            mGlobalConfig.hostVerifier(new SSLManager.UnSafeHostnameVerifier(mGlobalConfig.getBaseUrl()));
        }
        builder.hostnameVerifier(mGlobalConfig.getHostnameVerifier());

        if (mGlobalConfig.getSslSocketFactory() == null) {
            mGlobalConfig.sslFactory(SSLManager.getSslSocketFactory(null, null, null));
        }
        builder.sslSocketFactory(mGlobalConfig.getSslSocketFactory());
        builder.connectTimeout(CONFIG.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(CONFIG.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(CONFIG.DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        // retrofit config
        Retrofit.Builder retrofitBuilder = Panda.getRetrofitBuilder();
        if (!TextUtils.isEmpty(mGlobalConfig.getBaseUrl())) {
            retrofitBuilder.baseUrl(mGlobalConfig.getBaseUrl());
        } else {
            throw new IllegalArgumentException("base url can not be empty !!!");
        }


    }

    /**
     * 注入本地配置参数
     */
    protected void injectLocalParams() {
        // 注入本地配置前先重置现有配置为全局默认配置
        resetGlobalParams();
        OkHttpClient.Builder okHttpBuilder = Panda.getOkHttpBuilder();
        Retrofit.Builder retrofitBuilder = Panda.getRetrofitBuilder();
    }
}
