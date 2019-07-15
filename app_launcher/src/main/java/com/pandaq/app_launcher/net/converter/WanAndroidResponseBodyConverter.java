package com.pandaq.app_launcher.net.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.pandaq.app_launcher.net.entity.WanAndroidData;
import com.pandaq.rxpanda.exception.ApiException;

import java.io.IOException;

import android.support.annotation.NonNull;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by huxinyu on 2018/5/31.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :响应实体解析类,对返回数据实体做去壳处理
 */
public class WanAndroidResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Gson gson;
    private TypeAdapter<T> typeAdapter;

    public WanAndroidResponseBodyConverter(Gson gson, TypeAdapter<T> typeAdapter) {
        this.gson = gson;
        this.typeAdapter = typeAdapter;
    }

    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {
        String response = value.string();
        WanAndroidData apiData = gson.fromJson(response, new TypeToken<WanAndroidData>() {}.getType());
        /* 如是按约定格式返回数据 apiData 中的 code 是必须的。
         * 因此可以用 code 是否存在来判断数据是否合法
         */
        if (apiData.getCode() == null) {
            try {
                return typeAdapter.fromJson(response);
            } finally {
                value.close();
            }
        } else {
            // 获取解析数据
            String data = apiData.getData() != null ? new Gson().toJson(apiData.getData()) : "{}";
            if (!apiData.isSuccess()) {
                throw new ApiException(apiData.getCode(), apiData.getMsg(), data);
            }
            try {
                return typeAdapter.fromJson(data);
            } catch (Exception e) {
                throw new ApiException(apiData.getCode(), "illegal data type!!!", data);
            } finally {
                value.close();
            }
        }
    }
}
