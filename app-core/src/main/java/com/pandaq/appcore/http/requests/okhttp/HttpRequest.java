package com.pandaq.appcore.http.requests.okhttp;

import com.pandaq.appcore.http.api.Api;
import com.pandaq.appcore.http.requests.Request;

/**
 * Created by huxinyu on 2019/1/11.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : baseRequest for use okHttp lib, also can return an observable response
 */
public class HttpRequest<T extends HttpRequest> extends Request<T> {

    // http api,兼容 rxJava 观察者模式，需要返回观察对象时，将请求转换成 Retrofit 去请求
    private Api mApi;


}
