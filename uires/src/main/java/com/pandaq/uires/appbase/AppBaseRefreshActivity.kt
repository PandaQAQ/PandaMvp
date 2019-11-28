package com.pandaq.uires.appbase

import com.pandaq.uires.widget.recyclerview.RefreshRecyclerView


/**
 * Created by huxinyu on 2019/7/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :模板类
 */
abstract class AppBaseRefreshActivity<P : AppBasePresenter<*>> : AppBaseActivity<P>() {

    abstract fun bindRefresh(): RefreshRecyclerView?

    override fun onLoadFinish(success: Boolean) {
        hideLoading()
        bindRefresh()?.finishRefresh(!success)
        bindRefresh()?.finishLoadMore(!success)
    }
}