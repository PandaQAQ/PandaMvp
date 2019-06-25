package com.pandaq.app_launcher.net;

import com.pandaq.app_launcher.entites.WanApiData;
import com.pandaq.app_launcher.entites.WxArticle;
import com.pandaq.app_launcher.entites.Zhihu;
import com.pandaq.appcore.network.annotation.ApiData;
import com.pandaq.appcore.network.annotation.RealEntity;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description : retrofit api 接口
 */
public interface ApiService {
    String BASE_URL = "https://wanandroid.com/";
    String TOKEN_HEADER = "token";

    /**
     * 玩 Android 注解使用自定义壳
     */
    @ApiData(clazz = WanApiData.class)
    @GET("wxarticle/chapters/json")
    Observable<List<WxArticle>> testApiData();

    /**
     * 纯文本 Test
     *
     * @return String
     */
    @RealEntity
    @GET("http://192.168.0.107:8080/HelloServlet")
    Observable<Boolean> stringTest();

    // 在线 mock 正常使用 ApiData 数据壳
    @GET("https://www.easy-mock.com/mock/5cef4b3e651e4075bad237f8/example/test")
    Observable<String> test();

    /**
     * 知乎日报 api 测试 @RealEntity注解不进行脱壳
     */
    @RealEntity
    @GET("http://news-at.zhihu.com/api/4/news/latest")
    Observable<Zhihu> zhihu();
}
