package com.pandaq.appcore.imageloader.glide;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

import androidx.annotation.NonNull;

/**
 * Created by huxinyu on 2018/6/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :Glide 4.0+ 触发注解生成 GlideApp 类
 */
@GlideModule
public class GlideMoudle extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        int memoryCacheSizeBytes = 1024 * 1024 * 10; // 10mb 内存缓存
        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));
    }

}
