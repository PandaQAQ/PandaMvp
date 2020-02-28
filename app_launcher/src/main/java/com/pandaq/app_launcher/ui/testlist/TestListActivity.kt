package com.pandaq.app_launcher.ui.testlist

import com.pandaq.app_launcher.R
import com.pandaq.app_launcher.framework.AppBaseRefreshActivity
import com.pandaq.uires.widget.recyclerview.RefreshRecyclerView
import kotlinx.android.synthetic.main.launcher_activity_list_test.*

/**
 * Created by huxinyu on 2020/2/28.
 * Email : panda.h@foxmail.com
 * Description :
 */
class TestListActivity : AppBaseRefreshActivity<TestListPresenter>(), ITestView {


    override fun injectPresenter(): TestListPresenter = TestListPresenter(this)

    override fun bindContentRes(): Int = R.layout.launcher_activity_list_test

    override fun initVariable() {

    }

    override fun initView() {

    }

    override fun loadData() {

    }

    override fun bindRefresh(): RefreshRecyclerView? = rrv_data
}