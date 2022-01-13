package com.pandaq.uires.widget.marqueeview

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

/**
 * Created by huxinyu on 6/15/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
class LoopLayoutManager(context: Context) : LinearLayoutManager(context) {

    init {
        orientation = HORIZONTAL
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun canScrollHorizontally(): Boolean {
        return true
    }

    override fun canScrollVertically(): Boolean {
        return false
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        if (itemCount <= 0) {
            return
        }
        if (state.isPreLayout) {
            return
        }
        detachAndScrapAttachedViews(recycler)

        var realWidth = 0
        for (i in 0..itemCount) {
            val itemView = recycler.getViewForPosition(i)
            addView(itemView)
            measureChildWithMargins(itemView, 0, 0)
            val width = getDecoratedMeasuredWidth(itemView)
            val height = getDecoratedMeasuredHeight(itemView)
            layoutDecorated(itemView, realWidth, 0, realWidth + width, height)
            realWidth += width
            if (realWidth > getWidth()) {
                break
            }
        }
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State
    ): Int {
        val travl = fill(dx, recycler, state)
        if (travl == 0) return 0

        offsetChildrenHorizontal(-travl)

        recyclerHideView(dx, recycler)
        return travl
    }

    /**
     * 左右滑动的时候，填充
     */
    private fun fill(dx: Int, recycler: Recycler, state: RecyclerView.State): Int {
        var xDistance = dx
        if (xDistance > 0) {
            //标注1.向左滚动
            val lastView = getChildAt(childCount - 1) ?: return 0
            val lastPos = getPosition(lastView)
            //标注2.可见的最后一个itemView完全滑进来了，需要补充新的
            if (lastView.right < width) {
                var scrap: View? = null
                //标注3.判断可见的最后一个itemView的索引，
                // 如果是最后一个，则将下一个itemView设置为第一个，否则设置为当前索引的下一个
                scrap = if (lastPos == itemCount - 1) {
                    recycler.getViewForPosition(0)
                } else {
                    recycler.getViewForPosition(lastPos + 1)
                }
                //标注4.将新的itemViewadd进来并对其测量和布局
                addView(scrap)
                measureChildWithMargins(scrap, 0, 0)
                val width = getDecoratedMeasuredWidth(scrap)
                val height = getDecoratedMeasuredHeight(scrap)
                layoutDecorated(
                    scrap, lastView.right, 0,
                    lastView.right + width, height
                )
                return xDistance
            }
        } else {
            //向右滚动
            val firstView = getChildAt(0) ?: return 0
            val firstPos = getPosition(firstView)
            if (firstView.left >= 0) {
                val scrap: View = if (firstPos == 0) {
                    recycler.getViewForPosition(itemCount - 1)
                } else {
                    recycler.getViewForPosition(firstPos - 1)
                }
                addView(scrap, 0)
                measureChildWithMargins(scrap, 0, 0)
                val width = getDecoratedMeasuredWidth(scrap)
                val height = getDecoratedMeasuredHeight(scrap)
                layoutDecorated(
                    scrap, firstView.left - width, 0,
                    firstView.left, height
                )
            }
        }
        return xDistance
    }

    private fun recyclerHideView(dx: Int, recycler: Recycler) {
        for (i in 0 until childCount) {
            val view = getChildAt(i) ?: continue
            if (dx > 0) {
                //向左滚动，移除一个左边不在内容里的view
                if (view.right < 0) {
                    removeAndRecycleView(view, recycler)
                }
            } else {
                //向右滚动，移除一个右边不在内容里的view
                if (view.left > width) {
                    removeAndRecycleView(view, recycler)
                }
            }
        }
    }
}