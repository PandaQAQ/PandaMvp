package com.pandaq.appcore.network.requests.okhttp;

import com.pandaq.appcore.network.observer.ApiObserver;

import java.lang.reflect.Type;

import io.reactivex.Observable;

/**
 * Created by huxinyu on 2019/3/16.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :http trace method
 */
public class TraceRequest extends HttpRequest<TraceRequest> {

    public TraceRequest(String url) {
        super(url);
    }

    @Override
    protected <T> Observable<T> execute(Type type) {
        return null;
    }

    @Override
    protected void execute(ApiObserver callback) {

    }
}
