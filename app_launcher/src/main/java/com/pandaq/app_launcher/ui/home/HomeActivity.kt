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
import com.chad.library.adapter.base.BaseViewHolder
import com.pandaq.app_launcher.R
import com.pandaq.app_launcher.framework.AppBaseActivity
import com.pandaq.app_launcher.framework.AppBasePresenter
import com.pandaq.appcore.imageloader.core.PicLoader
import com.pandaq.appcore.permission.RtPermission
import com.pandaq.appcore.utils.system.DisplayUtils
import com.pandaq.router.routers.RouterPath
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
        return@lazy object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.launcher_item_homepage) {
            override fun convert(helper: BaseViewHolder, item: String?) {
                helper.let {
                    val itemView = it.getView<ConstraintLayout>(R.id.cl_container)
                    val icon = it.getView<ImageView>(R.id.iv_icon)
                    itemView.layoutParams.width = DisplayUtils.getScreenWidth() / 3
                    it.setText(R.id.tv_name, item)
                    it.addOnClickListener(R.id.cl_container)
                    PicLoader.with(this@HomeActivity)
                            .load(iconList[it.adapterPosition])
                            .into(icon)
                }
            }
        }
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
                    ARouter.getInstance()
                            .build(RouterPath.LAUNCH_ACTIVITY_TEST)
                            .navigation(this)
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
                .doOnSubscribe {
                    Log.d("doOnSubscribe1", Thread.currentThread().name)
                }
                .map {
                    Log.d("Subscribe1", Thread.currentThread().name)
                    return@map it
                }
//                .observeOn()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    Log.d("doOnSubscribe0", Thread.currentThread().name)
                }
                .map {
                    Log.d("Subscribe0 ", Thread.currentThread().name)
                    return@map it
                }
                .subscribeOn(Schedulers.computation())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.d("doOnSubscribe-1", Thread.currentThread().name)
                    }

                    override fun onNext(t: String) {

                    }

                    override fun onError(e: Throwable) {

                    }

                })
    }

    override fun loadData() {
        val list = arrayListOf<String>()
        list.add("提示控件")
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