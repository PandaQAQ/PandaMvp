package com.pandaq.uires.router.service

import com.alibaba.android.arouter.facade.template.IProvider


/**
 * Created by huxinyu on 2019/4/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :用户信息组件跨组件路由服务方法暴露
 */
interface IUserService : IProvider {

    companion object {
        const val path = "/service/user"
    }

    /**
     * 跨组件获取用户 Id
     */
    fun getUserId(): String

    /**
     * 跨组件获取登陆状态
     */
    fun isLogin(): Boolean

}