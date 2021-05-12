package com.pandaq.app_launcher.ui.home

import android.os.Environment
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.pandaq.app_launcher.R
import com.pandaq.app_launcher.framework.AppBaseActivity
import com.pandaq.app_launcher.framework.AppBasePresenter
import com.pandaq.appcore.framework.app.ActivityTask
import com.pandaq.appcore.imageloader.core.PicLoader
import com.pandaq.appcore.permission.RtPermission
import com.pandaq.appcore.utils.system.DisplayUtils
import com.pandaq.router.routers.RouterPath
import com.pandaq.uires.html.HtmlNoTitleActivity
import com.pandaq.uires.msgwindow.Toaster
import com.pandaq.uires.utils.compileSize
import com.pandaq.uires.widget.recyclerview.decoration.ItemDecoration
import kotlinx.android.synthetic.main.launcher_activity_home.*
import java.io.File
import kotlin.system.exitProcess

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

        mToolbar?.showMenu("测试")
        mToolbar?.setMenuClickListener(View.OnClickListener { Toaster.showSuccess("MenuClicked!") })

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
                    ARouter.getInstance()
                            .build(RouterPath.JETPACK_ZHIHU_LIST)
                            .navigation(this)
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
                    HtmlNoTitleActivity.start("http://122.51.187.156/#/")
                }

                3 -> {
                    ARouter.getInstance()
                            .build(RouterPath.ROUTE_404)
                            .navigation()
                }
                4 -> {
                    ARouter.getInstance()
                            .build(RouterPath.B_ACTIVITY_MAIN)
                            .navigation(this)
                }
                5 -> {
                    ARouter.getInstance()
                            .build(RouterPath.LAUNCH_ACTIVITY_ZHIHU)
                            .navigation(this)
                }
                else -> {
                    Toaster.showError(adapter.data[position] as String)
                }
            }
        }
    }

    override fun loadData() {
        val list = arrayListOf<String>()
        list.add("JetPack")
        list.add("运行时权限")
        list.add("WebView")
        list.add("跳转 A Module ")
        list.add("跳转 B Module")
        list.add("测试按钮")
        adapter.setNewData(list)
        refreshList.finishRefresh(true)
    }

    // 退出应用模板
    override fun onBackPressed() {
        MaterialDialog.Builder(this)
                .title(R.string.dialog_title_notice)
                .content("是否结束应用？")
                .positiveText("结束")
                .negativeText(R.string.text_cancel)
                .positiveColor(resources.getColor(R.color.colorAccent))
                .negativeColor(resources.getColor(R.color.colorAccent))
                .onNegative { dialog, _ -> dialog.dismiss() }
                .onPositive { dialog, _ ->
                    dialog.dismiss()
                    // 情况 activity 和 task 避免 exit 后创建新栈
                    ActivityTask.getInstance().killAllActivity()
                    finishAffinity()
                    // 延迟退出避免动画未结束闪屏
                    cl_container.postDelayed({ exitProcess(0) }, 500)
                }
                .build()
                .compileSize()
                .show()
    }
}