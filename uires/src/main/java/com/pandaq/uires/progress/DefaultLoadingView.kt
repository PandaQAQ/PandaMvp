package com.pandaq.uires.progress

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.pandaq.uires.R

/**
 * Created by huxinyu on 2019/8/8.
 * Email : panda.h@foxmail.com
 * Description :
 */
class DefaultLoadingView : FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    init {
        View.inflate(context, R.layout.default_loading_view, null)
    }

}