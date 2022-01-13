package com.pandaq.app_jetpack.app.entity

import com.google.gson.annotations.SerializedName
import com.pandaq.rxpanda.entity.IApiData

/**
 * Created by huxinyu on 2019/6/18.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
data class WanApiData<T>(@SerializedName("errorCode") private val code: String,
                         @SerializedName("errorMsg") private val msg: String,
                         @SerializedName("data") private val data: T) : IApiData<T> {
    override fun getCode(): String {
        return code
    }

    override fun getMsg(): String {
        return msg
    }

    override fun getData(): T {
        return data
    }

    override fun isSuccess(): Boolean {
        return code.toInt() == 0
    }

}