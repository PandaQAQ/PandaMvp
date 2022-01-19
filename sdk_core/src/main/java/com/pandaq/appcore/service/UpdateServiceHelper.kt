package com.pandaq.appcore.service

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder

class UpdateServiceHelper(var context: Context, iUpdateService: IUpdateService?) {
    var mUpdateBinder: UpdateService.DownloadBinder? = null
    private var isBind = false
    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            mUpdateBinder = service as UpdateService.DownloadBinder

            //请求应用更新
            iUpdateService?.let { iUpdateService.onServiceConnected() }

        }

        override fun onServiceDisconnected(name: ComponentName) {
            mUpdateBinder = null
            iUpdateService?.let { iUpdateService.onServiceDisconnected() }

        }
    }

    fun bindService() {
        val intent = Intent(context, UpdateService::class.java)
        context.startService(intent)
        isBind = context.bindService(intent, mConnection, Activity.BIND_AUTO_CREATE)
    }

    fun unbindService() {
        if (isBind) {
            context.unbindService(mConnection)
        }
    }

}