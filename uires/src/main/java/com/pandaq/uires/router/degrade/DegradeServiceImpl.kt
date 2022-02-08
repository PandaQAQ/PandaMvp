package com.pandaq.uires.router.degrade

import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.DegradeService
import com.pandaq.uires.commonpage.notfound.NotFoundActivity

/**
 * Created by huxinyu on 4/23/21.
 * Email : panda.h@foxmail.com
 * Description : 路由全局降级策略
 */
@Route(path = "/xxx/xxx")
class DegradeServiceImpl : DegradeService {
    override fun onLost(context: Context?, postcard: Postcard?) {
        val notFoundIntent = Intent(context, NotFoundActivity::class.java)
        context?.startActivity(notFoundIntent)
    }

    override fun init(context: Context?) {

    }
}