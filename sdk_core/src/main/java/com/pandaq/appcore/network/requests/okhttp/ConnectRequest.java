package com.pandaq.appcore.network.requests.okhttp;

import com.pandaq.appcore.network.Panda;
import com.pandaq.appcore.network.observer.ApiObserver;

import java.lang.reflect.Type;

import io.reactivex.Observable;

/**
 * Created by huxinyu on 2019/3/16.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :http connect request
 */
public class ConnectRequest extends HttpRequest<ConnectRequest> {

    public ConnectRequest(String url) {
        super(url);
    }

    @Override
    protected <T> Observable<T> execute(Type type) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void execute(ApiObserver callback) {
        if (tag != null) {
            Panda.manager().addTag(tag, callback);
        }
        this.execute(getType(callback)).subscribe(callback);
    }
}
