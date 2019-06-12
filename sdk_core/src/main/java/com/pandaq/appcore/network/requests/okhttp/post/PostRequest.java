package com.pandaq.appcore.network.requests.okhttp.post;

import com.pandaq.appcore.network.RxPanda;
import com.pandaq.appcore.network.observer.ApiObserver;
import com.pandaq.appcore.network.requests.okhttp.base.HttpRequest;

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
    private StringBuilder stringBuilder = new StringBuilder();


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
                        RxPanda.manager().addTag(tag, disposable);
                    }
                })
                .compose(httpTransformer(type));
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void execute(ApiObserver callback) {
        if (tag != null) {
            RxPanda.manager().addTag(tag, callback);
        }
        //获取到callback 中的泛型类型
        this.execute(getType(callback)).subscribe(callback);
    }

    /**
     * post url 中添加参数
     *
     * @param paramKey key
     * @param paramValue value
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
