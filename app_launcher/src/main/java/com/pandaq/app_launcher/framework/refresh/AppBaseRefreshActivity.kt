package com.pandaq.app_launcher.framework.refresh

import androidx.viewbinding.ViewBinding
import com.pandaq.app_launcher.framework.AppBaseActivity
import com.pandaq.uires.mvp.core.IListVIew
import com.pandaq.uires.widget.recyclerview.RefreshRecyclerView


/**
 * Created by huxinyu on 2019/7/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :模板类
 */
abstract class AppBaseRefreshActivity<P : AppBaseRefreshPresenter<*>,VB:ViewBinding> : AppBaseActivity<P,VB>(),
    IListVIew {

    abstract fun bindRefresh(): RefreshRecyclerView?

    override fun showLoading() {
        super.showLoading()
        // 结束加载头
        bindRefresh()?.finishRefresh(true)
    }

    override fun showContent(hasMore: Boolean) {
        super.showContent()
        // 结束加载头
        bindRefresh()?.finishRefresh(true)
        // 结束加载更多
        bindRefresh()?.finishLoadMore(!hasMore)
    }

    override fun showEmpty(msg: String?) {
        super.showEmpty(msg)
        // 结束加载头
        bindRefresh()?.finishRefresh(true)
    }

    override fun showError(showErrorPage: Boolean, errMsg: String?) {
        super.showError(showErrorPage, errMsg)
        // 结束加载头
        bindRefresh()?.finishRefresh(false)
    }
}