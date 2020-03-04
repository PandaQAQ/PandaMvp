package com.pandaq.appcore.utils

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import com.pandaq.appcore.permission.RtPermission
import com.pandaq.appcore.permission.SettingDialogUtils
import com.pandaq.appcore.permission.install.FileProvider
import java.io.File

/**
 * Created by huxinyu on 2020/3/4.
 * Email : panda.h@foxmail.com
 * Description :系统相机开启工具类
 */
class CameraUtils {


    companion object {
        private var PHOTO_PATH_NAME = ""
        private var defaultPath = "${Environment.getExternalStorageDirectory().absolutePath}/photo/IMAGE"

        fun open(activity: Activity, requestCode: Int) {
            RtPermission.with(activity)
                    .runtime(Manifest.permission.CAMERA
                            , Manifest.permission.WRITE_EXTERNAL_STORAGE
                            , Manifest.permission.READ_EXTERNAL_STORAGE)
                    .onAlwaysDenied {
                        SettingDialogUtils.showSetting(activity)
                    }
                    .rationale { _, _, executor ->
                        executor.execute()
                    }
                    .onGranted {
                        takePhoto(activity, requestCode)
                    }
                    .start()

        }

        private fun takePhoto(activity: Activity, requestCode: Int) {
            val dir = File(defaultPath)
            if (!dir.exists()) {
                dir.mkdirs()
            }
            if (dir.exists()) {
                PHOTO_PATH_NAME = dir.absolutePath + "/IMG_" + System.currentTimeMillis() + ".jpeg"
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                // 相机拍照后的照片存储的路径
                val imageUri: Uri
                imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    val authority: String = activity.packageName + ".file.path.share"
                    FileProvider.getUriForFile(activity, authority, File(PHOTO_PATH_NAME))
                } else {
                    Uri.fromFile(File(PHOTO_PATH_NAME))
                }
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                activity.startActivityForResult(intent, requestCode)
            } else {
                Toast.makeText(activity, "存储目录创建失败！", Toast.LENGTH_SHORT).show()
            }
        }

        /**
         * 获取拍摄的照片的路径
         */
        fun getPhotoPath(): String {
            return PHOTO_PATH_NAME
        }
    }

}