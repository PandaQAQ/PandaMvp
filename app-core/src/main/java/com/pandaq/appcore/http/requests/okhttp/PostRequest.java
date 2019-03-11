package com.pandaq.appcore.http.requests.okhttp;

import com.pandaq.appcore.http.observer.ApiObserver;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by huxinyu on 2019/1/11.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :a sample request
 */
public class PostRequest extends HttpRequest<PostRequest> {

    // 表单方式 post
    protected Map<String, Object> forms = new LinkedHashMap<>();
    // url中带参数 post
    protected StringBuilder stringBuilder = new StringBuilder();


    // body Post
    protected RequestBody requestBody;
    // body post 参数类型
    protected MediaType mediaType;
    // body post 内容
    protected String content;

    public PostRequest(String url) {
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
