package com.pandaq.appcore.network.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

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

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == String.class) {
            return new StringResponseBodyConverter();
        }
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
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
