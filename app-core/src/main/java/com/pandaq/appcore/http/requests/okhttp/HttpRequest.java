package com.pandaq.appcore.http.requests.okhttp;

import android.text.TextUtils;

import com.pandaq.appcore.http.api.Api;
import com.pandaq.appcore.http.observer.ApiObserver;
import com.pandaq.appcore.http.requests.Request;

import java.lang.reflect.Type;

import io.reactivex.Observable;

/**
 * Created by huxinyu on 2019/1/11.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : baseRequest for use okHttp lib, also can return an observable response
 */
public abstract class HttpRequest<T extends HttpRequest> extends Request<T> {

    // http api,兼容 rxJava 观察者模式，需要返回观察对象时，将请求转换成 Retrofit 去请求
    private Api mApi;
    private String url = "";

    public HttpRequest(String url) {
        if (!TextUtils.isEmpty(url)) {
            this.url = url;
        }
    }

    public <T> Observable<T> request(Type type) {
        injectLocalParams();
        return execute(type);
    }

    protected abstract <T> Observable<T> execute(Type type);

//    protected abstract <T> Observable<CacheResult<T>> cacheExecute(Type type);

    protected abstract void execute(ApiObserver<T> callback);
}
