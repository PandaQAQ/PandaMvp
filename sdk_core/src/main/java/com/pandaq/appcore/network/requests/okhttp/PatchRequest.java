package com.pandaq.appcore.network.requests.okhttp;

import com.pandaq.appcore.network.Panda;
import com.pandaq.appcore.network.observer.ApiObserver;
import com.pandaq.appcore.network.requests.okhttp.base.HttpRequest;

import java.lang.reflect.Type;

import io.reactivex.Observable;

/**
 * Created by huxinyu on 2019/6/3.
 * Email : panda.h@foxmail.com
 * Description :
 */
public class PatchRequest extends HttpRequest<PatchRequest> {
    public PatchRequest(String url) {
        super(url);
    }

    @Override
    protected <T> Observable<T> execute(Type type) {
        return mApi.patch(url, globalParams)
                .doOnSubscribe(disposable -> {
                    if (tag != null) {
                        Panda.manager().addTag(tag, disposable);
                    }
                })
                .compose(httpTransformer(type));
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