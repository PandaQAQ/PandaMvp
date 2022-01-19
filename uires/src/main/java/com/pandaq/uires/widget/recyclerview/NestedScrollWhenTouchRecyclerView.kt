package com.pandaq.uires.widget.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import com.pandaq.appcore.log.PLogger

/**
 * Created by huxinyu on 2021/9/3.
 * Email : panda.h@foxmail.com
 * Description :
 */
class NestedScrollWhenTouchRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

    private var listener: TouchDownListener? = null

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                isNestedScrollingEnabled = true
                listener?.touchDown(this)
                PLogger.d("NestedScrollWhenTouchRecyclerView", "$isNestedScrollingEnabled")
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    fun setTouchDownListener(touchDownListener: TouchDownListener) {
        this.listener = touchDownListener
    }

    interface TouchDownListener {
        fun touchDown(view: NestedScrollWhenTouchRecyclerView)
    }
}