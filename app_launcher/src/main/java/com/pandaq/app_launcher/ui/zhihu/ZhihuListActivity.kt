package com.pandaq.app_launcher.ui.zhihu

import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.pandaq.app_launcher.R
import com.pandaq.app_launcher.framework.refresh.AppBaseRefreshActivity
import com.pandaq.router.routers.RouterPath
import com.pandaq.uires.widget.recyclerview.RefreshRecyclerView
import kotlinx.android.synthetic.main.launcher_activity_zhihu_list.*

/**
 * Created by huxinyu on 5/11/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
@Route(path = RouterPath.LAUNCH_ACTIVITY_ZHIHU)
class ZhihuListActivity : AppBaseRefreshActivity<ZhihuListPresenter>(), IZhihuView {

    override fun bindContentRes(): Int = R.layout.launcher_activity_zhihu_list

    override fun initVariable() {

    }

    override fun initView() {
        initStateLayout(state_layout)
    }

    override fun loadData() {
        rrv_data.setLayoutManager(LinearLayoutManager(this))
        mPresenter?.let {
            rrv_data.setAdapter(it.zhihuAdapter)
            it.loadStories()
            rrv_data.setOnRefreshLoadMoreListener(it.refreshLoadListener)
            rrv_data.setEnableLoadMore(true)
        }
    }

    override fun bindRefresh(): RefreshRecyclerView? = rrv_data

}