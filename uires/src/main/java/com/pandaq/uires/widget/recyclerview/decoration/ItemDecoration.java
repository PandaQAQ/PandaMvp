package com.pandaq.uires.widget.recyclerview.decoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * Created by huxinyu on 2018/5/18.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : RecyclerView item 间隔线
 */
public class ItemDecoration extends RecyclerView.ItemDecoration {

    private int halfSpace;
    private int spanCount;
    private Paint mPaint;
    private boolean showBottom;
    private boolean showTop;
    private int color;
    private int startOffset;
    private int endOffset;
    private int ignoreCount;
    // 使用分割线给 item 第一列和最后一列加边距，达到 header 占满，item 有边距的效果
    private int paddingStart;
    private int paddingEnd;

    /**
     * item之间的间距
     *
     * @param space item 各个方向的间距
     */
    private ItemDecoration(int space, int spanCount,
                           int color, boolean showBottom,
                           boolean showTop, int startOffset,
                           int endOffset, int ignoreCount,
                           int paddingStart, int paddingEnd) {
        this.spanCount = spanCount;
        if (space / 2f < 1f) {
            this.halfSpace = 1;
        } else {
            this.halfSpace = space / 2;
        }
        this.ignoreCount = ignoreCount;
        this.showBottom = showBottom;
        this.showTop = showTop;
        this.color = color;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.paddingStart = paddingStart;
        this.paddingEnd = paddingEnd;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(space);
        mPaint.setColor(color);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        // adapter position 位置
        int adapterPosition = parent.getChildAdapterPosition(view);
        if (adapterPosition < ignoreCount) return; //小于忽略 count 的 item 不做处理
        int leftSpace = 0; // 左边空出距离
        int topSpace = 0; // 上边空出距离
        int rightSpace = 0; // 右边空出距离
        int bottomSpace = 0; // 下边空出距离
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) view.getLayoutParams();
            int spanIndex = params.getSpanIndex();
            if (spanIndex == 0) {
                rightSpace = halfSpace;
                leftSpace = paddingStart;
            } else if (spanIndex == spanCount - 1) {
                leftSpace = halfSpace;
                rightSpace = paddingEnd;
            } else {
                leftSpace = halfSpace;
                rightSpace = halfSpace;
            }
            topSpace = halfSpace;
            bottomSpace = halfSpace;
        } else if (parent.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            int spanIndex = params.getSpanIndex();
            if (spanIndex == 0) {
                rightSpace = halfSpace;
                leftSpace = paddingStart;
            } else if (spanIndex == spanCount - 1) {
                leftSpace = halfSpace;
                rightSpace = paddingEnd;
            } else {
                leftSpace = halfSpace;
                rightSpace = halfSpace;
            }
            topSpace = halfSpace;
            bottomSpace = halfSpace;
        } else if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            topSpace = halfSpace;
            bottomSpace = halfSpace;
            leftSpace = paddingStart;
            rightSpace = paddingEnd;
        }
        // 设置间距
        outRect.top = topSpace;
        outRect.left = leftSpace;
        outRect.right = rightSpace;
        outRect.bottom = bottomSpace;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (parent.getAdapter() == null) return;
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            if (parent.getLayoutManager() instanceof GridLayoutManager) {
                GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                int spanIndex = params.getSpanIndex();
                drawHorizontal(spanIndex, view, c);
                drawVertical(spanIndex, view, c);
            } else if (parent.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
                int spanIndex = params.getSpanIndex();
                drawHorizontal(spanIndex, view, c);
                drawVertical(spanIndex, view, c);
            } else if (parent.getLayoutManager() instanceof LinearLayoutManager) {
                drawLinearLayout(view, c);
            }
        }
    }

    /**
     * LinearLayoutManager 分割线绘制
     */
    private void drawLinearLayout(View view, Canvas c) {
        // 上分割线
        c.drawRect(view.getLeft() + startOffset, view.getTop() - halfSpace, view.getRight() - endOffset, view.getTop(), mPaint);
        // 下分割线
        c.drawRect(view.getLeft() + startOffset, view.getBottom(), view.getRight() - endOffset, view.getBottom() + halfSpace, mPaint);
    }

    /**
     * 绘制横向分割线
     */
    private void drawHorizontal(int spanIndex, View view, Canvas c) {
        // 第一列绘制跟  itemView 一样宽，右边其他向左填充一个分割线宽
        if (spanIndex == 0) { //第一列
            c.drawRect(view.getLeft() + startOffset, view.getTop() - halfSpace, view.getRight() + halfSpace, view.getTop(), mPaint);
            c.drawRect(view.getLeft() + startOffset, view.getBottom(), view.getRight() + halfSpace, view.getBottom() + halfSpace, mPaint);
        } else if (spanIndex == spanCount - 1) { // 最后一列
            c.drawRect(view.getLeft() - halfSpace, view.getTop() - halfSpace, view.getRight() - endOffset, view.getTop(), mPaint);
            c.drawRect(view.getLeft() - halfSpace, view.getBottom(), view.getRight() - endOffset, view.getBottom() + halfSpace, mPaint);
        } else {
            c.drawRect(view.getLeft() - halfSpace, view.getTop() - halfSpace, view.getRight() + halfSpace, view.getTop(), mPaint);
            c.drawRect(view.getLeft() - halfSpace, view.getBottom(), view.getRight() + halfSpace, view.getBottom() + halfSpace, mPaint);
        }
    }

    /**
     * 绘制纵向分割线
     */
    private void drawVertical(int spanIndex, View view, Canvas c) {
        if (spanIndex == 0) { //第一列
            c.drawRect(view.getRight(), view.getTop(), view.getRight() + halfSpace, view.getBottom(), mPaint);
        } else if (spanIndex == spanCount - 1) { //最后一列
            c.drawRect(view.getLeft() - halfSpace, view.getTop(), view.getLeft(), view.getBottom(), mPaint);
        } else { // 中间
            c.drawRect(view.getRight(), view.getTop(), view.getRight() + halfSpace, view.getBottom(), mPaint);
            c.drawRect(view.getLeft() - halfSpace, view.getTop(), view.getLeft(), view.getBottom(), mPaint);
        }
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.color = color;
        builder.showBottom = showBottom;
        builder.showTop = showTop;
        builder.space = 2 * halfSpace;
        builder.spanCount = spanCount;
        builder.ignoreCount = ignoreCount;
        return builder;
    }

    public static class Builder {

        private int space = 2;
        @ColorInt
        private int color = Color.GRAY;
        private int spanCount = 1;
        private boolean showTop;
        private boolean showBottom;
        private int startOffset = 0;
        private int endOffset = 0;
        private int ignoreCount = 0;

        private int paddingStart = 0;
        private int paddingEnd = 0;


        public Builder space(int space) {
            this.space = space;
            return this;
        }


        public Builder color(int color) {
            this.color = color;
            return this;
        }


        public Builder spanCount(int spanCount) {
            this.spanCount = spanCount;
            return this;
        }


        public Builder showTop(boolean showTop) {
            this.showTop = showTop;
            return this;
        }


        public Builder showBottom(boolean showBottom) {
            this.showBottom = showBottom;
            return this;
        }

        public Builder startOffset(int startOffset) {
            this.startOffset = startOffset;
            return this;
        }

        public Builder endOffset(int endOffset) {
            this.endOffset = endOffset;
            return this;
        }

        public Builder ignoreCount(int ignoreCount) {
            this.ignoreCount = ignoreCount;
            return this;
        }

        public Builder startPadding(int paddingStart) {
            this.paddingStart = paddingStart;
            return this;
        }

        public Builder endPadding(int paddingEnd) {
            this.paddingEnd = paddingEnd;
            return this;
        }

        public ItemDecoration build() {
            return new ItemDecoration(space, spanCount, color, showBottom, showTop, startOffset, endOffset, ignoreCount, paddingStart, paddingEnd);
        }
    }

}
