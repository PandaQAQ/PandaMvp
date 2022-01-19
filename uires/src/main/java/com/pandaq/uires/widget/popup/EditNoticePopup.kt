package com.pandaq.uires.widget.popup

import android.content.Context
import android.widget.TextView
import com.lxj.xpopup.core.PositionPopupView
import com.pandaq.uires.R

/**
 * Created by huxinyu on 6/11/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
class EditNoticePopup(context: Context, private val confirmListener: OnClickListener) :
    PositionPopupView(context) {

    private lateinit var exit: TextView
    private lateinit var cancel: TextView

    override fun initPopupContent() {
        super.initPopupContent()
        exit = findViewById(R.id.tv_exit)
        cancel = findViewById(R.id.tv_cancel)

        cancel.setOnClickListener {
            dismiss()
        }

        exit.setOnClickListener {
            dismiss()
            confirmListener.onClick(it)
        }
    }

    override fun getImplLayoutId(): Int {
        return R.layout.popup_exit_notice
    }

}