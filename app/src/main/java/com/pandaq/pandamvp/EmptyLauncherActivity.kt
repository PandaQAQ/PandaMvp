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
        ARouter.getInstance()
                .build(RouterPath.APP_ACTIVITY_FLASH)
                .navigation()
        this.finish()
    }
}