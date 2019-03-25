package com.pandaq.pandamvp.ui.launch

import android.content.Intent
import com.pandaq.pandamvp.R
import com.pandaq.pandamvp.framework.AppBaseActivity
import com.pandaq.pandamvp.ui.home.HomeActivity
import kotlinx.android.synthetic.main.app_activity_flash.*

/**
 * Created by huxinyu on 2019/3/25.
 * Email : panda.h@foxmail.com
 * Description :
 */
class FlashActivity : AppBaseActivity<FlashPresenter<FlashContract.View>>(), FlashContract.View {
    override fun injectPresenter(): FlashPresenter<FlashContract.View> {
        return FlashPresenter(this)
    }

    override fun bindContentRes(): Int = R.layout.app_activity_flash

    override fun initVariable() {
    }

    override fun initView() {
        iv_flash.setOnClickListener {
            val intent = Intent(this@FlashActivity, HomeActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun loadData() {

    }

}