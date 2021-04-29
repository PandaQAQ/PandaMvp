package com.pandaq.uires.widget

import android.content.Context
import android.util.AttributeSet
import androidx.core.widget.NestedScrollView
import com.pandaq.uires.R

/**
 * Created by huxinyu on 4/27/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
class MaxHeightScrollView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr) {

    private var maxHeight = 0

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.MaxHeightScrollView)
        maxHeight = typeArray.getLayoutDimension(R.styleable.MaxHeightScrollView_maxHeight, 0)
        typeArray.recycle()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (maxHeight > 0) {
            val newSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST)
            super.onMeasure(widthMeasureSpec, newSpec)
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

}