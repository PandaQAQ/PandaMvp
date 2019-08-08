package com.pandaq.uires.configs

/**
 * Created by huxinyu on 2019/8/8.
 * Email : panda.h@foxmail.com
 * Description :公共 ui 的 配置类
 */
class CommonUiConfigs {

    companion object {
        fun toast(): ToastConfig = ToastConfig.get()
        fun snacker(): SnackerConfig = SnackerConfig.get()
    }

}