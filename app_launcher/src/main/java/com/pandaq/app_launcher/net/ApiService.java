package com.pandaq.app_launcher.net;

import com.pandaq.app_launcher.entites.Zhihu;
import com.pandaq.rxpanda.annotation.RealEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description : retrofit api 接口
 */
public interface ApiService {
    String BASE_URL = "https://wanandroid.com/";

    /**
     * 知乎日报 api 测试 @RealEntity注解不进行脱壳
     */
    @RealEntity
    @GET("http://news-at.zhihu.com/api/4/news/latest")
    Observable<Zhihu> zhihu();
}
