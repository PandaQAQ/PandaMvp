package com.pandaq.app_jetpack.app.ui.zhihu

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.pandaq.app_jetpack.R
import com.pandaq.app_jetpack.app.TestTask
import com.pandaq.app_jetpack.app.ui.adapters.ZhihuNewsAdapter
import com.pandaq.router.routers.RouterPath
import com.pandaq.uires.msgwindow.Toaster
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.a_activity_zhihu_list.*
import java.util.concurrent.Executors

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

//    private val activityLauncher =
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
//                if (result?.resultCode == Activity.RESULT_OK) {
//                    Toaster.showSuccess(result.data?.getStringExtra("data"))
//                }
//            }
//    private val requestPermission =
//            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
//                Toaster.showSuccess("权限回调")
//            }

    private var date: String = ""
    private var isRefresh = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_activity_zhihu_list)
        val adapter = ZhihuNewsAdapter()
        rrv_data.setLayoutManager(LinearLayoutManager(this))
        rrv_data.setAdapter(adapter)

        viewModel.getDataList()

        viewModel.zhihuLiveData.observe(this, Observer {
            date = it.date.toString()
            if (isRefresh) {
                rrv_data.finishRefresh(true)
                adapter.setNewInstance(it.stories)
            } else {
                rrv_data.finishLoadMore(false)
                adapter.addData(it.stories)
            }
        })
        rrv_data.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                isRefresh = false
                viewModel.getHistory(date)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                isRefresh = true
                viewModel.getDataList()
            }

        })

        Executors.newFixedThreadPool(5)

        adapter.setOnItemChildClickListener { _, _, _ ->
//            val intent = Intent(this@ZhihuListActivity, MainActivity::class.java)
//            activityLauncher.launch(intent)

//            requestPermission.launch(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE))

            val task  = TestTask()
            task.execute("哈哈哈")
        }
    }


}