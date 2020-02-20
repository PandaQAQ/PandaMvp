package com.pandaq.app_bmodule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.pandaq.router.routers.RouterPath

@Route(path = RouterPath.B_ACTIVITY_MAIN)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.b_activity_main)
    }
}
