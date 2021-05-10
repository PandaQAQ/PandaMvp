package com.pandaq.uires.stateview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
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

    private var defEmptyImage: ImageView? = null
    private var defEmptyText: TextView? = null
    private var defErrorImage: ImageView? = null
    private var defErrorText: TextView? = null
    private var defNetErrorImage: ImageView? = null
    private var defNetErrorText: TextView? = null
    private var defLoadingView: GifImageView? = null

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.StateLayout)
        // emptyView
        val emptyId = typeArray.getResourceId(R.styleable.StateLayout_emptyLayout, R.layout.default_empty_layout)
        emptyView = View.inflate(context, emptyId, null)
        defEmptyText = emptyView.findViewById(R.id.state_empty_hint)
        defEmptyImage = emptyView.findViewById(R.id.state_empty_img)
        emptyView.visibility = View.GONE
        emptyView.setOnClickListener {
            mOnDefaultStateClickListener?.onEmptyClick()
        }
        // errorView
        val errorId = typeArray.getResourceId(R.styleable.StateLayout_errorLayout, R.layout.default_error_layout)
        errorView = View.inflate(context, errorId, null)
        defErrorText = errorView.findViewById(R.id.state_error_hint)
        defErrorImage = errorView.findViewById(R.id.state_error_img)
        errorView.visibility = View.GONE
        errorView.setOnClickListener {
            mOnDefaultStateClickListener?.onErrorClick()
        }
        // netErrorView
        val netErrorId = typeArray.getResourceId(R.styleable.StateLayout_noNetLayout, R.layout.default_neterror_layout)
        netErrorView = View.inflate(context, netErrorId, null)
        defNetErrorText = netErrorView.findViewById(R.id.state_neterror_hint)
        defNetErrorImage = netErrorView.findViewById(R.id.state_neterror_img)
        netErrorView.visibility = View.GONE
        netErrorView.setOnClickListener {
            mOnDefaultStateClickListener?.onNetErrorClick()
        }
        // loadingView
        val loadingId = typeArray.getResourceId(R.styleable.StateLayout_noNetLayout, R.layout.default_loading_layout)
        loadingView = inflate(context, loadingId, null)
        defLoadingView = loadingView.findViewById(R.id.state_loading_img)
        loadingView.visibility = View.GONE
        loadingView.setOnClickListener {
            mOnDefaultStateClickListener?.onLoadingClick()
        }
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

    fun showError() {
        this.mShowState = LoadState.ERROR
        showWithState()
    }

    fun showNetError() {
        this.mShowState = LoadState.NO_NET
        showWithState()
    }

    fun showLoading() {
        this.mShowState = LoadState.LOADING
        showWithState()
    }

    fun showEmpty() {
        this.mShowState = LoadState.EMPTY
        showWithState()
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        super.addView(child, index, params)
        child?.let {
            if (it != emptyView && it != errorView && it != netErrorView && it != loadingView) {
                contentView = child
                val param = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
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