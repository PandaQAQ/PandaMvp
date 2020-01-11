package com.pandaq.uires.utils

import com.afollestad.materialdialogs.MaterialDialog
import com.pandaq.appcore.utils.system.DisplayUtils

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