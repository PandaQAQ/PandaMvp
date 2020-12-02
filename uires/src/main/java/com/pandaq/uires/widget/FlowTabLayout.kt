package com.pandaq.uires.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * Created by huxinyu on 2020/11/30.
 * Email : panda.h@foxmail.com
 * Description :
 */
class FlowTabLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 先计算子 children 尺寸
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }
}