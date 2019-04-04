package com.pandaq.pandamvp.ui.home

import android.content.Intent
import android.os.Environment
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.pandaq.appcore.framework.mvp.BasePresenter
import com.pandaq.appcore.imageloader.core.PicLoader
import com.pandaq.appcore.permission.RtPermission
import com.pandaq.appcore.utils.system.FileUtils
import com.pandaq.commonui.msgwindow.Toaster
import com.pandaq.commonui.utils.DisplayUtils
import com.pandaq.commonui.widget.recyclerview.decoration.DividerDecoration
import com.pandaq.pandamvp.R
import com.pandaq.pandamvp.framework.AppBaseActivity
import com.pandaq.pandamvp.ui.functions.GalleryActivity
import kotlinx.android.synthetic.main.app_activity_home.*
import java.io.File

/**
 * Created by huxinyu on 2019/3/25.
 * Email : panda.h@foxmail.com
 * Description :
 */
class HomeActivity : AppBaseActivity<BasePresenter<*>>() {


    private val adapter: BaseQuickAdapter<String, BaseViewHolder> by lazy {
        return@lazy object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.app_item_homepage) {
            override fun convert(helper: BaseViewHolder?, item: String?) {
                helper?.let {
                    val itemView = it.getView<ConstraintLayout>(R.id.cl_container)
                    val icon = it.getView<ImageView>(R.id.iv_icon);
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

    override fun injectPresenter(): BasePresenter<*>? {
        return null
    }

    override fun bindContentRes(): Int {
        return R.layout.app_activity_home
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
        refreshList.addItemDecoration(DividerDecoration(DisplayUtils.dp2px(8f), 3))
        adapter.setOnItemChildClickListener { adapter, _, position ->
            when (position) {
                0 -> {
                    val intent = Intent(this, GalleryActivity::class.java)
                    startActivity(intent)
                }
                1 -> {
                    RtPermission.with(this)
                            .install(Environment.getExternalStoragePublicDirectory("panda")
                                    .absolutePath + File.separator + "panda.apk")
                            .onDenied {
                                Toaster.with(this)
                                        .msg("无应用安装权限！")
                                        .show()
                            }
                            .start()
                }
                3 -> {
                    FileUtils.getUri(this, "sdsadadas")
                }
                else -> {
                    Toaster.with(this)
                            .msg(adapter.data[position] as String)
                            .show()
                }
            }
        }
    }

    override fun loadData() {
        val list = arrayListOf<String>()
        list.add("提示控件")
        list.add("运行时权限")
        list.add("图片加载")
        list.add("网络请求")
        list.add("WebView")
        list.add("Dialogs")
        list.add("Pickers")
        list.add("向导遮罩")
        adapter.setNewData(list)
        refreshList.finishRefresh(true)
    }

}