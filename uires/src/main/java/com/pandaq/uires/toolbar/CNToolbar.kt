package com.pandaq.uires.widget.toolbar

import android.content.Context
import android.support.annotation.AttrRes
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.pandaq.uires.R

/**
 * Created by huxinyu on 2019/9/10.
 * Email : panda.h@foxmail.com
 * Description :
 */

class CNToolbar : FrameLayout {

    companion object {
        const val MENU_TEXT = 0
        const val MENU_IMAGE = 1
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, @AttrRes defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var arrowBack: ImageView? = null
    private var titleView: TextView? = null
    private var menuText: TextView? = null
    private var menuImage: ImageView? = null

    init {
        addView(inflate(context, R.layout.res_cn_toolbar, null))
        arrowBack = findViewById(R.id.iv_back)
        titleView = findViewById(R.id.tv_title)
        menuText = findViewById(R.id.tv_menu)
        menuImage = findViewById(R.id.iv_menu)
    }

    fun setTitle(title: String) {
        titleView?.text = title
    }

    fun setTitle(title: CharSequence?) {
        titleView?.text = title
    }

    /**
     * 设置 menu 文本颜色
     */
    fun setMenuColor(@ColorInt textColor: Int) {
        menuText?.setTextColor(textColor)
    }

    fun setTitle(@StringRes title: Int) {
        titleView?.text = context.getString(title)
    }

    /**
     * 显示文本
     */
    fun showMenu(menu: String) {
        menuText?.text = menu
        menuText?.visibility = View.VISIBLE
        menuImage?.visibility = View.GONE
    }

    fun setMenuTextBackground(@DrawableRes menu: Int) {
        menuText?.setBackgroundResource(menu)
    }

    /**
     * 显示 icon
     */
    fun showMenu(@DrawableRes menu: Int) {
        menuImage?.setImageResource(menu)
        menuText?.visibility = View.GONE
        menuImage?.visibility = View.VISIBLE
    }

    fun hideMenuText(hide:Boolean){
        if (hide){
            menuText?.visibility = View.GONE
        }else{
            menuText?.visibility = View.VISIBLE
        }
    }

    fun setMenuEnable(enable: Boolean) {
        menuText?.isEnabled = enable
        menuImage?.isEnabled = enable
    }

    fun setMenuClickListener(listener: OnClickListener) {
        if (menuText?.visibility == View.VISIBLE) {
            menuText?.setOnClickListener(listener)
        }
        if (menuImage?.visibility == View.VISIBLE) {
            menuImage?.setOnClickListener(listener)
        }
    }

    fun setOnBackPressed(listener: OnClickListener) {
        arrowBack?.setOnClickListener(listener)
    }
}