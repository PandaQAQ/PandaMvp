package com.pandaq.app_jetpack

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.pandaq.router.routers.RouterPath

@Route(path = RouterPath.JETPACK_MAIN)
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_activity_main)

        val intent = Intent()
        intent.putExtra("data", "MainActivity")
        setResult(Activity.RESULT_OK, intent)
    }

}
