package com.pandaq.uires.widget.gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pandaq.appcore.imageloader.core.PicLoader;
import com.pandaq.uires.R;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by huxinyu on 2020/4/7.
 * Email : panda.h@foxmail.com
 * Description :
 */
public class NormalBannerAdapter extends GalleryPageAdapter {
    private PageItemClickListener pageClickListener;

    public NormalBannerAdapter(List<IPagerItem> pageData, boolean recycle) {
        super(pageData, recycle);
    }

    @Override
    public View instantiatePage(@NonNull ViewGroup container, int position) {
        View page = LayoutInflater.from(container.getContext()).inflate(R.layout.res_item_gallery, null);
        ImageView target = page.findViewById(R.id.iv_icon);
        TextView title = page.findViewById(R.id.tv_title);
        String url = pageData.get(position).getUrl().trim();
        title.setText(pageData.get(position).getTitleStr().trim());
        PicLoader.with(container.getContext())
                .load(url)
                .into(target);
        container.addView(page);
        page.setOnClickListener(v -> {
            if (pageClickListener != null) {
                pageClickListener.onClick(pageData.get(position));
            }
        });
        return page;
    }

    public void setOnPageClickListener(PageItemClickListener listener) {
        this.pageClickListener = listener;
    }
}
