package com.pandaq.appcore.http.interceptor;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by huxinyu on 2018/5/31.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :请求头拦截器，为每一次请求添加请求头
 */
public class HeaderInterceptor implements Interceptor {

    /**
     * 设置的 header
     */
    private Map<String, String> setHeaderMap;
    /**
     * 添加的 header
     */
    private Map<String, String> addHeaderMap;

    public HeaderInterceptor() {
        setHeaderMap = new HashMap<>();
        addHeaderMap = new HashMap<>();
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        if (checkNotNull(setHeaderMap)) {
            for (String key : setHeaderMap.keySet()) {
                builder.header(key, setHeaderMap.get(key));
            }
        }
        if (checkNotNull(addHeaderMap)) {
            for (String key : addHeaderMap.keySet()) {
                builder.addHeader(key, addHeaderMap.get(key));
            }
        }
        return chain.proceed(builder.build());
    }

    private boolean checkNotNull(Map map) {
        return map != null && !map.isEmpty();
    }

    /**
     * 设置请求头
     *
     * @param setHeaders 设置的请求头 map
     */
    public void setHeaders(Map<String, String> setHeaders) {
        this.setHeaderMap = setHeaderMap;
        if (checkNotNull(addHeaderMap) && checkNotNull(setHeaderMap)) {
            for (String key : setHeaderMap.keySet()) {
                this.addHeaderMap.remove(key);
            }
        }
    }

    /**
     * 添加请求头
     *
     * @param addHeaders 被添加的请求头 map
     */
    public void addHeaders(Map<String, String> addHeaders) {
        if (addHeaders != null) {
            this.addHeaderMap.putAll(addHeaders);
        }
    }
}
