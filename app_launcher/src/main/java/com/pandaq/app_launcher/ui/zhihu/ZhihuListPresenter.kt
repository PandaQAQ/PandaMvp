package com.pandaq.app_launcher.ui.zhihu

import com.pandaq.app_launcher.R
import com.pandaq.app_launcher.databinding.LauncherItemZhihuBinding
import com.pandaq.app_launcher.entites.Story
import com.pandaq.app_launcher.entites.Zhihu
import com.pandaq.app_launcher.framework.refresh.AppBaseRefreshPresenter
import com.pandaq.app_launcher.net.AppCallBack
import com.pandaq.appcore.imageloader.core.PicLoader
import com.pandaq.rxpanda.exception.ApiException
import com.pandaq.rxpanda.transformer.RxScheduler
import com.pandaq.uires.widget.recyclerview.BindingQuickAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener

/**
 * Created by huxinyu on 5/11/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
class ZhihuListPresenter : AppBaseRefreshPresenter<IZhihuView>() {

    val zhihuAdapter by lazy {
        return@lazy object : BindingQuickAdapter<Story, LauncherItemZhihuBinding>() {

            init {
                addChildClickViewIds(R.id.item_cardview)
            }

            override fun convert(holder: BindingHolder<LauncherItemZhihuBinding>, item: Story) {
                holder.binding.let {
                    PicLoader.with(context)
                            .load(item.images!![0])
                            .into(it.newsImage)
                    it.newsTitle.text = item.title
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

                    }

                    override fun finish(success: Boolean) {

                    }

                })
    }
}