package com.pandaq.router.routers

/**
 * Created by huxinyu on 2019/4/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :路由路径
 */
class RouterPath {
    companion object {
        private const val GROUP_LAUNCH = "/app_launcher"
        private const val GROUP_JETPACK = "/app_jetpack"
        private const val GROUP_A = "/app_a"
        private const val GROUP_B = "/app_b"

        const val SERVICE = "/service"

        const val LAUNCH_ACTIVITY_FLASH = "$GROUP_LAUNCH/launch/FlashActivity"
        const val LAUNCH_ACTIVITY_HOME = "$GROUP_LAUNCH/home/HomeActivity"
        const val LAUNCH_ACTIVITY_TEST = "$GROUP_LAUNCH/TestListActivity"

        const val JETPACK_MAIN = "$GROUP_JETPACK/MainActivity"
        const val JETPACK_ZHIHU_LIST = "$GROUP_JETPACK/mvp/zhihu/ZhihuListActivity"

        const val A_ACTIVITY_MAIN = "$GROUP_A/MainActivity"
        const val A_SERVICE = "$GROUP_A/$SERVICE/AModuleService"


        const val B_ACTIVITY_MAIN = "$GROUP_B/MainActivity"
        const val B_SERVICE = "$GROUP_B/$SERVICE/BModuleService"
    }
}