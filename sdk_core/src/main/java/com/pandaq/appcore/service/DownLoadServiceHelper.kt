package com.pandaq.appcore.service.helper

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.pandaq.appcore.service.DownloadService

/**
 * Created by ZhaoShulin on 2018/6/9 下午2:47.
 * <br>
 * Desc:
 * <br>
 */
class DownLoadServiceHelper(var context: Activity, iDownloadService: IDownloadService?) {
    var mDownloadBinder: DownloadService.DownloadBinder? = null
    private var isBind = false
    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            mDownloadBinder = service as DownloadService.DownloadBinder

            //请求应用更新
            iDownloadService?.let { iDownloadService.onServiceConnected() }

        }

        override fun onServiceDisconnected(name: ComponentName) {
            mDownloadBinder = null
            iDownloadService?.let { iDownloadService.onServiceDisconnected() }

        }
    }

    fun bindService() {
        val intent = Intent(context, DownloadService::class.java)
        context.startService(intent)
        isBind = context.bindService(intent, mConnection, Activity.BIND_AUTO_CREATE)
    }

    fun unbindService() {
        if (isBind) {
            context.unbindService(mConnection)
        }
    }

}