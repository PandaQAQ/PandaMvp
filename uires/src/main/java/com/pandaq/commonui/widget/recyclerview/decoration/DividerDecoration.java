package com.pandaq.commonui.widget.recyclerview.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by huxinyu on 2019/3/26.
 * Email : panda.h@foxmail.com
 * Description :recyclerview 的 item 分割线
 */
public class DividerDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private int spanCount;

    /**
     * LinearLayoutManager item 分割线
     *
     * @param space 分隔线宽度
     */
    public DividerDecoration(int space) {
        this(space, 1);
    }

    /**
     * item之间的间距
     *
     * @param space item 各个方向的间距
     */
    public DividerDecoration(int space, int spanCount) {
        this.spanCount = spanCount;
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int leftSpace;
        int rightSpace;
        int topSpace;
        // 非单列才有左右布局
        if (spanCount > 1) {
            leftSpace = space / 2;
            rightSpace = space / 2;
        } else {
            leftSpace = 0;
            rightSpace = 0;
        }
        if (parent.getChildLayoutPosition(view) < spanCount) {
            topSpace = 0;
        } else {
            topSpace = space;
        }
        outRect.top = topSpace;
        outRect.left = leftSpace;
        outRect.right = rightSpace;
    }
}
