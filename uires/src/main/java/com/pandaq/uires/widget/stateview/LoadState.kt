package com.pandaq.uires.widget.stateview

/**
 * Created by huxinyu on 5/7/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
enum class LoadState(value: Int) {
    NORMAL(0), //正常状态
    LOADING(1), //加载中
    ERROR(2), //加载出错
    EMPTY(3), //暂无数据状态
    NO_NET(4) //网络异常状态
}