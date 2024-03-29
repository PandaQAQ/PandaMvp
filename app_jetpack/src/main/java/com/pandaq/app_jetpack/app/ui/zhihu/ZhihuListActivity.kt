package com.pandaq.app_jetpack.app.ui.zhihu

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.pandaq.app_jetpack.app.ui.adapters.ZhihuNewsAdapter
import com.pandaq.app_jetpack.databinding.AActivityZhihuListBinding
import com.pandaq.appcore.R
import com.pandaq.router.route.RouterPath
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener

/**
 * Created by huxinyu on 2020/3/30.
 * Email : panda.h@foxmail.com
 * Description :
 */
@Route(path = RouterPath.JETPACK_ZHIHU_LIST)
class ZhihuListActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(ZhihuViewModel::class.java)
    }

    private var date: String = ""
    private var isRefresh = true
    private lateinit var binding: AActivityZhihuListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
        )
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        // Translucent status bar
        window.statusBarColor = resources.getColor(R.color.toolbarBackground)
        // Translucent navigation bar
        window.navigationBarColor = Color.TRANSPARENT
        binding = AActivityZhihuListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = ZhihuNewsAdapter()
        binding.rrvData.setLayoutManager(LinearLayoutManager(this))
        binding.rrvData.setAdapter(adapter)

        viewModel.getDataList()

        viewModel.zhihuLiveData.observe(this, Observer {
            it?.let {
                date = it.date.toString()
                if (isRefresh) {
                    binding.rrvData.finishRefresh(true)
                    adapter.setNewInstance(it.stories)
                } else {
                    binding.rrvData.finishLoadMore(false)
                    adapter.addData(it.stories)
                }
            }
        })

        val data = viewModel.zhihuLiveData.value
        data?.stories = mutableListOf()
        viewModel.zhihuLiveData.postValue(data)

        binding.rrvData.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                isRefresh = false
                viewModel.getHistory(date)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                isRefresh = true
                viewModel.getDataList()
            }

        })
    }


}