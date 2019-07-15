package com.pandaq.commonui.widget.gallery;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pandaq.commonui.R;

import java.util.ArrayList;
import java.util.List;

import android.support.annotation.NonNull;

/**
 * Created by huxinyu on 2019/4/3.
 * Email : panda.h@foxmail.com
 * Description : view 作为 page 的示例 Adapter，使用其他类型的 Page 参考此类自定义
 */
public class GalleryPageAdapter extends PagerAdapter {

    private List<String> pageData = new ArrayList<>();
    private boolean recycle;

    public GalleryPageAdapter(List<String> pageData, boolean recycle) {
        if (recycle) {
            // 制造循环的假数据
            String first = pageData.get(0) + "_";
            String second = pageData.get(1) + "_";
            String last = pageData.get(pageData.size() - 1) + "_";
            String secondLast = pageData.get(pageData.size() - 2) + "_";
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
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View page = LayoutInflater.from(container.getContext()).inflate(R.layout.res_item_gallery, null);
        TextView textView = page.findViewById(R.id.tv_position);
        textView.setText(pageData.get(position));
        container.addView(page);
        return page;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object page) {
        container.removeView((View) page);
    }

    public void bindPager(GalleryPager pager) {
        pager.setGalleryAdapter(this);
        pager.setCanRecycle(recycle);
        pager.setCurrentItem(2);
    }

    public List<String> getPages() {
        return pageData;
    }
}
