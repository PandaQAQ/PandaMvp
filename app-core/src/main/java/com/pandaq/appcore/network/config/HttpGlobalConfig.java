package com.pandaq.appcore.network.config;

import com.pandaq.appcore.network.Panda;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import androidx.annotation.NonNull;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/**
 * Created by huxinyu on 2019/1/9.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :http global config
 */
public class HttpGlobalConfig {

    //todo cache 和 cookie 暂时未做
    private CallAdapter.Factory callAdapterFactory;//Call适配器工厂
    private Converter.Factory converterFactory;//转换工厂
    private Call.Factory callFactory;//Call工厂
    private SSLSocketFactory sslSocketFactory;//SSL工厂
    private HostnameVerifier hostnameVerifier;//主机域名验证
    private ConnectionPool connectionPool;//连接池
    private Map<String, String> globalHeaders = new LinkedHashMap<>();//请求头
    private Map<String, String> globalParams = new LinkedHashMap<>();//请求参数
    private String baseUrl;//基础域名
    private long retryDelayMillis;//请求失败重试间隔时间
    private int retryCount;//请求失败重试次数
    private static HttpGlobalConfig sHttpGlobalConfig;
    private boolean isDebug;
    private Long apiSuccessCode = 0L;

    private HttpGlobalConfig() {
    }

    public static HttpGlobalConfig getInstance() {
        if (sHttpGlobalConfig == null) {
            synchronized (HttpGlobalConfig.class) {
                if (sHttpGlobalConfig == null) {
                    sHttpGlobalConfig = new HttpGlobalConfig();
                }
            }
        }
        return sHttpGlobalConfig;
    }

    public HttpGlobalConfig callAdapterFactory(@NonNull CallAdapter.Factory factory) {
        this.callAdapterFactory = factory;
        return this;
    }

    public HttpGlobalConfig converterFactory(@NonNull Converter.Factory factory) {
        this.converterFactory = factory;
        return this;
    }

    public HttpGlobalConfig callFactory(@NonNull Call.Factory factory) {
        this.callFactory = factory;
        return this;
    }

    public HttpGlobalConfig sslFactory(@NonNull SSLSocketFactory factory) {
        this.sslSocketFactory = factory;
        return this;
    }

    public HttpGlobalConfig hostVerifier(@NonNull HostnameVerifier verifier) {
        this.hostnameVerifier = verifier;
        return this;
    }

    public HttpGlobalConfig connectionPool(@NonNull ConnectionPool pool) {
        this.connectionPool = pool;
        return this;
    }

    public HttpGlobalConfig addGlobalHeader(@NonNull String key, String header) {
        this.globalHeaders.put(key, header);
        return this;
    }

    public HttpGlobalConfig globalHeader(@NonNull Map<String, String> headers) {
        this.globalHeaders = headers;
        return this;
    }

    public HttpGlobalConfig globalParams(@NonNull Map<String, String> params) {
        this.globalParams = params;
        return this;
    }

    public HttpGlobalConfig addGlobalParam(@NonNull String key, String param) {
        this.globalParams.put(key, param);
        return this;
    }

    public HttpGlobalConfig baseUrl(@NonNull String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public HttpGlobalConfig retryDelayMillis(long retryDelay) {
        this.retryDelayMillis = retryDelay;
        return this;
    }

    public HttpGlobalConfig retryCount(int retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    public HttpGlobalConfig interceptor(@NonNull Interceptor interceptor) {
        Panda.getOkHttpBuilder().addInterceptor(interceptor);
        return this;
    }

    public HttpGlobalConfig netInterceptor(@NonNull Interceptor netInterceptor) {
        Panda.getOkHttpBuilder().addNetworkInterceptor(netInterceptor);
        return this;
    }

    public HttpGlobalConfig readTimeout(long readTimeout) {
        Panda.getOkHttpBuilder().readTimeout(readTimeout, TimeUnit.MILLISECONDS);
        return this;
    }

    public HttpGlobalConfig writeTimeout(long writeTimeout) {
        Panda.getOkHttpBuilder().writeTimeout(writeTimeout, TimeUnit.MILLISECONDS);
        return this;
    }

    public HttpGlobalConfig connectTimeout(long connectTimeout) {
        Panda.getOkHttpBuilder().connectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
        return this;
    }

//    ######################################## getter ########################################

    public CallAdapter.Factory getCallAdapterFactory() {
        return callAdapterFactory;
    }

    public Converter.Factory getConverterFactory() {
        return converterFactory;
    }

    public Call.Factory getCallFactory() {
        return callFactory;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return sslSocketFactory;
    }

    public HostnameVerifier getHostnameVerifier() {
        return hostnameVerifier;
    }

    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public Map<String, String> getGlobalHeaders() {
        return globalHeaders;
    }

    public Map<String, String> getGlobalParams() {
        return globalParams;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public long getRetryDelayMillis() {
        return retryDelayMillis;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public HttpGlobalConfig debug(boolean debug) {
        isDebug = debug;
        return this;
    }

    public Long getApiSuccessCode() {
        return apiSuccessCode;
    }

    public HttpGlobalConfig apiSuccessCode(Long apiSuccessCode) {
        this.apiSuccessCode = apiSuccessCode;
        return this;
    }
}