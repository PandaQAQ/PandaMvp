package com.pandaq.uires.update

import android.annotation.SuppressLint
import android.app.Activity
import com.lxj.xpopup.core.CenterPopupView
import com.pandaq.appcore.utils.system.DisplayUtils
import com.pandaq.rxpanda.RxPanda
import com.pandaq.uires.R
import kotlinx.android.synthetic.main.popup_update.view.*

/**
 * Created by huxinyu on 4/27/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
@SuppressLint("ViewConstructor")
class UpdatePopup(context: Activity,  url:String) : CenterPopupView(context) {

    private val apkUrl:String = url

    override fun getImplLayoutId(): Int {
        return R.layout.popup_update
    }

    override fun offsetLeftAndRight(offset: Int) {
        super.offsetLeftAndRight(DisplayUtils.dp2px(32f))
    }

    override fun initPopupContent() {
        super.initPopupContent()
        btn_download.setOnClickListener {
//            RxPanda.download(url)
//                    .
        }
    }

}