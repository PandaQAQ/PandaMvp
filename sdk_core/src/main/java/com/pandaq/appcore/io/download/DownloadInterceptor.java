package com.pandaq.appcore.io.download;

import com.pandaq.appcore.io.TransmitCallback;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by huxinyu on 2018/6/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :下载拦截器
 */
public class DownloadInterceptor implements Interceptor {

    private TransmitCallback mCallback;

    public DownloadInterceptor(TransmitCallback callback) {
        mCallback = callback;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        ProgressResponseBody responseBody = new ProgressResponseBody(response.body(), mCallback);
        return response.newBuilder()
                .body(responseBody)
                .build();
    }
}
