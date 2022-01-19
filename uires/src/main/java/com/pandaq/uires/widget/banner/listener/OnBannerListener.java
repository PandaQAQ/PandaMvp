package com.pandaq.uires.widget.banner.listener;

public interface OnBannerListener<T> {

    /**
     * 点击事件
     *
     * @param data     数据实体
     * @param position 当前位置
     */
    void onBannerClick(T data, int position);

}
