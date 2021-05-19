package com.pandaq.uires.widget.recyclerview.loadfooter

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.pandaq.uires.R
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshKernel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import pl.droidsonroids.gif.GifDrawable

/**
 * Created by huxinyu on 5/19/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
class PandaHeader @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), RefreshHeader {

    private var gifDrawable: GifDrawable
    private var headerLayout:LinearLayout

    init {
        View.inflate(context,R.layout.res_panda_refresh_header,this)
        val image = findViewById<ImageView>(R.id.res_iv_refresh)
        headerLayout = findViewById(R.id.ll_refresh_header)
        gifDrawable = GifDrawable(resources, R.drawable.res_refresh_header)
        image.setImageDrawable(gifDrawable)
    }

    override fun onStateChanged(
        refreshLayout: RefreshLayout,
        oldState: RefreshState,
        newState: RefreshState
    ) {

    }

    override fun getView(): View {
        return this
    }

    override fun getSpinnerStyle(): SpinnerStyle {
        return SpinnerStyle.Translate
    }

    override fun setPrimaryColors(vararg colors: Int) {

    }

    override fun onInitialized(kernel: RefreshKernel, height: Int, maxDragHeight: Int) {

    }

    override fun onMoving(
        isDragging: Boolean,
        percent: Float,
        offset: Int,
        height: Int,
        maxDragHeight: Int
    ) {

    }

    override fun onReleased(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {
        gifDrawable.start()
    }

    override fun onStartAnimator(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {

    }

    override fun onFinish(refreshLayout: RefreshLayout, success: Boolean): Int {
        gifDrawable.stop()
        return 500
    }

    override fun onHorizontalDrag(percentX: Float, offsetX: Int, offsetMax: Int) {

    }

    override fun isSupportHorizontalDrag(): Boolean {
        return false
    }

    fun setHeaderBackgroundColor(@ColorInt color:Int){
        headerLayout.setBackgroundColor(color)
    }

    fun setHeaderBackgroundRes(@ColorRes res:Int){
        headerLayout.setBackgroundResource(res)
    }
}