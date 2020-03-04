package com.pandaq.appcore.browser.bridge

/**
 * Created by huxinyu on 2020/3/3.
 * Email : panda.h@foxmail.com
 * Description :
 */

/**
 * @param methodName 调用 JS 的方法名
 * @param data 回调返回的数据
 */
data class BridgeData(val methodName: String?,
                      val data: Any?)