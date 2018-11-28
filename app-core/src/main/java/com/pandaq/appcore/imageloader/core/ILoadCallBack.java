package com.pandaq.appcore.imageloader.core;

import android.graphics.drawable.Drawable;

/**
 * Created by huxinyu on 2018/7/4.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :图片加载后的回调
 */
public interface ILoadCallBack {
    void loaded(Drawable drawable);
}
