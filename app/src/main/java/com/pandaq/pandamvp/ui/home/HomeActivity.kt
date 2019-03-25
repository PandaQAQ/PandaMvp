package com.pandaq.pandamvp.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.pandaq.appcore.framework.mvp.BasePresenter
import com.pandaq.pandamvp.R
import com.pandaq.pandamvp.framework.AppBaseActivity
import kotlinx.android.synthetic.main.app_activity_home.*
import kotlinx.android.synthetic.main.app_item_homepage.*

/**
 * Created by huxinyu on 2019/3/25.
 * Email : panda.h@foxmail.com
 * Description :
 */
class HomeActivity : AppBaseActivity<BasePresenter<*>>() {


    private val adapter: BaseQuickAdapter<String, BaseViewHolder> by lazy {
        return@lazy object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.app_item_homepage) {
            override fun convert(helper: BaseViewHolder?, item: String?) {
                helper?.setText(R.id.tv_name,item)
            }
        }
    }


    override fun injectPresenter(): BasePresenter<*>? {
        return null
    }

    override fun bindContentRes(): Int {
        return R.layout.app_activity_home
    }

    override fun initVariable() {
    }

    override fun initView() {
        refreshList.setLayoutManager(StaggeredGridLayoutManager(3, RecyclerView.VERTICAL))
        refreshList.setAdapter(adapter)
        refreshList.setOnRefreshListener {
            loadData()
        }
        refreshList.setEnableLoadMore(false)
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