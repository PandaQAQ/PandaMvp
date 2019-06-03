package com.pandaq.appcore.network.requests.okhttp;

import com.pandaq.appcore.network.observer.ApiObserver;

import java.lang.reflect.Type;

import io.reactivex.Observable;

/**
 * Created by huxinyu on 2019/3/16.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :request in delete method
 */
public class DeleteRequest extends HttpRequest<DeleteRequest>{
    public DeleteRequest(String url) {
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
