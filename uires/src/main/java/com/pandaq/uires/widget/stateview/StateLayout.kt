package com.pandaq.uires.widget.stateview

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pandaq.appcore.imageloader.glide.BlurTransformation
import com.pandaq.uires.R
import pl.droidsonroids.gif.GifImageView

/**
 * Created by huxinyu on 5/7/21.
 * Email : panda.h@foxmail.com
 * Description :网络加载状态 View
 */
class StateLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var mOnDefaultStateClickListener: DefaultStateClickListener? = null
    private var mShowState: LoadState = LoadState.LOADING

    private var contentView: View? = null
    private var emptyView: View
    private var loadingView: View
    private var errorView: View
    private var netErrorView: View

    private var defLoadingView: GifImageView? = null

    private var defEmptyImage: ImageView? = null
    private var defEmptyText: TextView? = null

    private var defErrorImage: ImageView? = null
    private var defErrorText: TextView? = null

    private var defNetErrorImage: ImageView? = null
    private var defNetErrorText: TextView? = null

    private var defEmptyString: String? = null
    private var defErrorString: String? = null
    private var defNetErrorString: String? = null

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.StateLayout)
        // emptyView
        val emptyId = typeArray.getResourceId(
            R.styleable.StateLayout_emptyLayout,
            R.layout.default_empty_layout
        )
        emptyView = View.inflate(context, emptyId, null)
        defEmptyText = emptyView.findViewById(R.id.state_empty_hint)
        defEmptyImage = emptyView.findViewById(R.id.state_empty_img)
        defEmptyString = defEmptyText?.text.toString()
        emptyView.visibility = View.GONE
        emptyView.setOnClickListener {
            mOnDefaultStateClickListener?.onEmptyClick()
        }
        // errorView
        val errorId = typeArray.getResourceId(
            R.styleable.StateLayout_errorLayout,
            R.layout.default_error_layout
        )
        errorView = View.inflate(context, errorId, null)
        defErrorText = errorView.findViewById(R.id.state_error_hint)
        defErrorImage = errorView.findViewById(R.id.state_error_img)
        val errorRefresh = errorView.findViewById<TextView>(R.id.state_error_refresh)
        defErrorString = defErrorText?.text.toString()
        errorView.visibility = View.GONE
        errorRefresh?.setOnClickListener {
            mOnDefaultStateClickListener?.onErrorClick()
        }
        // netErrorView
        val netErrorId = typeArray.getResourceId(
            R.styleable.StateLayout_noNetLayout,
            R.layout.default_neterror_layout
        )
        netErrorView = View.inflate(context, netErrorId, null)
        defNetErrorText = netErrorView.findViewById(R.id.state_net_error_hint)
        defNetErrorImage = netErrorView.findViewById(R.id.state_net_error_img)
        val noNetRefresh = errorView.findViewById<TextView>(R.id.state_net_error_refresh)
        defNetErrorString = defNetErrorText?.text.toString()
        netErrorView.visibility = View.GONE
        noNetRefresh?.setOnClickListener {
            mOnDefaultStateClickListener?.onNetErrorClick()
        }
        // loadingView
        val loadingId = typeArray.getResourceId(
            R.styleable.StateLayout_loadingLayout,
            R.layout.default_loading_layout
        )
        val useBlurLoading = typeArray.getBoolean(R.styleable.StateLayout_useBlurLoading, false)
        if (useBlurLoading) {
            val drawableView = inflate(context, loadingId, null)
            drawableView.measure(
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            )
            drawableView.layoutParams =
                LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            drawableView.layout(0, 0, drawableView.measuredWidth, drawableView.measuredHeight)
            drawableView.isDrawingCacheEnabled = true
            drawableView.buildDrawingCache(true)
            val loadingBitmap = Bitmap.createBitmap(drawableView.getDrawingCache(true))
            drawableView.isDrawingCacheEnabled = false
            drawableView.destroyDrawingCache()
            loadingView = AppCompatImageView(context)
            (loadingView as AppCompatImageView).let {
                it.scaleType = ImageView.ScaleType.FIT_XY
                Glide.with(context)
                    .load(loadingBitmap)
                    .apply(RequestOptions.bitmapTransform(BlurTransformation(context)))
                    .into(loadingView as AppCompatImageView)
            }
        } else {
            loadingView = inflate(context, loadingId, null)
            defLoadingView = loadingView.findViewById(R.id.state_loading_img)
        }
        loadingView.visibility = View.GONE
        typeArray.recycle()
    }

    fun setOnStateClickListener(listener: DefaultStateClickListener) {
        this.mOnDefaultStateClickListener = listener
    }

    fun getState(): LoadState {
        return mShowState
    }

    fun getEmptyView(): View {
        return emptyView
    }

    fun getErrorView(): View {
        return errorView
    }

    fun showContent() {
        this.mShowState = LoadState.NORMAL
        showWithState()
    }

    fun showError(errMsg: String? = null) {
        if (errMsg == null) {
            defErrorText?.text = this.defErrorString
        } else {
            defErrorText?.text = errMsg
        }
        this.mShowState = LoadState.ERROR
        showWithState()
    }

    fun showNetError(errMsg: String? = null) {
        if (errMsg == null) {
            defNetErrorText?.text = this.defNetErrorString
        } else {
            defErrorText?.text = errMsg
        }
        this.mShowState = LoadState.NO_NET
        showWithState()
    }

    fun showLoading() {
        this.mShowState = LoadState.LOADING
        showWithState()
    }

    fun showEmpty(errMsg: String? = null) {
        if (errMsg == null) {
            defEmptyText?.text = this.defEmptyString
        } else {
            defEmptyText?.text = errMsg
        }
        this.mShowState = LoadState.EMPTY
        showWithState()
    }

    fun setDefNetErrorText(msg: String) {
        this.defNetErrorString = msg
        defNetErrorText?.text = this.defNetErrorString
    }

    fun setDefEmptyText(msg: String) {
        this.defEmptyString = msg
        defEmptyText?.text = this.defEmptyString
    }

    fun setDefErrorText(msg: String) {
        this.defErrorString = msg
        defErrorText?.text = this.defErrorString
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        super.addView(child, index, params)
        child?.let {
            if (it != emptyView && it != errorView && it != netErrorView && it != loadingView) {
                contentView = child
                val param = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                addView(emptyView, param)
                addView(errorView, param)
                addView(netErrorView, param)
                addView(loadingView, param)
                showWithState()
            }
        }
    }

    /**
     * 根据加载状态显示内容
     */
    private fun showWithState() {
        when (mShowState) {
            LoadState.EMPTY -> {
                emptyView.visibility = View.VISIBLE
                errorView.visibility = View.GONE
                contentView?.visibility = View.GONE
                loadingView.visibility = View.GONE
                netErrorView.visibility = View.GONE
            }
            LoadState.LOADING -> {
                emptyView.visibility = View.GONE
                errorView.visibility = View.GONE
                contentView?.visibility = View.GONE
                loadingView.visibility = View.VISIBLE
                netErrorView.visibility = View.GONE
            }
            LoadState.ERROR -> {
                emptyView.visibility = View.GONE
                errorView.visibility = View.VISIBLE
                contentView?.visibility = View.GONE
                loadingView.visibility = View.GONE
                netErrorView.visibility = View.GONE
            }
            LoadState.NO_NET -> {
                emptyView.visibility = View.GONE
                errorView.visibility = View.GONE
                contentView?.visibility = View.GONE
                loadingView.visibility = View.GONE
                netErrorView.visibility = View.VISIBLE
            }
            else -> {
                emptyView.visibility = View.GONE
                errorView.visibility = View.GONE
                contentView?.visibility = View.VISIBLE
                loadingView.visibility = View.GONE
                netErrorView.visibility = View.GONE
            }
        }
    }

}