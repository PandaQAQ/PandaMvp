package com.pandaq.appcore.http.requests.okhttp;

import com.pandaq.appcore.http.Panda;
import com.pandaq.appcore.http.RequestManager;
import com.pandaq.appcore.http.exception.ApiException;
import com.pandaq.appcore.http.observer.ApiObserver;
import com.pandaq.appcore.http.transformer.RetryFunc;
import com.pandaq.appcore.http.transformer.RxScheduler;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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
        return mApi.get(url, params)
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
