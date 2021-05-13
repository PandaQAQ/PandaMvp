package com.pandaq.uires.update

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import com.lxj.xpopup.core.CenterPopupView
import com.pandaq.appcore.utils.system.DisplayUtils
import com.pandaq.uires.R
import com.pandaq.uires.databinding.PopupUpdateBinding

/**
 * Created by huxinyu on 4/27/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
@SuppressLint("ViewConstructor")
class UpdatePopup(context: Activity, url: String) : CenterPopupView(context) {

    private val apkUrl: String = url

    override fun getImplLayoutId(): Int {
        return R.layout.popup_update
    }

    override fun offsetLeftAndRight(offset: Int) {
        super.offsetLeftAndRight(DisplayUtils.dp2px(32f))
    }

    override fun initPopupContent() {
        super.initPopupContent()
        val binding = PopupUpdateBinding.inflate(LayoutInflater.from(context))
        binding.btnDownload.setOnClickListener {
//            RxPanda.download(url)
//                    .
        }
    }

}