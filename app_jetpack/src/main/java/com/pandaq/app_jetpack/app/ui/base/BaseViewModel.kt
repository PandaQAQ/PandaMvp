package com.pandaq.app_jetpack.app.ui.base

import androidx.lifecycle.ViewModel
import com.pandaq.app_jetpack.app.net.ApiService
import com.pandaq.rxpanda.RxPanda
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by huxinyu on 2020/3/30.
 * Email : panda.h@foxmail.com
 * Description :
 */
open class BaseViewModel : ViewModel() {

    private var mCompositeDisposable: CompositeDisposable? = null

    protected val api by lazy {
        RxPanda.retrofit()
                .baseUrl("http://news-at.zhihu.com/api/4/")
                .create(ApiService::class.java)
    }

    fun addDisposable(disposable: Disposable?) {
        if (mCompositeDisposable == null || mCompositeDisposable!!.isDisposed) { //csb 如果解绑了的话添加 sb 需要新的实例否则绑定时无效的
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable?.add(disposable!!)
    }

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable?.dispose()
    }
}