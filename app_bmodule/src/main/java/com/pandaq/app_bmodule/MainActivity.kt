package com.pandaq.app_bmodule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.lxj.xpopup.XPopup
import com.pandaq.router.routers.RouterPath
import com.pandaq.rxpanda.exception.ApiException
import com.pandaq.rxpanda.observer.ApiObserver
import com.pandaq.rxpanda.transformer.RxScheduler
import com.pandaq.uires.update.UpdatePopup
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.b_activity_main.*
import java.util.concurrent.TimeUnit

@Route(path = RouterPath.B_ACTIVITY_MAIN)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.b_activity_main)

        tv_content.setOnClickListener {
            val xpopup = XPopup.Builder(this).asCustom(UpdatePopup(this, "a"))
            xpopup.show()
        }

        pb_progress.setOnClickListener {
            Observable.interval(0, 1, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .map {
                        pb_progress.updateProgress(it.toInt())
                        return@map it
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
