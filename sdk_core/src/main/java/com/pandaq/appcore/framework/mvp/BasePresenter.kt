package com.pandaq.appcore.framework.mvp

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.pandaq.rxpanda.exception.ApiException
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by huxinyu on 2018/4/2.
 * Email : panda.h@foxmail.com
 * Description :BasePresenter 实现类基类模板,可直接 module 中继承重写生命周期函数
 */
abstract class BasePresenter<V : IView>(protected val mView: V?) : LifecycleObserver {

    //将所有正在处理的Subscription都添加到CompositeSubscription中。统一退出的时候注销观察
    private var mCompositeDisposable: CompositeDisposable? = null
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
        Log.d("LifeCycle", "onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        dispose()
        Log.d("LifeCycle", "dispose")
    }


    protected open fun handelError(showErrorPage: Boolean, e: ApiException?) {
        e?.let {
            mView?.showError(showErrorPage, e.message)
        }
    }

}