package com.pandaq.uires.widget.gallery;

import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/**
 * Created by huxinyu on 2019/4/3.
 * Email : panda.h@foxmail.com
 * Description : view 作为 page 的示例 Adapter，使用其他类型的 Page 参考此类自定义
 */
public abstract class GalleryPageAdapter extends PagerAdapter {

    protected List<IPagerItem> pageData = new ArrayList<>();
    private boolean recycle;

    public GalleryPageAdapter(List<IPagerItem> pageData, boolean recycle) {
        if (recycle && pageData.size() > 1) {
            // 制造循环的假数据
            IPagerItem first = pageData.get(0);
            IPagerItem second = pageData.get(1);
            IPagerItem last = pageData.get(pageData.size() - 1);
            IPagerItem secondLast = pageData.get(pageData.size() - 2);
            // 这里为了保证动画的流畅性在首位分别加两个  123 变为 23 123 12
            pageData.add(first);
            pageData.add(second);
            pageData.add(0, last);
            pageData.add(0, secondLast);
        }
        this.pageData.addAll(pageData);
        this.recycle = recycle;
    }

    @Override
    public int getCount() {
        return pageData.size();
    }

    @Override
    public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {
        return (view == object);
    }

    @NonNull
    @Override
    public View instantiateItem(@NonNull ViewGroup container, int position) {
        return instantiatePage(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object page) {
        container.removeView((View) page);
    }

    public abstract View instantiatePage(@NonNull ViewGroup container, int position);

    public void bindPager(GalleryPager pager) {
        pager.setOffscreenPageLimit(pageData.size());
        pager.setGalleryAdapter(this);
        pager.setCanRecycle(recycle);
        pager.setCurrentItem(2);
        pager.startPlay();
    }

    public List<IPagerItem> getPages() {
        return pageData;
    }
}
