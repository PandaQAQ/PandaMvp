package com.pandaq.uires.configs

/**
 * Created by huxinyu on 2019/8/8.
 * Email : panda.h@foxmail.com
 * Description :
 */
class ToastConfig {

    companion object {
        const val COLOR_NULL = -1
        private val config by lazy {
            ToastConfig()
        }

        fun get(): ToastConfig = config
    }

}