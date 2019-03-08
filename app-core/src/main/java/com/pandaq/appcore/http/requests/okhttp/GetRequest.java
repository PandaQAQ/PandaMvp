package com.pandaq.appcore.http.requests.okhttp;

import com.pandaq.appcore.http.observer.ApiObserver;

import java.lang.reflect.Type;

import io.reactivex.Observable;

/**
 * Created by huxinyu on 2019/1/11.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
public class GetRequest extends HttpRequest<GetRequest> {

    public GetRequest(String path) {
        super(path);
    }

    @Override
    protected <T> Observable<T> execute(Type type) {
        return null;
    }

    @Override
    protected void execute(ApiObserver<GetRequest> callback) {

    }
}
