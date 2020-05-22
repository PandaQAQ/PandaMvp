package com.pandaq.app_jetpack.app.ui.zhihu

import androidx.lifecycle.MutableLiveData
import com.pandaq.app_jetpack.app.entity.ZhihuData
import com.pandaq.app_jetpack.app.ui.base.BaseViewModel
import com.pandaq.app_jetpack.app.net.AppCallBack
import com.pandaq.rxpanda.transformer.RxScheduler
import com.pandaq.uires.msgwindow.Toaster

/**
 * Created by huxinyu on 2020/3/30.
 * Email : panda.h@foxmail.com
 * Description :
 */
class ZhihuViewModel : BaseViewModel() {

    var zhihuLiveData: MutableLiveData<ZhihuData> = MutableLiveData()

    fun getDataList() {
        api.test()
                .doOnSubscribe {
                    addDisposable(it)
                }
                .compose(RxScheduler.sync())
                .subscribe(object : AppCallBack<ZhihuData>() {
                    override fun success(data: ZhihuData) {
                        zhihuLiveData.postValue(data)
                    }

                    override fun fail(code: Long?, msg: String?) {
                        Toaster.showError(msg)
                    }

                    override fun finish(success: Boolean) {

                    }

                })
    }

    fun getHistory(date: String) {
        api.history(date)
                .doOnSubscribe {
                    addDisposable(it)
                }
                .compose(RxScheduler.sync())
                .subscribe(object : AppCallBack<ZhihuData>() {
                    override fun success(data: ZhihuData) {
                        zhihuLiveData.postValue(data)
                    }

                    override fun fail(code: Long?, msg: String?) {
                        Toaster.showError(msg)
                    }

                    override fun finish(success: Boolean) {

                    }

                })
    }

}