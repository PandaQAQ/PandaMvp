package com.pandaq.uires.mvp.core

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.viewbinding.ViewBinding
import com.pandaq.appcore.R
import com.pandaq.appcore.log.PLogger
import com.pandaq.rxpanda.utils.CastUtils
import com.pandaq.uires.widget.guide.GuideCoverView
import java.lang.reflect.ParameterizedType

/**
 * Created by huxinyu on 2018/5/19.
 * Email : panda.h@foxmail.com
 *
 *
 * Description :给出的模板基类
 */
abstract class CoreBaseActivity<P : BasePresenter<*>, VB : ViewBinding> : AppCompatActivity(),
    IView {

    private var mParentView: FrameLayout? = null

    /**
     * 遮罩引导载体图层
     */
    protected var mGuideCoverView: GuideCoverView? = null

    protected var mPresenter: P? = null

    private var clazzVB: Class<VB>

    protected lateinit var binding: VB

    init {
        val type = (this.javaClass.genericSuperclass as ParameterizedType)
        val typeArray = type.actualTypeArguments
        var clazzV: Class<*>? = null
        this.javaClass.genericInterfaces.forEach {
            val v = it as Class<*>
            if (IView::class.java.isAssignableFrom(v)) {
                clazzV = v
                return@forEach
            }
        }
        mPresenter = if (clazzV != null) {
            val clazzP: Class<P> = CastUtils.cast(typeArray[0])
            clazzP.getConstructor().newInstance()
        } else {
            PLogger.e(
                "MVPCore::",
                "############################### 注意 ###########################"
            )
            PLogger.e(
                "MVPCore::",
                "\n\n\n"
            )
            PLogger.e(
                "MVPCore::",
                "${this::class.java.simpleName} 必须实现对应 Presenter<V> 的泛型接口 V！！！"
            )
            PLogger.e(
                "MVPCore::",
                "\n\n\n"
            )
            PLogger.e(
                "MVPCore::",
                "############################### 注意 ###########################"
            )
            null
        }
        mPresenter?.attachView(this)
        clazzVB = CastUtils.cast(typeArray[1])
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
        )
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        // Translucent status bar
        window.statusBarColor = resources.getColor(R.color.toolbarBackground)
        // Translucent navigation bar
        window.navigationBarColor = Color.TRANSPARENT
        mPresenter?.let {
            lifecycle.addObserver(it as LifecycleObserver)
        }
        initVariable()
        binding = inflateViewBinding(layoutInflater)
        if (getRootView() != null) {
            val content = getRootView()
            content?.addView(binding.root)
            setContentView(content)
        } else {
            setContentView(binding.root)
        }
        // 如果是新手向导页则初始化向导载体图层
        if (showGuideCoverView()) {
            initGuide()
        }
        initToolbar()
        initStateLayout()
        initView()
        loadData()
    }

    /**
     * 如果需要往给界面添加父布局则重写此方法
     */
    protected open fun getRootView(): ViewGroup? {
        return null
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    protected open fun showGuideCoverView(): Boolean {
        return false
    }

    /**
     * 是否为透明状态栏界面
     */
    protected open fun fullScreen(): Boolean {
        return false
    }

    /**
     * 初始化向导图层
     */
    protected open fun initGuide() {
        mParentView = window.decorView as FrameLayout
        if (mGuideCoverView == null) {
            mGuideCoverView = GuideCoverView(mParentView)
        }
    }

    /**
     * 显示 GuideCoverV
     */
    protected open fun showGuideView() {
        mGuideCoverView?.let {
            mParentView?.addView(mGuideCoverView)
        }
    }

    /**
     * 初始化属性参数值
     */
    protected abstract fun initVariable()

    /**
     * 初始化 toolbar
     */
    protected abstract fun initToolbar()

    /**
     * 初始化加载状态 layout
     */
    protected abstract fun initStateLayout()

    /**
     * 初始化 View 视图
     */
    protected abstract fun initView()

    /**
     * 加载数据
     */
    protected abstract fun loadData()

    /**
     * 封装的 fragment 切换工具方法（如 Home BaseActivity 导航栏切换不同功能模块 fragment,隐藏显示
     * fragment 不会进行入栈出栈管理
     * @param containerId 显示 fragment 的 layout 资源 ID
     * @param from        当前显示的 fragment
     * @param to          即将显示的 fragment
     * @return 切换后的 currentFragment
     */
    protected fun switchFragment(containerId: Int, @Nullable from: Fragment?, to: Fragment):
            Fragment {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        if (to.isAdded) {
            if (from != null) {
                transaction.hide(from).show(to).commit()
            }
        } else {
            if (from == null) {
                transaction.add(containerId, to).commit()
            } else {
                transaction.hide(from).add(containerId, to).commit()
            }
        }
        return to
    }

    protected fun removeFragment(fragment: Fragment?) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        fragment?.let {
            if (fragment.isAdded) {
                transaction.remove(fragment)
                transaction.commitAllowingStateLoss()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.let {
            lifecycle.removeObserver(it as LifecycleObserver)
        }
    }

    /**
     * inflate
     */
    private fun inflateViewBinding(layoutInflater: LayoutInflater): VB {
        return CastUtils.cast(
            clazzVB.getMethod("inflate", LayoutInflater::class.java).invoke(
                null,
                layoutInflater
            )
        )
    }
}