package com.pandaq.app_launcher.ui.testlist

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.pandaq.app_launcher.R
import com.pandaq.app_launcher.framework.AppBasePresenter
import com.pandaq.app_launcher.net.AppCallBack
import com.pandaq.rxpanda.exception.ApiException
import com.pandaq.rxpanda.transformer.RxScheduler
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by huxinyu on 2020/2/28.
 * Email : panda.h@foxmail.com
 * Description :
 */
class TestListPresenter(view: ITestView) : AppBasePresenter<ITestView>(view) {

    private var page = 0

    val refreshLoadMoreListener by lazy {
        object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                loadData()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 0
                loadData()
            }

        }
    }

    val adapter by lazy {
        object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.res_item_list_select_popup) {
            override fun convert(holder: BaseViewHolder, item: String) {
                holder.setText(R.id.tv_item, item)
            }
        }
    }


    fun loadData() {
        mView?.showLoading()
        Observable.just("")
                .map {
                    return@map arrayListOf("position_0", "position_1", "position_2", "position_3",
                            "position_4", "position_5", "position_6", "position_7", "position_8",
                            "position_9", "position_10", "position_11", "position_12", "position_13")
                }
                .delay(3, TimeUnit.SECONDS)
                .compose(RxScheduler.sync())
                .doOnSubscribe(this::addDisposable)
                .subscribe(object : AppCallBack<MutableList<String>>() {
                    override fun success(data: MutableList<String>) {
                        if (page == 0) {
                            adapter.setNewData(data)
                        } else {
                            adapter.addData(data)
                        }
                        mView?.hasMore(page < 3)
                        page++
                    }

                    override fun finish(success: Boolean) {
                        mView?.onFinish(success)
                    }

                    override fun fail(e: ApiException?) {
                        handelError(e)
                    }

                })
    }

}
