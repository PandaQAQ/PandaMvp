package com.pandaq.uires.configs

import com.pandaq.appcore.imageloader.PicLoaderConfig
import com.pandaq.uires.widget.emptyview.EmptyConfig

/**
 * Created by huxinyu on 2019/8/8.
 * Email : panda.h@foxmail.com
 * Description :公共 ui 的 配置类
 */
class UiConfigs {

    companion object {

        /**
         * toast 全局配置
         */
        fun toast() = ToastConfig.get()

        /**
         * snackbar 全局配置
         */
        fun snackbar() = SnackerConfig.get()

        /**
         * 图片加载的全局配置
         */
        fun imageLoader() = PicLoaderConfig.get()

        /**
         * 列表状态页全局配置
         */
        fun stateLayout() = EmptyConfig.get()

    }

}