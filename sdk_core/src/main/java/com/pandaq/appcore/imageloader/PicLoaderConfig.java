package com.pandaq.appcore.imageloader;

import android.support.annotation.DrawableRes;

/**
 * Created by huxinyu on 2019/9/29.
 * Email : panda.h@foxmail.com
 * Description :图片加载库配置
 */
public class PicLoaderConfig {

    private static PicLoaderConfig sPicLoaderConfig;

    public static PicLoaderConfig get() {
        if (sPicLoaderConfig == null) {
            sPicLoaderConfig = new PicLoaderConfig();
        }
        return sPicLoaderConfig;
    }

    @DrawableRes
    private int placeHolder = -1;

    @DrawableRes
    private int error = -1;

    public int getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(int placeHolder) {
        this.placeHolder = placeHolder;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
