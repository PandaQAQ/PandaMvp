package com.pandaq.app_launcher.ui.launch

import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.pandaq.app_launcher.databinding.LauncherActivityFlashBinding
import com.pandaq.app_launcher.framework.AppBaseActivity
import com.pandaq.router.routers.RouterPath

/**
 * Created by huxinyu on 2019/3/25.
 * Email : panda.hxmail.com
 * Description :
 */
@Route(path = RouterPath.LAUNCH_ACTIVITY_FLASH)
class FlashActivity : AppBaseActivity<FlashPresenter, LauncherActivityFlashBinding>(), IFlashView {

    override fun initVariable() {

    }

    override fun fullScreen(): Boolean {
        return true
    }

    override fun initView() {
        val animator = AlphaAnimation(0f, 1f)
        animator.duration = 800
        animator.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                ARouter.getInstance()
                        .build(RouterPath.LAUNCH_ACTIVITY_HOME)
                        .navigation()
                finish()
            }

            override fun onAnimationStart(animation: Animation?) {

            }

        })
        binding.ivFlash.startAnimation(animator)
    }

    override fun loadData() {

    }

}