package com.pandaq.router.routers

/**
 * Created by huxinyu on 2019/4/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :路由路径
 */
class RouterPath {
    companion object {
        private const val GROUP_A = "/app_a"
        private const val GROUP_B = "/app_b"
        private const val GROUP_LAUNCH = "/app_launcher"

        const val SERVICE = "/service"

        const val APP_ACTIVITY_FLASH = "$GROUP_LAUNCH/launch/FlashActivity"
        const val APP_ACTIVITY_HOME = "$GROUP_LAUNCH/home/HomeActivity"

        const val A_ACTIVITY_MAIN = "$GROUP_A/MainActivity"
        const val A_SERVICE = "$GROUP_A/$SERVICE/AModuleService"

        const val B_ACTIVITY_MAIN = "$GROUP_B/MainActivity"
        const val B_SERVICE = "$GROUP_B/$SERVICE/BModuleService"
    }
}