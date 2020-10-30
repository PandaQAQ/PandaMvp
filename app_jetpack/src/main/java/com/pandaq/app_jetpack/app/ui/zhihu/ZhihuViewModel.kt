package com.pandaq.app_jetpack.app.ui.zhihu

import androidx.lifecycle.MutableLiveData
import com.pandaq.app_jetpack.app.entity.ZhihuData
import com.pandaq.app_jetpack.app.net.AppCallBack
import com.pandaq.app_jetpack.app.ui.base.BaseViewModel
import com.pandaq.rxpanda.transformer.RxScheduler
import com.pandaq.rxpanda.utils.GsonUtil
import com.pandaq.uires.msgwindow.Toaster
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Created by huxinyu on 2020/3/30.
 * Email : panda.h@foxmail.com
 * Description :
 */
class ZhihuViewModel : BaseViewModel() {

    var zhihuLiveData: MutableLiveData<ZhihuData> = MutableLiveData()

    fun getDataList() {
//        api.test()
//                .doOnSubscribe {
//                    addDisposable(it)
//                }
//                .compose(RxScheduler.sync())
//                .subscribe(object : AppCallBack<ZhihuData>() {
//                    override fun success(data: ZhihuData) {
//                        zhihuLiveData.postValue(data)
//                    }
//
//                    override fun fail(code: Long?, msg: String?) {
//                        Toaster.showError(msg)
//                    }
//
//                    override fun finish(success: Boolean) {
//
//                    }
//
//                })
        GlobalScope.launch(Dispatchers.Main) {
            val data = async {
                api.test()
            }
            zhihuLiveData.postValue(data.await())
        }

        GlobalScope.launch(Dispatchers.Main) {
            val data = async {
                api.wanAndroid()
            }
            println(GsonUtil.gson().toJson(data.await()))
        }
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