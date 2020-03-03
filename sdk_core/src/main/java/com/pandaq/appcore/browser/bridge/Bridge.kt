package com.pandaq.appcore.browser.bridge

/**
 * Created by huxinyu on 2020/3/3.
 * Email : panda.h@foxmail.com
 * Description :
 */

/**
 * @param methodName 调用 JS 的方法名
 * @param callbackId 回调的 Id 用于对应调用与回调
 * @param data 回调返回的数据
 */
data class Bridge(val methodName: String,
                  var callbackId: Int = 0,
                  val data: String = "{}")