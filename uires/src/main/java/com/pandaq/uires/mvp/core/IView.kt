package com.pandaq.uires.mvp.core

/**
 * Created by huxinyu on 2018/9/14.
 * Email : panda.h@foxmail.com
 *
 *
 * Description :V 层基础接口实现
 *
 *
 * module 中让 Mvp 的 baseView 实现此接口（app 中将给出实现模板）
 */
interface IView {

    /**
     * 显示加载 Loading
     */
    fun dialogLoading(cancelAble: Boolean = true, msg: String? = null)

    /**
     * 显示加载 Loading
     */
    fun dialogLoadingWithCover(msg: String? = null)

    /**
     * 出错回调
     *
     * @param showErrorPage 是否展示错误页面
     * @param errMsg        错误信息
     */
    fun showError(showErrorPage: Boolean, errMsg: String? = null)

    /**
     * 显示空页面
     */
    fun showEmpty(msg: String? = null)

    /**
     * 显示加载中页面
     */
    fun showLoading()

    /**
     * 显示内容页面，非分页加载使用
     */
    fun showContent()
}