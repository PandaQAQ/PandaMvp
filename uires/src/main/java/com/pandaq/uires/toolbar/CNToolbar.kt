package com.pandaq.uires.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.pandaq.uires.R

/**
 * Created by huxinyu on 2019/9/10.
 * Email : panda.h@foxmail.com
 * Description :
 */

class CNToolbar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var arrowBack: ImageView? = null
    private var titleView: TextView? = null
    private var menuText: TextView? = null
    private var divider: View? = null
    private var menuImage: ImageView? = null
    private var isDark = false
    private var showDivider = false
    private var dividerColor = context.resources.getColor(R.color.res_dividing)

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.CNToolbar)
        addView(inflate(context, R.layout.res_cn_toolbar, null))
        arrowBack = findViewById(R.id.iv_back)
        titleView = findViewById(R.id.tv_title)
        menuText = findViewById(R.id.tv_menu)
        menuImage = findViewById(R.id.iv_menu)
        isDark = ta.getBoolean(R.styleable.CNToolbar_darkStyle, false)
        showDivider = ta.getBoolean(R.styleable.CNToolbar_showDivider, false)
        dividerColor = ta.getColor(R.styleable.CNToolbar_dividerColor, dividerColor)
        setDarkStyle(isDark)
        divider?.visibility =
                if (showDivider) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
        ta.recycle()
    }

    fun setDarkStyle(isDark: Boolean) {
        if (isDark) {
            arrowBack?.setImageResource(R.drawable.arrow_back_ios_white)
            titleView?.setTextColor(resources.getColor(R.color.res_colorWhite))
            menuText?.setTextColor(resources.getColor(R.color.res_colorWhite))
        } else {
            arrowBack?.setImageResource(R.drawable.arrow_back_ios)
            titleView?.setTextColor(resources.getColor(R.color.res_colorBlack))
            menuText?.setTextColor(resources.getColor(R.color.res_colorTextMinor))
        }
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

    fun hideMenuText(hide: Boolean) {
        if (hide) {
            menuText?.visibility = View.GONE
        } else {
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

    fun setDividerColor(@ColorInt color: Int) {
        divider?.setBackgroundColor(color)
    }
}