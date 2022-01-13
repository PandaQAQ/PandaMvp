package com.pandaq.appcore.framework.mvp

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.pandaq.appcore.event.ApiConfigChange
import com.pandaq.appcore.log.PLogger
import com.pandaq.rxpanda.exception.ApiException
import com.pandaq.rxpanda.utils.CastUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description :BasePresenter 实现类基类模板,可直接 module 中继承重写生命周期函数
 */
abstract class BasePresenter<V : IView> : LifecycleObserver {

    protected var mView: V? = null

    //将所有正在处理的Subscription都添加到CompositeSubscription中。统一退出的时候注销观察
    private var mCompositeDisposable: CompositeDisposable? = null

    fun <V> attachView(v: V) {
        PLogger.d("BasePresenter", "attachView")
        mView = CastUtils.cast(v)
    }

    fun addDisposable(disposable: Disposable?) {
        if (mCompositeDisposable == null || mCompositeDisposable!!.isDisposed) { //csb 如果解绑了的话添加 sb 需要新的实例否则绑定时无效的
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable!!.add(disposable!!)
    }

    private fun dispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable!!.dispose()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        // presenter 绑定到 MvpFragment 或 MvpActivity 时触发
        EventBus.getDefault().register(this)
    }



    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        dispose()
        EventBus.getDefault().unregister(this)
    }

    /**
     * Api 配置刷新
     */
    @Subscribe
    fun onApiConfigRefresh(apiConfigChange: ApiConfigChange) {
        refreshApiConfig()
    }

    abstract fun refreshApiConfig()

    protected open fun showError(showErrorPage: Boolean, e: ApiException?) {
        e?.let {
            mView?.showError(showErrorPage, e.message)
        }
    }

}