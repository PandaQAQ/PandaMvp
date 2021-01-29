package com.pandaq.uires.utils

import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.pandaq.appcore.utils.system.DisplayUtils
import com.pandaq.uires.R

/**
 * Created by huxinyu on 2019/9/27.
 * Email : panda.h@foxmail.com
 * Description :
 */
fun MaterialDialog.compileSize(): MaterialDialog {
    val params = this.window?.attributes
    params?.width = DisplayUtils.getScreenWidth() - DisplayUtils.dp2px(32f)
    this.window?.attributes = params
    return this
}

fun MaterialDialog.transWindow(): MaterialDialog {
    this.window?.setBackgroundDrawableResource(R.color.btn_red_normal)
    val parent = this.customView?.parent as View
    parent.transAll()
    return this
}

fun View.transAll() {
    if (this.parent != null) {
        val parent = this.parent as View
        parent.setBackgroundResource(R.color.colorTrans)
        parent.transAll()
    }
}