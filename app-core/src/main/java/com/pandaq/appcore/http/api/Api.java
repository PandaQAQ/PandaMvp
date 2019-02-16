package com.pandaq.appcore.http.api;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by huxinyu on 2019/2/15.
 * Email : panda.h@foxmail.com
 * Description : http request apis, make requests fit rxJava Observable
 */
public interface Api {

    @GET()
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String,String> params);


}
