package com.pandaq.uires.progress

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pandaq.uires.R


/**
 * Created by huxinyu on 2018/5/21.
 * Email : panda.h@foxmail.com
 *
 * Description :
 */

class LoadingDialog(mContext: Context) : Dialog(mContext, R.style.loading_dialog) {

    /**
     * loadingView
     */
    private var loadingView: View? = null

    private val rootView by lazy {
        LayoutInflater.from(context).inflate(R.layout.dialog_loading, null).findViewById<ViewGroup>(R.id.fl_container)
    }

    /**
     * 弹出Dialog
     *
     * @param message    文本信息
     * @param cancelable 是可以点击返回键
     * @return Dialog
     */
    fun show(cancelable: Boolean): Dialog {
        if (loadingView == null) {
            loadingView = DefaultLoadingView(context)
        }
        loadingView?.let {
            setLoadingView(it)
        }
        setCancelable(cancelable)
        setCanceledOnTouchOutside(cancelable)
        setContentView(rootView)
        show()
        return this
    }

    /**
     * 设置自定义的加载 View
     */
    fun setLoadingView(view: View) {
        rootView.removeAllViews()
        rootView.addView(view)
    }
}
