package com.pandaq.app_launcher.framework

import com.pandaq.appcore.framework.mvp.IMvpListView
import com.pandaq.uires.widget.recyclerview.RefreshRecyclerView


/**
 * Created by huxinyu on 2019/7/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :模板类
 */
abstract class AppBaseRefreshActivity<P : AppBasePresenter<*>> : AppBaseActivity<P>(), IMvpListView {

    abstract fun bindRefresh(): RefreshRecyclerView?

    override fun onFinish(success: Boolean) {
        // 隐藏加载进度条
        hideLoading()
        // 结束加载头
        bindRefresh()?.finishRefresh(success)
    }

    override fun hasMore(hasMore: Boolean) {
        bindRefresh()?.finishLoadMore(!hasMore)
    }
}