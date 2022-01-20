package com.pandaq.app_launcher.ui.zhihu

import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.pandaq.app_launcher.databinding.LauncherActivityZhihuListBinding
import com.pandaq.app_launcher.framework.refresh.AppBaseRefreshActivity
import com.pandaq.router.route.RouterPath
import com.pandaq.uires.widget.recyclerview.RefreshRecyclerView

/**
 * Created by huxinyu on 5/11/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
@Route(path = RouterPath.LAUNCH_ACTIVITY_ZHIHU)
class ZhihuListActivity : AppBaseRefreshActivity<ZhihuListPresenter,LauncherActivityZhihuListBinding>(), IZhihuView {

    override fun initVariable() {

    }

    override fun initView() {
        setStateLayout(binding.stateLayout)
    }

    override fun loadData() {
        binding.rrvData.setLayoutManager(LinearLayoutManager(this))
        mPresenter?.let {
            binding.rrvData.setAdapter(it.zhihuAdapter)
            it.loadStories()
            binding.rrvData.setOnRefreshLoadMoreListener(it.refreshLoadListener)
            binding.rrvData.setEnableLoadMore(true)
        }
    }

    override fun bindRefresh(): RefreshRecyclerView? = binding.rrvData

}