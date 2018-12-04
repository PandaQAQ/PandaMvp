package com.pandaq.appcore.http.converter;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by huxinyu on 2018/5/31.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :String 类型解析
 */
public class StringResponseBodyConverter implements Converter<ResponseBody, String> {
    @Override
    public String convert(@NonNull ResponseBody value) throws IOException {
        try {
            return value.string();
        } finally {
            value.close();
        }
    }
}
