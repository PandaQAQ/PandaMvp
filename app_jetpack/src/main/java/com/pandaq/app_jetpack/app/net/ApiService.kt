package com.pandaq.app_jetpack.app.net

import com.pandaq.app_jetpack.app.entity.WanAndroidData
import com.pandaq.app_jetpack.app.entity.WanApiData
import com.pandaq.app_jetpack.app.entity.ZhihuData
import com.pandaq.rxpanda.annotation.ApiData
import com.pandaq.rxpanda.annotation.RealEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description : retrofit api 接口
 */
interface ApiService {
    @RealEntity
    @GET("news/latest")
    suspend fun test(): ZhihuData?

    @RealEntity
    @GET("news/before/{date}")
    fun history(@Path("date") date: String?): Observable<ZhihuData>

    @ApiData(clazz = WanApiData::class)
    @GET("https://wanandroid.com/article/listproject/0/json")
    suspend fun wanAndroid(): WanAndroidData?
}