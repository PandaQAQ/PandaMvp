package com.pandaq.pandacore.http;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by huxinyu on 2018/5/22.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : Api 管理类
 */
public class ApiManager<T> {

    private static ApiManager sApiManager;
    private T apiService;

    /**
     * 获取 ApiManger 静态对象
     * @return ApiManager 对象
     */
    public static synchronized ApiManager getDefault(){
        if (sApiManager==null){
            sApiManager = new ApiManager();
        }
        return sApiManager;
    }

    /**
     * 获取 Api 接口对象
     *
     * @param clazz 接口管理对象 class
     * @return 结口对象
     */
    public T getApiService(Class<T> clazz) {
        if (apiService==null){
            Retrofit retrofit = new Retrofit.Builder()
                    //                    .client(mClient)
                    //                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService = retrofit.create(clazz);
        }
        return apiService;
    }

    /**
     * 重置 ApiManger
     */
    public void reset(){
        sApiManager = null;
        apiService = null;
    }

    public static class Config{
        private OkHttpClient mHttpClient;
    }
}
