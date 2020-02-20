package com.pandaq.uires.configs

import androidx.annotation.ColorInt

/**
 * Created by huxinyu on 2019/8/8.
 * Email : panda.h@foxmail.com
 * Description :
 */
class SnackerConfig {

    companion object {
        const val COLOR_NULL = -1
        private val config by lazy {
            SnackerConfig()
        }

        fun get(): SnackerConfig = config
    }

    private var msgColor = COLOR_NULL
    private var actionColor = COLOR_NULL
    private var backgroundColor = COLOR_NULL

    fun setMsgColor(@ColorInt color: Int): SnackerConfig {
        this.msgColor = color
        return this
    }

    fun setActionColor(@ColorInt color: Int): SnackerConfig {
        this.actionColor = color
        return this
    }

    fun setBackgroundColor(@ColorInt color: Int): SnackerConfig {
        this.backgroundColor = color
        return this
    }

    fun getMsgColor(): Int {
        return msgColor
    }

    fun getActionColor(): Int {
        return actionColor
    }

    fun getBackgroundColor(): Int {
        return backgroundColor
    }
}