package com.pandaq.pandamvp.network;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description :本地请求拦截器，对网络请求做前置或后置拦截，参数增删等
 */
public class LocalIntercepter implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        return chain.proceed(chain.request());
    }
}
