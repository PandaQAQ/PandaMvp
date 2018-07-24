package com.pandaq.appcore.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by huxinyu on 2018/5/18.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : dp 尺寸的互相转关工具
 */
public class DisplayUtils {
    private static final float HALF = 0.5F;

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue 要转换的dp值
     * @return 转换后的结果
     */
    public static int dp2px(float dipValue) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return (int) (dipValue * metrics.density);
    }


    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue 要转换的sp值
     * @param context context
     * @return sp转换为px的结果
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + HALF);
    }

    /**
     * 获取屏幕宽度
     *
     * @param context 上下文
     * @return 屏幕宽度
     */
    public static int getDisplayWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param context 上下文
     * @return 屏幕高度
     */
    public static int getDisplayHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

}
