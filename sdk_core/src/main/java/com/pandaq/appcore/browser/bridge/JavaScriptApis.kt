package com.pandaq.appcore.browser.bridge

import android.webkit.JavascriptInterface
import com.pandaq.appcore.framework.app.ActivityTask
import com.pandaq.appcore.utils.CameraUtils

/**
 * Created by huxinyu on 2020/3/4.
 * Email : panda.h@foxmail.com
 * Description : JavaScript 交互接口类
 *
 * 同步回调时直接返回数据，异步回调时将回调的方法名保存到 jsMethodsNames 中，有回调结果后调用
 * 根据 code 取出方法名，组装数据调用 webView?.sendDataToH5() 将值回传给 Js
 */
class JavaScriptApis {

    companion object {

        val jsMethodsNames = hashMapOf<Int, String>()

        // 拍照回调 id
        const val CALLBACK_CODE_TAKE_PHOTO:Int = 0x0001
        //  原生获取数据回调 id
        const val CALLBACK_CODE_GETDATA:Int = 0x0002

    }

    /**
     * 结束当前 Activity
     */
    @JavascriptInterface
    fun back() {
        ActivityTask.getInstance().currentActivity().finish()
    }

    /**
     * takePhoto
     *
     * @param callbackName 回调方法名称
     */
    @JavascriptInterface
    fun takePhoto(callbackName: String) {
        jsMethodsNames[CALLBACK_CODE_TAKE_PHOTO] = callbackName
        CameraUtils.open(ActivityTask.getInstance().currentActivity(), CALLBACK_CODE_TAKE_PHOTO)
    }

}