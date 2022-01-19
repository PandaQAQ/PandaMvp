package com.pandaq.appcore.api

import com.google.gson.annotations.SerializedName

/**
 * Created by huxinyu on 2021/10/18.
 * Email : panda.h@foxmail.com
 * Description :
 */
data class Rows<T>(
    @SerializedName("rows") val rows: T
)