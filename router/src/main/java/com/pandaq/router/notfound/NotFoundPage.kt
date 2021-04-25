package com.pandaq.router.notfound

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.pandaq.router.R
import com.pandaq.router.routers.RouterPath

/**
 * Created by huxinyu on 4/23/21.
 * Email : panda.h@foxmail.com
 * Description :路由丢失页面
 */
@Route(path = RouterPath.ROUTE_404)
class NotFoundPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_404)
    }

}