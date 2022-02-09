package com.pandaq.uires.router.service

import com.alibaba.android.arouter.facade.template.IProvider


/**
 * Created by huxinyu on 2019/4/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :收银台组件跨组件路由服务方法暴露
 */
interface ICashierService : IProvider {

    companion object {
        const val path = "/service/cashier"
    }

    /**
     * 支付订单
     */
    fun pay(orderId: String)

}