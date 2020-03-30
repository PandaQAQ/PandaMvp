package com.pandaq.app_jetpack.app.net;

import com.pandaq.app_jetpack.app.entity.ZhihuData;
import com.pandaq.rxpanda.annotation.RealEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description : retrofit api 接口
 */
public interface ApiService {

    @RealEntity
    @GET("news/latest")
    Observable<ZhihuData> test();

    @RealEntity
    @GET("news/before/{date}")
    Observable<ZhihuData> history(@Path("date") String date);
}
