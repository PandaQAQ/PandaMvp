package com.pandaq.pandamvp.net;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description :公共拦截器，对网络请求做前置或后置拦截，参数增删等
 */
public class CommonIntercepter implements Interceptor {

    private Map<String, String> headers;

    private CommonIntercepter(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        return chain.proceed(builder.build());
    }

    public static class Builder {
        private Map<String, String> headers;
        private Map<String, String> params;

        public Builder() {
            if (headers == null) {
                headers = new HashMap<>();
            }
        }

        public Builder addHeader(String key, String value) {
            headers.put(key, value);
            return this;
        }

        public Builder addHeaders(Map<String, String> headers) {
            if (this.headers == null) {
                this.headers = headers;
            } else {
                this.headers.putAll(headers);
            }
            return this;
        }

        public Builder addParam(String key, String value) {
            params.put(key, value);
            return this;
        }

        public Builder addParams(Map<String, String> params) {
            if (this.params == null) {
                this.params = headers;
            } else {
                this.params.putAll(params);
            }
            return this;
        }

        public CommonIntercepter build() {
            return new CommonIntercepter(headers);
        }
    }
}
