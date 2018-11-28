package com.pandaq.appcore.transmitter.upload;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.pandaq.appcore.transmitter.TransmitCallback;
import com.pandaq.appcore.transmitter.UploadResponse;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by huxinyu on 2018/6/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :进度监听拦截器
 */
public class UploadInterceptor implements Interceptor {

    private TransmitCallback mCallback;

    public UploadInterceptor(@NonNull TransmitCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        ProgressRequestBody requestBody = new ProgressRequestBody(request.body(), mCallback);
        Request finalRequest = request.newBuilder()
                .addHeader("Connection", "alive")
                .method(request.method(), requestBody)
                .build();
        Response response = chain.proceed(finalRequest);
        ResponseBody body = response.body();
        if (body != null) {
            UploadResponse data = new Gson().fromJson(body.string(), UploadResponse.class);
            if (data != null && data.getCode().equals("200")) {
                mCallback.done(data.getData());
            } else {
                mCallback.onFailed(new RuntimeException("unknown err!"));
            }
        }
        return response;
    }
}
