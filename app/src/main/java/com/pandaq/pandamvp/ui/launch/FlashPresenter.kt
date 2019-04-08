package com.pandaq.pandamvp.ui.launch

import com.pandaq.appcore.framework.mvp.BasePresenter
import com.pandaq.appcore.network.transformer.RxScheduler
import com.pandaq.pandamvp.net.AppCallBack
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import java.util.concurrent.TimeUnit

/**
 * Created by huxinyu on 2019/3/25.
 * Email : panda.h@foxmail.com
 * Description :
 */
class FlashPresenter(view: FlashContract.View) : BasePresenter<FlashContract.View>(view) {

}