package com.pandaq.appcore.utils

import android.Manifest
import android.widget.Toast
import com.pandaq.appcore.permission.RtPermission
import com.pandaq.appcore.service.UpdateService
import com.pandaq.appcore.utils.system.AppUtils
import java.io.PrintWriter
import java.util.concurrent.Executors

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
        fun downloadApk(downloader: UpdateService.DownloadBinder?, url: String?) {
            if (url.isNullOrEmpty()) {
                Toast.makeText(AppUtils.getContext(), "下载地址无效", Toast.LENGTH_SHORT).show()
                return
            }
            RtPermission.with(AppUtils.getContext())
                .runtime(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                .onDenied {
                    Toast.makeText(AppUtils.getContext(), "无存储权限，无法下载", Toast.LENGTH_SHORT).show()
                }
                .rationale { _, _, executor ->
                    executor.execute()
                }
                .onGranted {
                    downloader?.startDownload(url)
                }
                .start()

        }
    }
}