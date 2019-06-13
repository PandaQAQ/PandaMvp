package com.pandaq.appcore.utils.system;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

/**
 * Created by huxinyu on 2019/1/7.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :应用资源工具类
 */
public class ResourceUtils {

    private ResourceUtils() {
        // private constructor
    }

    /**
     * 获取资源字符串
     *
     * @param string 资源 id
     * @return 字符串
     */
    public static String getString(@StringRes int string) {
        return AppUtils.applicationContext().getString(string);
    }

    /**
     * 获取资源 color
     *
     * @param color 资源 id
     * @return 资源 color
     */
    public static int getColor(@ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return AppUtils.applicationContext().getColor(color);
        } else {
            return AppUtils.applicationContext().getResources().getColor(color, null);
        }
    }

    /**
     * 获取 drawable 资源
     *
     * @param drawable 资源 id
     * @return 资源 drawable
     */
    public static Drawable getDrawable(@DrawableRes int drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return AppUtils.applicationContext().getDrawable(drawable);
        } else {
            return AppUtils.applicationContext().getResources().getDrawable(drawable, null);
        }
    }

    /**
     * @param fontPath 字库路径
     * @return 字库类型
     */
    public static Typeface getAssetTypeFace(String fontPath) {
        return Typeface.createFromAsset(AppUtils.applicationContext().getAssets(),
                fontPath);
    }
}
