package com.pandaq.pandamvp

import android.app.Activity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.pandaq.router.routers.RouterPath

/**
 * Created by huxinyu on 2019/6/3.
 * Email : panda.h@foxmail.com
 * Description :shell empty launcher
 */
class EmptyLauncherActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        ARouter.getInstance()
                .build(RouterPath.LAUNCH_ACTIVITY_FLASH)
                .navigation()
        this.finish()
//        btn_jump.setOnClickListener {
//            ARouter.getInstance()
//                    .build(RouterPath.ROUTE_404)
//                    .navigation()
//        }
    }
}