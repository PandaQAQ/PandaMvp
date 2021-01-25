package com.pandaq.app_jetpack.app.ui.zhihu

import androidx.lifecycle.MutableLiveData
import com.pandaq.app_jetpack.app.entity.ZhihuData
import com.pandaq.app_jetpack.app.net.AppCallBack
import com.pandaq.app_jetpack.app.ui.base.BaseViewModel
import com.pandaq.rxpanda.transformer.RxScheduler
import com.pandaq.rxpanda.utils.GsonUtil
import com.pandaq.uires.msgwindow.Toaster
import kotlinx.coroutines.*

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
        val zhihuScop = CoroutineScope(Dispatchers.Main)
//        方式 1 ，主线程，launch 后 async 异步获取
//        zhihuScop.launch {
//            val data = async {
//                api.test()
//            }
//            zhihuLiveData.postValue(data.await())
//        }
//        方式2，launch 切换线程
        zhihuScop.launch(Dispatchers.IO) {
            val data = async { api.test() }
            launch(Dispatchers.IO) {
                zhihuLiveData.postValue(data.await())
            }
        }

        // 通过 withContext() 指定线程，完成后会自动切回原来的线程
        zhihuScop.launch(Dispatchers.Main) {
            val data = withContext(Dispatchers.IO) {
                async { api.test() }
            }
            zhihuLiveData.postValue(data.await())
        }
        // 生命周期是全局的且不可取消，Android 不建议使用此方式来实现写成
//        GlobalScope.launch(Dispatchers.Main) {
//            val data = async {
//                api.test()
//            }
//            zhihuLiveData.postValue(data.await())
//        }
//
//        GlobalScope.launch(Dispatchers.Main) {
//            val data = async {
//                api.wanAndroid()
//            }
//            println(GsonUtil.gson().toJson(data.await()))
//        }

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