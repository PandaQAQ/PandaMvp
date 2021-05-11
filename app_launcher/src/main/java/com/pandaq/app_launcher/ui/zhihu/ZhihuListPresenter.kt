package com.pandaq.app_launcher.ui.zhihu

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.pandaq.app_launcher.R
import com.pandaq.app_launcher.entites.Story
import com.pandaq.app_launcher.entites.Zhihu
import com.pandaq.app_launcher.framework.refresh.AppBaseRefreshPresenter
import com.pandaq.app_launcher.net.AppCallBack
import com.pandaq.appcore.imageloader.core.PicLoader
import com.pandaq.rxpanda.exception.ApiException
import com.pandaq.rxpanda.transformer.RxScheduler
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.launcher_item_zhihu.view.*

/**
 * Created by huxinyu on 5/11/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
class ZhihuListPresenter(view: IZhihuView) : AppBaseRefreshPresenter<IZhihuView>(view) {

    val zhihuAdapter by lazy {
        return@lazy object : BaseQuickAdapter<Story, BaseViewHolder>(R.layout.launcher_item_zhihu) {

            init {
                addChildClickViewIds(R.id.item_cardview)
            }

            override fun convert(holder: BaseViewHolder, item: Story) {
                holder.itemView.apply {
                    PicLoader.with(context)
                            .load(item.images!![0])
                            .into(this.news_image)
                    this.news_title.text = item.title
                }
            }
        }

    }

    val refreshLoadListener by lazy {
        object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                pageNo = 1
                loadStories()
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                loadStories()
            }
        }
    }

    private var pageNo = 1

    fun loadStories() {
        if (zhihuAdapter.data.isNullOrEmpty()) {
            mView?.showLoading()
        }
        api.zhihu()
                .doOnSubscribe {
                    addDisposable(it)
                }
                .compose(RxScheduler.sync())
                .subscribe(object : AppCallBack<Zhihu>() {
                    override fun success(data: Zhihu) {
                        data.stories?.let {
                            if (pageNo == 1) {
                                zhihuAdapter.setNewInstance(it)
                            } else {
                                zhihuAdapter.addData(it)
                            }
                        }
                        if (zhihuAdapter.data.isNullOrEmpty()) {
                            mView?.showEmpty()
                        } else {
                            mView?.showContent(pageNo < 3)
                            pageNo++
                        }
                    }

                    override fun fail(e: ApiException?) {
                        handelError(zhihuAdapter.data.isNullOrEmpty(), e)
                    }

                    override fun finish(success: Boolean) {

                    }

                })
    }
}