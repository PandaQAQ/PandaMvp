package com.pandaq.partbuilding.base

import com.pandaq.app_launcher.ui.framework.AppBaseFragment
import com.pandaq.app_launcher.ui.framework.AppBasePresenter
import com.pandaq.uires.widget.recyclerview.RefreshRecyclerView

/**
 * Created by huxinyu on 2019/7/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
abstract class AppBaseRefreshFragment<P : AppBasePresenter<*>> : AppBaseFragment<P>() {

    abstract fun bindRefresh(): RefreshRecyclerView?

    override fun onLoadFinish(success: Boolean) {
        hideLoading()
        bindRefresh()?.finishRefresh(!success)
        bindRefresh()?.finishLoadMore(!success)
    }
}