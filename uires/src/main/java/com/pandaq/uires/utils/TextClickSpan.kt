package com.pandaq.uires.utils

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import com.pandaq.appcore.utils.system.AppUtils
import com.pandaq.uires.R

/**
 * Created by huxinyu on 2019/8/22.
 * Email : panda.h@foxmail.com
 * Description :
 */
/**
 * Created by huxinyu on 2019/3/12.
 * Email : panda.h@foxmail.com
 * Description :TextView 部分文本响应点击事件
 */
open class TextClickSpan(click: SpanClickListener, spanStr: String = "") : ClickableSpan() {

    private var mHighLightColor = AppUtils.getContext().resources.getColor(R.color.res_colorTextMinor)

    private var mClickListener: SpanClickListener? = click
    private var mSpanStr: String = spanStr


    override fun onClick(widget: View?) {
        mClickListener?.onClick(mSpanStr, widget)
    }

    override fun updateDrawState(ds: TextPaint) {
        ds.color = mHighLightColor
    }

}