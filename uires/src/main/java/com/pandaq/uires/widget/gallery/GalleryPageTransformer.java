package com.pandaq.uires.widget.gallery;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by huxinyu on 2019/4/3.
 * Email : panda.h@foxmail.com
 * Description :
 */
public class GalleryPageTransformer implements ViewPager.PageTransformer {

    private float maxScale = 1.0f;
    private float minScale = 1.0f;

    public GalleryPageTransformer() {
    }

    public GalleryPageTransformer(float maxScale, float minScale) {
        this.maxScale = maxScale;
        this.minScale = minScale;
    }

    @Override
    public void transformPage(@NonNull View page, float position) {
        if (position < -1) {
            page.setScaleX(minScale);
            page.setScaleY(minScale);
        } else if (position <= 1) { //a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0
            float scaleFactor = minScale + (1 - Math.abs(position)) * (maxScale - minScale);
            page.setScaleX(scaleFactor);
            //每次滑动后进行微小的移动目的是为了防止在三星的某些手机上出现两边的页面为显示的情况
            if (position > 0) {
                page.setTranslationX(-scaleFactor * 2);
            } else if (position < 0) {
                page.setTranslationX(scaleFactor * 2);
            }
            page.setScaleY(scaleFactor);
        } else { // (1,+Infinity]
            page.setScaleX(minScale);
            page.setScaleY(minScale);
        }
    }

}
