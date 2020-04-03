package com.pandaq.appcore.framework.mvp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.pandaq.appcore.guide.GuideCoverView

/**
 * Created by huxinyu on 2018/5/19.
 * Email : panda.h@foxmail.com
 *
 *
 * Description :给出的模板基类
 * 也可完全自己写基类绑定 UI
 */
abstract class BaseActivity<P : BasePresenter<*>> : AppCompatActivity(), IMvpView {

    private var mParentView: FrameLayout? = null
    /**
     * 遮罩引导载体图层
     */
    protected var mGuideCoverView: GuideCoverView? = null

    protected var mPresenter: P? = null

    protected abstract fun injectPresenter(): P?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isTransStatus()){
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        mPresenter = injectPresenter()
        mPresenter?.let {
            lifecycle.addObserver(it as LifecycleObserver)
        }
        initVariable()
        if (bindContentRes() != 0) {
            setContentView(bindContentRes())
        } else {
            throw RuntimeException("must bindContentRes first!!!")
        }
        // 如果是新手向导页则初始化向导载体图层
        if (showGuideCoverView()) {
            initGuide()
        }
        initToolbar()
        initView()
        loadData()
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
    protected open fun isTransStatus(): Boolean {
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
     * 绑定当前 BaseFragment 的 layout 资源 id
     *
     * @return layout 资源 id
     */
    protected abstract fun bindContentRes(): Int

    /**
     * 初始化属性参数值
     */
    protected abstract fun initVariable()

    /**
     * 初始化 toolbar
     */
    protected abstract fun initToolbar()

    /**
     * 初始化 View 视图
     */
    protected abstract fun initView()

    /**
     * 加载数据
     */
    protected abstract fun loadData()

    /**
     * 封装的 fragment 切换工具方法（如 Home BaseActivity 导航栏切换不同功能模块 fragment）
     *
     * @param containerId 显示 fragment 的 layout 资源 ID
     * @param from        当前显示的 fragment
     * @param to          即将显示的 fragment
     * @return 切换后的 currentFragment
     */
    protected fun switchFragment(containerId: Int, from: Fragment?, to: Fragment): Fragment {
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

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycle", "removeObserver")
        mPresenter?.let {
            lifecycle.removeObserver(it as LifecycleObserver)
        }
    }
}