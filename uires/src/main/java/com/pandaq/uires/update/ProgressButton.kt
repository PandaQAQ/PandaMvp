package com.pandaq.uires.update

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

/**
 * Created by huxinyu on 4/27/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
class ProgressButton @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {

    private var progressListener: ProgressListener? = null

    private val progressPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 进度
    private var progress: Int = 0

    // 是否在进度条模式
    private var inProgressMode = false

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val shader = LinearGradient(0f, 0f, width * 1.0f, 0f, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP)
        progressPaint.shader = shader
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (inProgressMode) {
            val fraction = progress / 100f
            canvas.drawRoundRect(0f, 0f, width * fraction, height * 1.0f, 20f, 20f, progressPaint)
        }
    }

    private fun showProgress(inProgress: Boolean) {
        inProgressMode = inProgress
        isEnabled = !inProgressMode
        invalidate()
    }

    fun updateProgress(progress: Int) {
        this.progress = progress
        when (progress) {
            0 -> {
                progressListener?.onStart()
                showProgress(true)
            }
            in 1..99 -> {
                showProgress(true)
                progressListener?.onProgress(progress)
            }
            else -> {
                showProgress(false)
                text = "下载完成"
                progressListener?.onFinish()
            }
        }
    }

    fun setProgressListener(listener: ProgressListener) {
        this.progressListener = listener
    }


    interface ProgressListener {
        fun onStart()
        fun onProgress(progress: Int)
        fun onFinish()
    }

}