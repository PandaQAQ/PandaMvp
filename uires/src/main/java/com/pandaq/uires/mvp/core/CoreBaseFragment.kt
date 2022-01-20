package com.pandaq.uires.mvp.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.viewbinding.ViewBinding
import com.pandaq.appcore.log.PLogger
import com.pandaq.rxpanda.utils.CastUtils
import java.lang.reflect.ParameterizedType

/**
 * Created by huxinyu on 2018/5/19.
 * Email : panda.h@foxmail.com
 *
 *
 * Description : 给出的模板基类,可选择继承此类实现 bindButterKnife（）方法使用 ButterKnife 绑定 UI
 * 也可完全自己写基类绑定 UI
 */
abstract class CoreBaseFragment<P : BasePresenter<*>?, VB : ViewBinding> : Fragment(), IView {

    protected var contentView: View? = null

    protected var mPresenter: P? = null

    private var clazzVB: Class<VB>

    protected lateinit var binding: VB

    init {
        val type = (this.javaClass.genericSuperclass as ParameterizedType)
        val typeArray = type.actualTypeArguments
        var clazzV: Class<*>? = null
        this.javaClass.genericInterfaces.forEach {
            val v = it as Class<*>
            // 判断 IView 是否为 v 的父类
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
                "${this::class.java.simpleName} 必须实现对应 Presenter<V> 的泛型接口 V！！！"
            )
            null
        }
        mPresenter?.attachView(this)
        clazzVB = CastUtils.cast(typeArray[1])
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter?.let {
            lifecycle.addObserver(it as LifecycleObserver)
        }
        initVariable()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = inflateViewBinding(layoutInflater, container)
        return if (getRootView() != null) {
            val content = getRootView()
            content?.addView(binding.root)
            content
        } else {
            binding.root
        }
    }

    /**
     * 如果需要往给界面添加父布局则重写此方法
     */
    protected open fun getRootView(): ViewGroup? {
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initStateLayout()
        initView()
        loadData()
    }

    /**
     * 初始化加载状态 layout
     */
    protected abstract fun initStateLayout()

    /**
     * 初始化属性参数值
     */
    protected abstract fun initVariable()

    /**
     * 初始化 View 视图
     */
    protected abstract fun initView()

    /**
     * 加载数据
     */
    protected abstract fun loadData()

    /**
     * 封装的 fragment 切换工具方法 fragment 中嵌套子 fragment
     *
     * @param containerId 显示 fragment 的 layout 资源 ID
     * @param from        当前显示的 fragment
     * @param to          即将显示的 fragment
     * @return 切换后的 currentFragment
     */
    protected fun switchFragment(containerId: Int, from: Fragment?, to: Fragment): Fragment {
        val manager = childFragmentManager
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
        mPresenter?.let {
            lifecycle.removeObserver(it as LifecycleObserver)
        }
    }
    /**
     * inflate
     */
    private fun inflateViewBinding(layoutInflater: LayoutInflater, container: ViewGroup?): VB {
        return CastUtils.cast(clazzVB.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java,
                Boolean::class.java).invoke(null, layoutInflater, container, false))
    }
}