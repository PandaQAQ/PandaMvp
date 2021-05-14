package com.pandaq.appcore.utils

import android.content.Context
import android.net.ConnectivityManager
import com.pandaq.appcore.utils.system.AppUtils

/**
 * Created by huxinyu on 5/10/21.
 * Email : panda.h@foxmail.com
 * Description :网络连接工具类
 */
class NetWorkUtils {

    companion object {
        fun isNetworkConnected(): Boolean {
            val manager = AppUtils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networks = manager.allNetworks
            networks.forEach {
                val info = manager.getNetworkInfo(it)
                return info?.isConnected ?: false
            }
            return false
        }
    }

}