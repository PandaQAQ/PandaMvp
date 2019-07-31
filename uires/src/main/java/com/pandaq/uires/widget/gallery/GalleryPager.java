package com.pandaq.uires.widget.gallery;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by huxinyu on 2019/4/3.
 * Email : panda.h@foxmail.com
 * Description :画廊 ViewPager
 */
public class GalleryPager extends ViewPager {

    private boolean canRecycle = false;
    private GalleryPageAdapter mPageAdapter;
    private int currentPosition = 0;
    private GalleryPageTransformer mTransformer;

    public GalleryPager(@NonNull Context context) {
        super(context);
        init();
    }

    public GalleryPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public boolean isCanRecycle() {
        return canRecycle;
    }

    public void setCanRecycle(boolean canRecycle) {
        this.canRecycle = canRecycle;
    }

    private void init() {
        mTransformer = new GalleryPageTransformer();
    }

    private void initAfterSetData() {
        if (mPageAdapter == null || mPageAdapter.getPages() == null) {
            throw new RuntimeException("make sure adapter.getPages() is not null");
        }
        setPageTransformer(true, mTransformer);
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (!canRecycle) return; // 不需要循环则不做处理
                if (currentPosition == mPageAdapter.getPages().size() - 2) {
                    // 倒数第二页，页面重置为第0页
                    setCurrentItem(2, false);
                } else if (currentPosition == 1) {
                    // 第一页后页面重置为最后页
                    setCurrentItem(mPageAdapter.getPages().size() - 3, false);
                }
            }
        });
    }

    public void setGalleryAdapter(GalleryPageAdapter adapter) {
        mPageAdapter = adapter;
        setAdapter(mPageAdapter);
        initAfterSetData();
    }
}
