package com.pandaq.appcore.http.requests.okhttp.post;

import com.pandaq.appcore.http.Panda;
import com.pandaq.appcore.http.observer.ApiObserver;
import com.pandaq.appcore.http.requests.okhttp.HttpRequest;

import java.lang.reflect.Type;

import io.reactivex.Observable;

/**
 * Created by huxinyu on 2019/1/11.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :a sample request
 */
public class PostRequest extends HttpRequest<PostRequest> {

    // url中带参数 post
    protected StringBuilder stringBuilder = new StringBuilder();


    public PostRequest(String url) {
        super(url);
    }

    @Override
    protected <T> Observable<T> execute(Type type) {
        if (stringBuilder.length() != 0) {
            url = url + stringBuilder.toString();
        }
        return mApi.post(url, globalParams)
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

    /**
     * post url 中添加参数
     *
     * @param paramKey
     * @param paramValue
     * @return self
     */
    public PostRequest urlParams(String paramKey, String paramValue) {
        if (paramKey != null && paramValue != null) {
            if (stringBuilder.length() == 0) {
                stringBuilder.append("?");
            } else {
                stringBuilder.append("&");
            }
            stringBuilder.append(paramKey).append("=").append(paramValue);
        }
        return this;
    }
}
