package com.pandaq.appcore.utils

import android.Manifest
import android.util.Log
import android.widget.Toast
import com.pandaq.appcore.permission.RtPermission
import com.pandaq.appcore.service.DownloadService
import com.pandaq.appcore.utils.system.AppUtils
import java.io.PrintWriter
import java.lang.Exception

/**
 * Created by huxinyu on 2019/9/25.
 * Email : panda.h@foxmail.com
 * Description :
 */
class UpdateUtils {
    companion object {
        /**
         * 使用系统的 Downloader 下载应用
         */
        fun downloadApk(downloader: DownloadService.DownloadBinder?, url: String?) {
            if (url.isNullOrEmpty()) {
                Toast.makeText(AppUtils.getContext(), "下载地址无效", Toast.LENGTH_SHORT).show()
                return
            }
            RtPermission.with(AppUtils.getContext())
                    .runtime(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                    .onDenied { Toast.makeText(AppUtils.getContext(), "无存储权限，无法下载", Toast.LENGTH_SHORT).show() }
                    .rationale { _, _, executor ->
                        executor.execute()
                    }
                    .onGranted {
                        downloader?.startDownload(url)
                    }
                    .start()

        }

        /**
         * 静默安装应用
         */
        fun installSilence(apkPath:String):Boolean {
            if (hasRootPermission()) {
                val printWriter: PrintWriter?
                var process: Process? = null
                try {
                    process = Runtime.getRuntime().exec("su")
                    printWriter = PrintWriter(process.outputStream)
                    printWriter.println("chmod 777 $apkPath")
                    printWriter.println("export LD_LIBRARY_PATH=/vendor/lib:/system/lib")
                    printWriter.println("pm install -r $apkPath")
                    printWriter.println("exit")
                    printWriter.flush()
                    printWriter.close()
                    return process.waitFor() == 0
                } catch (e: Exception) {
                    process?.destroy()
                }
                return false
            } else {
                Log.e("Installer", "has no permission to install silence!")
                return false
            }
        }

        fun uninstallSilence(packageName:String):Boolean{
            if (hasRootPermission()) {
                val printWriter: PrintWriter?
                var process: Process? = null
                try {
                    process = Runtime.getRuntime().exec("su")
                    printWriter = PrintWriter(process.outputStream)
                    printWriter.println("export LD_LIBRARY_PATH=/vendor/lib:/system/lib")
                    printWriter.println("pm uninstall -r $packageName")
                    printWriter.println("exit")
                    printWriter.flush()
                    printWriter.close()
                    return process.waitFor() == 0
                } catch (e: Exception) {
                    process?.destroy()
                }
                return false
            } else {
                Log.e("Installer", "has no permission to install silence!")
                return false
            }
        }

        /**
         * 判断当前应用是否有 root 权限，是否为系统签名应用
         */
        private fun hasRootPermission(): Boolean {
            val printWriter: PrintWriter?
            var process: Process? = null
            try {
                process = Runtime.getRuntime().exec("su")
                printWriter = PrintWriter(process.outputStream)
                printWriter.flush()
                printWriter.close()
                return process.waitFor() == 0
            } catch (e: Exception) {
                process?.destroy()
            }
            return false
        }
    }
}