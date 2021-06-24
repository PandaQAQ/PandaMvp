package com.pandaq.appcore.browser

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.pandaq.appcore.utils.log.PLogger
import com.tencent.smtt.sdk.TbsReaderView
import com.tencent.smtt.sdk.ValueCallback
import java.io.File

/**
 * Created by huxinyu on 6/18/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
class PandaReaderView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), TbsReaderView.ReaderCallback, ValueCallback<String> {

    private val mTbsReaderView: TbsReaderView = TbsReaderView(context, this)

    init {
        this.addView(
                mTbsReaderView,
                LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        )
        mTbsReaderView.isForceDarkAllowed = false
    }

    /**
     * 浏览文件
     */
    fun displayFile(file: File) {
        val extensionName = getFileType(file.absolutePath)
        val bundle = Bundle()
        bundle.putString(TbsReaderView.KEY_FILE_PATH, file.absolutePath)
        bundle.putString(TbsReaderView.KEY_TEMP_PATH, context.cacheDir.absolutePath)
        val canOpen = mTbsReaderView.preOpen(extensionName, false)
        if (canOpen) {
            mTbsReaderView.openFile(bundle)
        }
    }

    /**
     * 浏览文件
     */
    fun displayFile(filePath: String) {
        if (!File(filePath).exists()) {
            PLogger.e("File $filePath not exists")
            return
        }
        displayFile(File(filePath))
    }

    fun stopDisplay(){
        mTbsReaderView.onStop()
    }

    override fun onCallBackAction(p0: Int?, p1: Any?, p2: Any?) {

    }

    override fun onReceiveValue(p0: String?) {

    }

    private fun getFileType(filename: String): String {
        if (filename.isNotEmpty()) {
            val dot = filename.lastIndexOf('.')
            if (dot > -1 && dot < filename.length - 1) {
                return filename.substring(dot + 1)
            }
        }
        return ""
    }

}