package com.pandaq.appcore.network.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.pandaq.appcore.network.annotation.ApiData;
import com.pandaq.appcore.network.annotation.RealEntity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import androidx.annotation.Nullable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by huxinyu on 2018/5/31.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :自定义解析工厂类
 */
public class PandaConvertFactory extends Converter.Factory {

    public static PandaConvertFactory create() {
        return create(new Gson());
    }

    private Gson gson;

    private static PandaConvertFactory create(Gson gson) {
        if (gson == null) throw new NullPointerException("gson is null");
        return new PandaConvertFactory(gson);
    }

    private PandaConvertFactory(Gson gson) {
        this.gson = gson;
    }

    /**
     * 这里这个 type 是本地接口方法中传入的泛型类型
     *
     * @param type        Retrofit 接口传入的泛型对象
     * @param annotations Retrofit 接口的注解
     * @param retrofit    Retrofit 对象
     * @return 转换器
     */
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        for (Annotation annotation : annotations) {
            // PandaResponseBodyConverter 转换时不使用 ApiData 剥壳，直接转换为接口中定义的对象
            if (annotation instanceof RealEntity) {
                return new GsonResponseBodyConverter<>(gson, adapter);
            }
            // 指定某一接口自己的 App壳
            if (annotation instanceof ApiData) {
                return new PandaResponseBodyConverter<>(gson, adapter, ((ApiData) annotation).clazz());
            }
        }
        return new PandaResponseBodyConverter<>(gson, adapter);
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new PandaRequestBodyConverter<>(gson, adapter);
    }

    @Nullable
    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return super.stringConverter(type, annotations, retrofit);
    }
}
