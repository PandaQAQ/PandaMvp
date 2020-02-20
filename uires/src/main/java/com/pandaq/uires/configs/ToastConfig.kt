package com.pandaq.uires.configs

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.Gravity
import androidx.annotation.ColorInt
import com.pandaq.appcore.utils.system.AppUtils
import com.pandaq.uires.R

/**
 * Created by huxinyu on 2019/8/8.
 * Email : panda.h@foxmail.com
 * Description :
 */
class ToastConfig {

    // 正常背景 drawable
    var normalBackground: Drawable? = null
    // 正常文本颜色
    @ColorInt
    var normalTextColor: Int = AppUtils.getContext().resources.getColor(R.color.res_colorTextSuccess)
    // 正常 Icon 图标
    var normalIcon: Drawable = AppUtils.getContext().resources.getDrawable(R.drawable.ic_toast_success)


    // 错误背景 drawable
    var errorBackground: Drawable? = null
    // 错误文本颜色
    @ColorInt
    var errorTextColor: Int = AppUtils.getContext().resources.getColor(R.color.res_colorTextError)
    // 错误 Icon 图标
    var errorIcon: Drawable = AppUtils.getContext().resources.getDrawable(R.drawable.ic_toast_error)


    // 警告背景 drawable
    var warningBackground: Drawable? = null
    // 警告文本颜色
    @ColorInt
    var warningTextColor: Int = AppUtils.getContext().resources.getColor(R.color.res_colorTextWarning)
    // 警告 Icon 图标
    var warningIcon: Drawable = AppUtils.getContext().resources.getDrawable(R.drawable.ic_toast_warning)

    // 有 icon 时 icon 的 gravity
    var iconGravity: Int = Gravity.START


    companion object {
        const val COLOR_NULL = -1
        private val config by lazy {
            ToastConfig()
        }

        fun get(): ToastConfig = config
    }


}