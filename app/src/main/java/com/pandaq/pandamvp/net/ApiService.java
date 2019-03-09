package com.pandaq.pandamvp.net;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description : retrofit api 接口
 */
public interface ApiService {
    String BASE_URL = "http://news-at.zhihu.com/api/4/";
    String TOKEN_HEADER = "token";

    @GET("news/latest")
    Observable<String> test();
}
