package com.pandaq.artqrcode;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by huxinyu on 2018/5/7.
 * Email : panda.h@foxmail.com
 * Description :二维码图像处理的工具类
 */
public class Utils {

    /**
     * 将 bitmap 图片像素颜色深浅二值化
     *
     * @param bitmap    资源 bitmap
     * @param threshold 阈值
     */
    private static Bitmap binarize(Bitmap bitmap, int threshold) {
        int red;
        int green;
        int black;
        for (int y = 0; y < bitmap.getHeight(); y++) {
            for (int x = 0; x < bitmap.getHeight(); x++) {
                int color = bitmap.getPixel(x, y);
                red = (color >> 16) & 0xFF;
                green = (color >> 8) & 0xFF;
                black = color & 0xFF;
                float sum = 0.30f * red + 0.59f * green + 0.11f * black;
                bitmap.setPixel(x, y, sum > threshold ? Color.WHITE : Color.BLACK);
            }
        }
        return bitmap;
    }

}
