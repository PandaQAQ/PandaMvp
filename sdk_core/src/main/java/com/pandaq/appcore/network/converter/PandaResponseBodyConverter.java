package com.pandaq.appcore.network.converter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.pandaq.appcore.network.RxPanda;
import com.pandaq.appcore.network.entity.ApiData;
import com.pandaq.appcore.network.entity.EmptyData;
import com.pandaq.appcore.network.entity.IApiData;
import com.pandaq.appcore.network.exception.ApiException;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.NonNull;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by huxinyu on 2018/5/31.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :响应实体解析类,对返回数据实体做去壳处理
 */
public class PandaResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Gson gson;
    private TypeAdapter<T> typeAdapter;

    public PandaResponseBodyConverter(Gson gson, TypeAdapter<T> typeAdapter) {
        this.gson = gson;
        this.typeAdapter = typeAdapter;
    }

    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {
        String response = value.string();
        // 如是加密信息可在此处解密后再解析
        Class apiDataClazz = RxPanda.globalConfig().getApiDataClazz();
        IApiData apiData = gson.fromJson(response, (Type) apiDataClazz);
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
            // 获取解析数据,无 data 数据，默认以空对象替代
            String data = apiData.getData() != null ? new Gson().toJson(apiData.getData()) : new Gson().toJson(new EmptyData());
            if (!apiData.isSuccess()) {
                throw new ApiException(apiData.getCode(), apiData.getMsg(), data);
            } else {
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

    /**
     * 获取第一级type
     *
     * @param k clazz 对象
     * @return 泛型 Type
     */
    protected Type getType(Class k) {
        Type genType = k.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type type = params[0];
        Type finalNeedType;
        if (params.length > 1) {
            if (!(type instanceof ParameterizedType)) throw new IllegalStateException("没有填写泛型参数");
            finalNeedType = ((ParameterizedType) type).getActualTypeArguments()[0];
        } else {
            finalNeedType = type;
        }
        return finalNeedType;
    }
}
