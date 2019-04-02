package com.pandaq.pandamvp.ui.launch

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
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
        val animator = AlphaAnimation(0f, 1f)
        animator.duration = 800
        animator.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                val intent = Intent(this@FlashActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onAnimationStart(animation: Animation?) {

            }

        })
        iv_flash.startAnimation(animator)
    }

    override fun loadData() {

    }

}