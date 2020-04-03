package com.pandaq.uires.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.pandaq.uires.R

/**
 * Created by huxinyu on 2020/4/3.
 * Email : panda.h@foxmail.com
 * Description :指定宽高比的 FrameLayout 布局
 */
class RationFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var ratio: Float = 0f

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.RationFrameLayout)
        ratio = ta.getFloat(R.styleable.RationFrameLayout_ratio, 0f)
        ta.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (ratio > 0) {
            val height = MeasureSpec.makeMeasureSpec(
                (MeasureSpec.getSize(widthMeasureSpec) / ratio).toInt(),
                MeasureSpec.EXACTLY
            )
            super.onMeasure(widthMeasureSpec, height)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}