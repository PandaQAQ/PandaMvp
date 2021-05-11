package com.pandaq.appcore.framework.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver

/**
 * Created by huxinyu on 2018/5/19.
 * Email : panda.h@foxmail.com
 *
 *
 * Description : 给出的模板基类,可选择继承此类实现 bindButterKnife（）方法使用 ButterKnife 绑定 UI
 * 也可完全自己写基类绑定 UI
 */
abstract class CoreBaseFragment<P : BasePresenter<*>?> : Fragment(), IView,LifecycleObserver {
    protected var contentView: View? = null
    protected var mPresenter: P? = null
    protected abstract fun injectPresenter(): P
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = injectPresenter()
        mPresenter?.let {
            lifecycle.addObserver(it as LifecycleObserver)
        }
        initVariable()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (getContentRes() == 0) {
            throw RuntimeException("must binContentRes first !!!")
        }
        contentView = inflater.inflate(getContentRes(), container, false)
        return contentView
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
     * 绑定当前 BaseFragment 的 layout 资源 id
     *
     * @return layout 资源 id
     */
    protected abstract fun getContentRes(): Int

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

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter?.let {
            lifecycle.removeObserver(it as LifecycleObserver)
        }
    }
}