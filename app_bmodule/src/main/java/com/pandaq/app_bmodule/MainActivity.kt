package com.pandaq.app_bmodule

import com.alibaba.android.arouter.facade.annotation.Route
import com.lxj.xpopup.XPopup
import com.pandaq.app_bmodule.databinding.BActivityMainBinding
import com.pandaq.uires.mvp.core.BasePresenter
import com.pandaq.router.route.RouterPath
import com.pandaq.rxpanda.exception.ApiException
import com.pandaq.rxpanda.observer.ApiObserver
import com.pandaq.uires.mvp.BaseActivity
import com.pandaq.uires.update.UpdatePopup
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

@Route(path = RouterPath.B_ACTIVITY_MAIN)
class MainActivity : BaseActivity<BasePresenter<*>, BActivityMainBinding>() {

    override fun initVariable() {

    }

    override fun loadData() {

    }

    override fun initView() {
        binding.let {
            it.tvContent.setOnClickListener {
                val xpopup = XPopup.Builder(this).asCustom(UpdatePopup(this, "a"))
                xpopup.show()
            }

            it.pbProgress.setOnClickListener {
                Observable.interval(0, 1, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .map { time ->
                            binding.pbProgress.updateProgress(time.toInt())
                            return@map time
                        }
                        .subscribe(object : ApiObserver<Long>() {
                            override fun onSuccess(p0: Long) {

                            }

                            override fun finished(p0: Boolean) {

                            }

                            override fun onError(p0: ApiException?) {

                            }

                        })
            }
        }
    }
}
