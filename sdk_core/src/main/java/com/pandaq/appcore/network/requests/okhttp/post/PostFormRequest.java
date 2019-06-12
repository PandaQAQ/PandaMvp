package com.pandaq.appcore.network.requests.okhttp.post;

import com.pandaq.appcore.network.RxPanda;
import com.pandaq.appcore.network.observer.ApiObserver;
import com.pandaq.appcore.network.requests.okhttp.base.HttpRequest;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by huxinyu on 2019/3/15.
 * Email : panda.h@foxmail.com
 * Description :表单方式 post
 */
public class PostFormRequest extends HttpRequest<PostFormRequest> {

    // 表单方式 post
    private Map<String, Object> forms = new LinkedHashMap<>();
    // url中带参数 post
    private StringBuilder stringBuilder = new StringBuilder();


    public PostFormRequest(String url) {
        super(url);
    }

    @Override
    protected <T> Observable<T> execute(Type type) {
        if (stringBuilder.length() != 0) {
            url = url + stringBuilder.toString();
        }
        if (globalParams.size() > 0) {
            forms.putAll(globalParams);
            return mApi.postForm(url, forms)
                    .doOnSubscribe(disposable -> {
                        if (tag != null) {
                            RxPanda.manager().addTag(tag, disposable);
                        }
                    })
                    .compose(httpTransformer(type));
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
        this.execute(getType(callback)).subscribe(callback);
    }

    /**
     * post url 中添加参数
     *
     * @param paramKey key
     * @param paramValue value
     * @return self
     */
    public PostFormRequest urlParams(String paramKey, String paramValue) {
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

    /**
     * @param paramKey   表单数据 key
     * @param paramValue 表单数据 value
     * @return self
     */
    public PostFormRequest formParams(String paramKey, Object paramValue) {
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