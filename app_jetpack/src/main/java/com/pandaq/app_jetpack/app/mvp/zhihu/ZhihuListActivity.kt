package com.pandaq.app_jetpack.app.mvp.zhihu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.pandaq.app_jetpack.R
import com.pandaq.app_jetpack.app.entity.ZhihuData
import com.pandaq.router.routers.RouterPath
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.a_activity_zhihu_list.*

/**
 * Created by huxinyu on 2020/3/30.
 * Email : panda.h@foxmail.com
 * Description :
 */
@Route(path = RouterPath.A_ACTIVITY_MAIN)
class ZhihuListActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(ZhihuViewModel::class.java)
    }

    private var date: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_activity_zhihu_list)
        viewModel.getDataList()
        viewModel.zhihuLiveData.observe(this, Observer<ZhihuData> {
            rrv_data.finishLoadMore(false)
            rrv_data.finishRefresh(true)
            tv_date.text = it.date
            date = it.date.toString()
        })
        rrv_data.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                viewModel.getHistory(date)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                viewModel.getDataList()
            }

        })
    }
}