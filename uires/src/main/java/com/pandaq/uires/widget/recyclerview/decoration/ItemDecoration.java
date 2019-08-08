package com.pandaq.uires.widget.recyclerview.decoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

    /**
     * item之间的间距
     *
     * @param space item 各个方向的间距
     */
    private ItemDecoration(int space, int spanCount,
                           int color, boolean showBottom,
                           boolean showTop, int startOffset,
                           int endOffset) {
        this.spanCount = spanCount;
        if (space / 2 < 1f) {
            this.halfSpace = 1;
        } else {
            this.halfSpace = space / 2;
        }
        this.showBottom = showBottom;
        this.showTop = showTop;
        this.color = color;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(space);
        mPaint.setColor(color);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int leftSpace = 0;
        int topSpace = 0;
        int rightSpace = 0;
        int bottomSpace = 0;
        if (parent.getChildLayoutPosition(view) % spanCount == 0) {
            rightSpace = halfSpace;
        } else if (parent.getChildLayoutPosition(view) % spanCount == spanCount - 1) {
            leftSpace = halfSpace;
        } else {
            leftSpace = halfSpace;
            rightSpace = halfSpace;
        }
        if (showTop) {
            if (parent.getChildLayoutPosition(view) < spanCount) {
                topSpace = 2 * halfSpace;
            }
        }
        bottomSpace = 2 * halfSpace;
        outRect.top = topSpace;
        outRect.left = leftSpace;
        outRect.right = rightSpace;
        outRect.bottom = bottomSpace;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            drawHorizontal(childCount, i, view, c);
            drawVertical(i, view, c);
        }
    }


    /**
     * 绘制横向分割线
     */
    private void drawHorizontal(int childCount, int index, View view, Canvas c) {
        if (spanCount == 1) {
            if (showTop) {
                if (index == 0) {
                    c.drawRect(view.getLeft() + startOffset, view.getTop() - 2 * halfSpace, view.getRight() - endOffset, view.getTop(), mPaint);
                }
            }
            if (showBottom) {
                c.drawRect(view.getLeft() + startOffset, view.getBottom(), view.getRight() - endOffset, view.getBottom() + 2 * halfSpace, mPaint);
            } else {
                if (index < childCount - spanCount) {
                    c.drawRect(view.getLeft() + startOffset, view.getBottom(), view.getRight() - endOffset, view.getBottom() + 2 * halfSpace, mPaint);
                }
            }
            return;
        }
        // 第一列绘制跟  itemView 一样宽，右边其他向左填充一个分割线宽
        if (index % spanCount == 0) { //第一列
            if (showTop) {
                if (index < spanCount) {
                    c.drawRect(view.getLeft() + startOffset, view.getTop() - 2 * halfSpace, view.getRight(), view.getTop(), mPaint);
                }
            }
            if (showBottom) {
                c.drawRect(view.getLeft() + startOffset, view.getBottom(), view.getRight(), view.getBottom() + 2 * halfSpace, mPaint);
            } else {
                if (index < childCount - spanCount) {
                    c.drawRect(view.getLeft() + startOffset, view.getBottom(), view.getRight(), view.getBottom() + 2 * halfSpace, mPaint);
                }
            }
        } else if (index % spanCount == spanCount - 1) {
            if (showTop) {
                if (index < spanCount) {
                    c.drawRect(view.getLeft() - 2 * halfSpace, view.getTop() - 2 * halfSpace, view.getRight() - endOffset, view.getTop(), mPaint);
                }
            }
            if (showBottom) {
                c.drawRect(view.getLeft() - 2 * halfSpace, view.getBottom(), view.getRight() - endOffset, view.getBottom() + 2 * halfSpace, mPaint);
            } else {
                if (index < childCount - spanCount) {
                    c.drawRect(view.getLeft() - 2 * halfSpace, view.getBottom(), view.getRight() - endOffset, view.getBottom() + 2 * halfSpace, mPaint);
                }
            }
        } else {
            if (showTop) {
                if (index < spanCount) {
                    c.drawRect(view.getLeft() - 2 * halfSpace, view.getTop() - 2 * halfSpace, view.getRight(), view.getTop(), mPaint);
                }
            }
            if (showBottom) {
                c.drawRect(view.getLeft() - 2 * halfSpace, view.getBottom(), view.getRight(), view.getBottom() + 2 * halfSpace, mPaint);
            } else {
                if (index < childCount - spanCount) {
                    c.drawRect(view.getLeft() - 2 * halfSpace, view.getBottom(), view.getRight(), view.getBottom() + 2 * halfSpace, mPaint);
                }
            }
        }

    }

    private void drawVertical(int index, View view, Canvas c) {
        if (index % spanCount == 0) { //第一列
            c.drawRect(view.getRight(), view.getTop(), view.getRight() + halfSpace, view.getBottom(), mPaint);
        } else if (index % spanCount == spanCount - 1) { //最后一列
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

        public ItemDecoration build() {
            return new ItemDecoration(space, spanCount, color, showBottom, showTop, startOffset, endOffset);
        }
    }

}
