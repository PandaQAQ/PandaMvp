package com.pandaq.uires.router

/**
 * Created by huxinyu on 2022/2/8.
 * Email : panda.h@foxmail.com
 * Description :
 */
class RouteRoot {
    companion object {
        // 用户组件路由分组
        const val USER = "/user"

        // 便民信息组件路由分组
        const val INFO = "/information"

        // im 组件路由分组
        const val IM = "/im"

        // 收银台组件路由分组
        const val CASHIER = "/cashier"

        /**
         * 以下为主工程内的模块分组
         */

        // 主模块路由分组
        const val MAIN = "/main"

        // 跑腿模块路由分组
        const val ERRAND = "/errand"

        // 外卖模块路由分组
        const val TAKEOUT = "/takeout"

        // 商城模块路由分组
        const val MALL = "/mall"


    }
}