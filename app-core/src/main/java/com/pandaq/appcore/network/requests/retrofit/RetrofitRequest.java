package com.pandaq.appcore.network.requests.retrofit;

import com.pandaq.appcore.network.requests.Request;

/**
 * Created by huxinyu on 2019/1/11.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : request for use retrofit
 */
public class RetrofitRequest extends Request<RetrofitRequest> {

    public RetrofitRequest() {
    }

    public <T> T create(Class<T> apiService) {
        injectLocalParams();
        return retrofit.create(apiService);
    }
}