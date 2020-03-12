package com.pandaq.app_launcher.ui.testlist

import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.pandaq.app_launcher.R
import com.pandaq.app_launcher.framework.AppBaseRefreshActivity
import com.pandaq.router.routers.RouterPath
import com.pandaq.uires.widget.recyclerview.RefreshRecyclerView
import kotlinx.android.synthetic.main.launcher_activity_list_test.*

/**
 * Created by huxinyu on 2020/2/28.
 * Email : panda.h@foxmail.com
 * Description :
 */
@Route(path = RouterPath.LAUNCH_ACTIVITY_TEST)
class TestListActivity : AppBaseRefreshActivity<TestListPresenter>(), ITestView {


    override fun injectPresenter(): TestListPresenter = TestListPresenter(this)

    override fun bindContentRes(): Int = R.layout.launcher_activity_list_test

    override fun initVariable() {

    }

    override fun initView() {
        rrv_data.setLayoutManager(LinearLayoutManager(this))
        mPresenter?.let {
            rrv_data.setAdapter(it.adapter)
            rrv_data.setOnRefreshLoadMoreListener(it.refreshLoadMoreListener)
        }
    }

    override fun loadData() {
        mPresenter?.loadData()
    }

    override fun bindRefresh(): RefreshRecyclerView? = rrv_data
}