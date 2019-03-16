package com.pandaq.appcore.http.requests.okhttp;

import com.pandaq.appcore.http.Panda;
import com.pandaq.appcore.http.observer.ApiObserver;

import java.lang.reflect.Type;

import io.reactivex.Observable;

/**
 * Created by huxinyu on 2019/1/11.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : get Request
 */
public class GetRequest extends HttpRequest<GetRequest> {

    public GetRequest(String url) {
        super(url);
    }

    @Override
    protected <T> Observable<T> execute(Type type) {
        return mApi.get(url, globalParams)
                .doOnSubscribe(disposable -> {
                    if (tag != null) {
                        Panda.manager().addTag(tag, disposable);
                    }
                })
                .compose(httpTransformer(type));
    }

    @Override
    protected void execute(ApiObserver callback) {
        if (tag != null) {
            Panda.manager().addTag(tag, callback);
        }
        this.execute(getType(callback)).subscribe(callback);
    }


}
