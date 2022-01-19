package com.pandaq.appcore.log.bugly

import com.tencent.bugly.crashreport.CrashReport

/**
 * Created by huxinyu on 2021/12/16.
 * Email : panda.h@foxmail.com
 * Description :
 */
class CrashCallback : CrashReport.CrashHandleCallback() {

    override fun onCrashHandleStart(
        crashType: Int,
        errorType: String?,
        errorMessage: String?,
        errorStack: String?
    ): MutableMap<String, String> {
        //todo 错误日志增加网络状态、存储空间等数据
        val logMap = hashMapOf<String, String>()
        return super.onCrashHandleStart(crashType, errorType, errorMessage, errorStack)
    }

    override fun onCrashHandleStart2GetExtraDatas(
        crashType: Int,
        errorType: String?,
        errorMessage: String?,
        errorStack: String?
    ): ByteArray {
        return super.onCrashHandleStart2GetExtraDatas(
            crashType,
            errorType,
            errorMessage,
            errorStack
        )
    }

}