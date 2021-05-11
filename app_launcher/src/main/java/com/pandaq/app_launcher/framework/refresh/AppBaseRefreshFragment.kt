package com.pandaq.app_launcher.framework.refresh

import com.pandaq.app_launcher.framework.AppBaseFragment
import com.pandaq.app_launcher.framework.AppBasePresenter
import com.pandaq.appcore.framework.mvp.IListVIew
import com.pandaq.uires.widget.recyclerview.RefreshRecyclerView

/**
 * Created by huxinyu on 2019/7/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :模板类
 */
abstract class AppBaseRefreshFragment<P : AppBasePresenter<*>> : AppBaseFragment<P>(), IListVIew {

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