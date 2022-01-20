package com.pandaq.uires.mvp.core

/**
 * Created by huxinyu on 5/11/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
interface IListVIew : IView {

    /**
     * 显示内容页面,列表分页加载页面使用
     */
    fun showContent(hasMore: Boolean)

}