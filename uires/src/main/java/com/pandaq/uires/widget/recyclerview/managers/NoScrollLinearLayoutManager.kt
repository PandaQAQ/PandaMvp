package com.pandaq.uires.widget.recyclerview.managers

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * Created by huxinyu on 2019/7/24.
 * Email : panda.h@foxmail.com
 * Description :可禁止滚动的 layoutManager
 */
class NoScrollLinearLayoutManager : LinearLayoutManager {

    constructor(context: Context) : super(context)

    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(context, orientation, reverseLayout)


    private var canScrollVertically = false
    private var canScrollHorizontally = false

    override fun canScrollVertically(): Boolean {
        return canScrollVertically && super.canScrollVertically()
    }

    override fun canScrollHorizontally(): Boolean {
        return canScrollHorizontally && super.canScrollHorizontally()
    }
}