package com.pandaq.app_launcher.net;

import io.reactivex.Observable;
import retrofit2.http.GET;

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
