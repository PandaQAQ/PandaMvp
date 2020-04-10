package com.pandaq.app_launcher.ui.home

import android.os.Environment
import android.util.Log
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.pandaq.app_launcher.R
import com.pandaq.app_launcher.framework.AppBaseActivity
import com.pandaq.app_launcher.framework.AppBasePresenter
import com.pandaq.appcore.imageloader.core.PicLoader
import com.pandaq.appcore.permission.RtPermission
import com.pandaq.appcore.utils.system.DisplayUtils
import com.pandaq.router.routers.RouterPath
import com.pandaq.uires.html.HtmlActivity
import com.pandaq.uires.msgwindow.Toaster
import com.pandaq.uires.widget.recyclerview.decoration.ItemDecoration
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.launcher_activity_home.*
import java.io.File

/**
 * Created by huxinyu on 2019/3/25.
 * Email : panda.h@foxmail.com
 * Description :
 */
@Route(path = RouterPath.LAUNCH_ACTIVITY_HOME)
class HomeActivity : AppBaseActivity<AppBasePresenter<*>>() {

    private val adapter: BaseQuickAdapter<String, BaseViewHolder> by lazy {
        val adp = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.launcher_item_homepage) {
            override fun convert(holder: BaseViewHolder, item: String) {
                holder.let {
                    val itemView = it.getView<ConstraintLayout>(R.id.cl_container)
                    val icon = it.getView<ImageView>(R.id.iv_icon)
                    itemView.layoutParams.width = DisplayUtils.getScreenWidth() / 3
                    it.setText(R.id.tv_name, item)
                    PicLoader.with(this@HomeActivity)
                            .load(iconList[it.adapterPosition])
                            .into(icon)
                }
            }
        }
        adp.addChildClickViewIds(R.id.cl_container)
        return@lazy adp
    }

    private lateinit var iconList: MutableList<Int>

    override fun injectPresenter(): AppBasePresenter<*>? {
        return null
    }

    override fun bindContentRes(): Int {
        return R.layout.launcher_activity_home
    }

    override fun initVariable() {
        iconList = mutableListOf()
        iconList.add(R.drawable.icon_panda_01)
        iconList.add(R.drawable.icon_panda_02)
        iconList.add(R.drawable.icon_panda_03)
        iconList.add(R.drawable.icon_panda_04)
        iconList.add(R.drawable.icon_panda_05)
        iconList.add(R.drawable.icon_panda_06)
        iconList.add(R.drawable.icon_panda_07)
        iconList.add(R.drawable.icon_panda_08)
    }

    override fun initView() {
        refreshList.setLayoutManager(StaggeredGridLayoutManager(3, RecyclerView.VERTICAL))
        refreshList.setAdapter(adapter)
        refreshList.setOnRefreshListener {
            loadData()
        }
        refreshList.setEnableLoadMore(false)
        val divider = ItemDecoration.Builder()
                .space(DisplayUtils.dp2px(8f))
                .spanCount(3)
                .build()
        refreshList.addItemDecoration(divider)
        adapter.setOnItemChildClickListener { adapter, _, position ->
            when (position) {
                0 -> {
                    testRxJava()
                }
                1 -> {
                    RtPermission.with(this)
                            .install(Environment.getExternalStoragePublicDirectory("panda")
                                    .absolutePath + File.separator + "panda.apk")
                            .onDenied {
                                Toaster.showWarning("无应用安装权限！")
                            }
                            .start()
                }
                2 -> {
                    Toaster.showWarning("警告消息")
                }
                3 -> {
                    Toaster.showSuccess("正常消息")
                }

                4 -> {
                    HtmlActivity.start("Vue Page", url = "http://192.168.0.73:8080/#/")
                }

                5 -> {
                    Toaster.showError("错误消息测试错误消息测试错误消息测试错误消息测试错误消息测试错误消息测试")
                }

                6 -> {
                    ARouter.getInstance()
                            .build(RouterPath.A_ACTIVITY_MAIN)
                            .navigation(this)
                }
                7 -> {
                    ARouter.getInstance()
                            .build(RouterPath.B_ACTIVITY_MAIN)
                            .navigation(this)
                }
                else -> {
                    Toaster.showError(adapter.data[position] as String)

                }
            }
        }
    }

    private fun testRxJava() {
        Observable.just("Data")
                .map {
                    Log.d("Map 1", Thread.currentThread().name)
                    return@map it
                }
                .doOnSubscribe {
                    Log.d("doOnSubscribe 1 ", Thread.currentThread().name)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .map {
                    Log.d("Map 2 ", Thread.currentThread().name)
                    return@map it
                }
                .doOnSubscribe {
                    Log.d("doOnSubscribe 2 ", Thread.currentThread().name)
                }
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .map {
                    Log.d("Map 3 ", Thread.currentThread().name)
                    return@map it
                }
                .subscribe(object : Observer<String> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.d("onSubscribe", Thread.currentThread().name)
                    }

                    override fun onNext(t: String) {
                        Log.d("onNext", Thread.currentThread().name)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
    }

    override fun loadData() {
        val list = arrayListOf<String>()
        list.add("RxJava2 Test")
        list.add("运行时权限")
        list.add("图片加载")
        list.add("网络请求")
        list.add("WebView")
        list.add("Dialogs")
        list.add("跳转 A Module ")
        list.add("跳转 B Module")
        adapter.setNewData(list)
        refreshList.finishRefresh(true)
    }
}