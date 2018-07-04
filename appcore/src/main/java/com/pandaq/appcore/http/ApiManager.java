package com.pandaq.appcore.http;

import android.text.TextUtils;

import com.pandaq.appcore.http.converter.PandaConvertFactory;
import com.pandaq.appcore.http.interceptor.HeaderInterceptor;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by huxinyu on 2018/5/22.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : Api 管理类
 */
public class ApiManager<T> {
    /**
     * 单利对象
     */
    private static ApiManager sApiManager;
    /**
     * api 接口类，由使用的 module app 提供
     */
    private T apiService;
    /**
     * 接口基准地址 Url
     */
    private String baseUrl;
    /**
     * 是否处于 debug 模式
     */
    private boolean debug;
    /**
     * 网络请求 client builder
     * 外部设置为设置 client 但此类中会转换为对应 builder 以便添加拦截器
     */
    private OkHttpClient.Builder clientBuilder;
    /**
     * 被添加的 header（not recover）
     */
    private Map<String, String> addHeaderMap;
    /**
     * 被设置的 header（recover)
     */
    private Map<String, String> setHeaderMap;

    /**
     * 获取 ApiManger 静态对象
     *
     * @return ApiManager 对象
     */
    private static synchronized ApiManager getDefault() {
        if (sApiManager == null) {
            sApiManager = new ApiManager();
        }
        return sApiManager;
    }

    /**
     * 获取 Api 接口对象
     *
     * @param clazz 接口管理对象 class
     * @return 结口对象
     */
    public T getApiService(Class<T> clazz) {
        if (apiService == null) {
            Retrofit.Builder builder = new Retrofit.Builder();
            if (clientBuilder == null) {
                clientBuilder = new OkHttpClient.Builder();
            }
            HeaderInterceptor headerInterceptor = new HeaderInterceptor();
            if (addHeaderMap != null) {
                headerInterceptor.addHeaders(addHeaderMap);
            }
            if (setHeaderMap != null) {
                headerInterceptor.setHeaders(setHeaderMap);
            }
            clientBuilder.addNetworkInterceptor(headerInterceptor);
            if (debug) {
                clientBuilder.addNetworkInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY));
            }
            if (TextUtils.isEmpty(baseUrl) || !baseUrl.startsWith("http")) {
                throw new RuntimeException("baseUrl is illegal !!! please check it !!!");
            }
            builder.baseUrl(baseUrl)
                    .client(clientBuilder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(PandaConvertFactory.create())
                    .build();
            apiService = builder
                    .build()
                    .create(clazz);
        }
        return apiService;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }


    private void setAddHeaderMap(Map<String, String> addHeaderMap) {
        this.addHeaderMap = addHeaderMap;
    }

    private void setSetHeaderMap(Map<String, String> setHeaderMap) {
        this.setHeaderMap = setHeaderMap;
    }

    public OkHttpClient.Builder getClientBuilder() {
        return clientBuilder;
    }

    public void setClientBuilder(OkHttpClient.Builder clientBuilder) {
        this.clientBuilder = clientBuilder;
    }

    /**
     * 重置 ApiManger
     */
    private static void reset() {
        sApiManager = null;
    }

    public Builder newBuilder() {
        Builder builder = new Builder()
                .debug(debug)
                .addHeaders(addHeaderMap)
                .setHeaders(setHeaderMap)
                .baseUrl(baseUrl);
        if (clientBuilder != null) {
            builder.client(clientBuilder.build());
        }
        return builder;
    }

    /**
     * 配置构造类
     */
    public static class Builder {
        private Map<String, String> setHeaderMap;
        private Map<String, String> addHeaderMap;
        private OkHttpClient okHttpClient;
        private boolean debug;
        private String baseUrl;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder debug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public Builder client(OkHttpClient client) {
            this.okHttpClient = client;
            return this;
        }

        public Builder setHeaders(Map<String, String> setHeaderMap) {
            this.setHeaderMap = setHeaderMap;
            if (addHeaderMap != null) {
                for (String key : setHeaderMap.keySet()) {
                    addHeaderMap.remove(key);
                }
            }
            return this;
        }

        public Builder setHeader(String key, String value) {
            if (this.setHeaderMap == null) {
                this.setHeaderMap = new HashMap<>();
            }
            this.setHeaderMap.put(key, value);
            if (this.addHeaderMap != null) {
                addHeaderMap.remove(key);
            }
            return this;
        }

        public Builder addHeaders(Map<String, String> addHeaderMap) {
            if (this.addHeaderMap == null) {
                this.addHeaderMap = new HashMap<>();
            }
            this.addHeaderMap.putAll(addHeaderMap);
            return this;
        }

        public Builder addHeader(String key, String value) {
            if (this.addHeaderMap == null) {
                this.addHeaderMap = new HashMap<>();
            }
            addHeaderMap.put(key, value);
            return this;
        }

        public ApiManager build() {
            reset();
            ApiManager apiManager = ApiManager.getDefault();
            apiManager.setBaseUrl(baseUrl);
            if (setHeaderMap != null) {
                apiManager.setSetHeaderMap(setHeaderMap);
            }
            if (addHeaderMap != null) {
                apiManager.setAddHeaderMap(addHeaderMap);
            }
            apiManager.setDebug(debug);
            if (okHttpClient != null) {
                apiManager.setClientBuilder(okHttpClient.newBuilder());
            }
            return apiManager;
        }
    }
}
