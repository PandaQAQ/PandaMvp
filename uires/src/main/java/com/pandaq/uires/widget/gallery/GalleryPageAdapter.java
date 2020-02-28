package com.pandaq.uires.widget.gallery;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pandaq.appcore.imageloader.core.PicLoader;
import com.pandaq.uires.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huxinyu on 2019/4/3.
 * Email : panda.h@foxmail.com
 * Description : view 作为 page 的示例 Adapter，使用其他类型的 Page 参考此类自定义
 */
public class GalleryPageAdapter extends PagerAdapter {

    private List<IPagerItem> pageData = new ArrayList<>();
    private boolean recycle;
    private PageItemClickListener pageClickListener;

    public GalleryPageAdapter(List<IPagerItem> pageData, boolean recycle) {
        if (recycle) {
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
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View page = LayoutInflater.from(container.getContext()).inflate(R.layout.res_item_gallery, null);
        ImageView target = page.findViewById(R.id.iv_icon);
        TextView title = page.findViewById(R.id.tv_position);
        String url = pageData.get(position).getUrl().trim();
        title.setText(pageData.get(position).getTitleStr().trim());
        PicLoader.with(container.getContext())
                .load(url)
                .asCircle(8)
                .into(target);
        container.addView(page);
        page.setOnClickListener(v -> {
            if (pageClickListener != null) {
                pageClickListener.onClick(pageData.get(position));
            }
        });
        return page;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object page) {
        container.removeView((View) page);
    }

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

    public void setOnPageClickListener(PageItemClickListener listener) {
        this.pageClickListener = listener;
    }

    public interface PageItemClickListener {
        void onClick(IPagerItem item);
    }
}
