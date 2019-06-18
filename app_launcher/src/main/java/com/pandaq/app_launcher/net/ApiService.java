package com.pandaq.app_launcher.net;

import com.pandaq.app_launcher.entites.WxArticle;
import com.pandaq.app_launcher.entites.Zhihu;

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

    @GET("wxarticle/chapters/json")
    Observable<List<WxArticle>> test();
}
