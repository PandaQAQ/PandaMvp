package com.pandaq.appcore.http.requests;

import com.pandaq.appcore.http.PandaHttp;
import com.pandaq.appcore.http.config.HttpGlobalConfig;
import com.pandaq.appcore.utils.CastUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;

/**
 * Created by huxinyu on 2019/1/9.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :base http request
 */
public class Request<T extends Request> {

    private String baseUrl;
    private Long readTimeout;
    private Long writeTimeout;
    private Long connectTimeout;
    private int retryCount = 1;
    private HttpGlobalConfig mGlobalConfig;
    private Map<String, String> headers = new LinkedHashMap<>();
    private List<Interceptor> interceptors = new ArrayList<>();
    private List<Interceptor> networkInterceptors = new ArrayList<>();


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
     * 注入全局配置参数
     */
    protected void initGlobalParams() {
        mGlobalConfig = PandaHttp.globalConfig();

    }

    /**
     * 注入本地配置参数
     */
    protected void initLocalParams() {

    }
}
