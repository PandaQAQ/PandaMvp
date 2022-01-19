package com.pandaq.appcore.framework.app.lifecycleimpl

import android.annotation.SuppressLint
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.Uri
import android.os.Build
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.pandaq.appcore.event.NetworkChange
import com.pandaq.appcore.event.NetworkState
import com.pandaq.appcore.framework.app.lifecycle.IAppLifeCycle
import com.pandaq.appcore.framework.app.lifecycle.ManifestParser
import com.pandaq.appcore.log.PLogger
import com.pandaq.appcore.utils.system.AppUtils
import com.pandaq.rxpanda.utils.ThreadUtils
import com.tencent.smtt.export.external.TbsCoreSettings
import com.tencent.smtt.sdk.QbSdk
import io.reactivex.plugins.RxJavaPlugins
import org.greenrobot.eventbus.EventBus
import java.lang.reflect.Method
import java.util.*

/**
 * Created by huxinyu on 2018/12/25.
 * Email : panda.h@foxmail.com
 *
 *
 * Description :基础 module 中的 Application 生命周期实现类除了实现 module 自身生命参数初始化还需要扫描
 * Manifest 文件，注入其他 module 的生命周期方法
 */
class AppProxy(private val application: Application) : IAppLifeCycle {


    private var mAppLifeCycles: List<IAppLifeCycle>? = ArrayList()
    private var mActivityLifeCycles: MutableList<ActivityLifecycleCallbacks>? = ArrayList()
    private var mFragmentLifecycleCallbacks: List<FragmentManager.FragmentLifecycleCallbacks>? =
        ArrayList()

    init {
        AppUtils.init(application)
        val injectors = ManifestParser(
            application
        ).parse()
        for (injector in injectors) {
            // add other module's callback to callback list
            injector.injectAppLifeCycle(application, mAppLifeCycles)
            // add other module's callback to callback list
            injector.injectActivityLifeCycle(application, mActivityLifeCycles)
            // add other module's callback to callback list
            injector.injectFragmentLifeCycle(application, mFragmentLifecycleCallbacks)
        }
        // register fragment lifecycle callbacks
        mActivityLifeCycles!!.add(DefaultActivityLifecycle(mFragmentLifecycleCallbacks))
    }

    // 网络状态监听
    private val networkCallback: NetworkCallback = object : NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            // 延迟通知刷新界面，让需要网络初始化的 SDK 先初始化
            ThreadUtils.getMainHandler().postDelayed({
                EventBus.getDefault().postSticky(NetworkChange(NetworkState.CONNECTED))
            }, 5000)
        }

        override fun onLosing(network: Network, maxMsToLive: Int) {
            super.onLosing(network, maxMsToLive)
            EventBus.getDefault().postSticky(NetworkChange(NetworkState.LOSING))
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            EventBus.getDefault().postSticky(NetworkChange(NetworkState.LOST))
        }

        override fun onUnavailable() {
            super.onUnavailable()
            EventBus.getDefault().postSticky(NetworkChange(NetworkState.LOST))
        }
    }

    override fun attachBaseContext(base: Context) {
        for (appLifeCycle in mAppLifeCycles!!) {
            appLifeCycle.attachBaseContext(base)
        }
    }

    override fun onCreate(application: Application) {
        openCrashProtected()
        // 监听网络变化，自动连接
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val connectivityManager =
                application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        }
        for (appLifeCycle in mAppLifeCycles!!) {
            appLifeCycle.onCreate(application)
        }
        for (callbacks in mActivityLifeCycles!!) {
            application.registerActivityLifecycleCallbacks(callbacks)
        }
        hookWebView()
        // 全局捕获 RxJava 的异常，避免因取消订阅，未捕获异常等导致的闪退
        RxJavaPlugins.setErrorHandler { e: Throwable? -> PLogger.e(e) }
        initSdk()
    }

    private fun initSdk() {
        // 初始化阿里云日志服务
//        SLSLoger.init()

        // 初始化 bugly
//        CrashReport.initCrashReport(application, "2449c506df", BuildConfig.IN_DEBUG)
//        val strategy = UserStrategy(application)
//        strategy.setCrashHandleCallback(CrashCallback())
        // 联网后延迟 30 秒上传信息
//        strategy.appReportDelay = 30000
//        strategy.appVersion = AppUtils.versionName()
//        strategy.isReplaceOldChannel = true
//        strategy.isEnableUserInfo = true
//        CrashReport.setUserId(AppUtils.getMacAddress() + "_" + BuildConfig.BUILD_TYPE)
//        CrashReport.setDeviceId(application, AppUtils.getMacAddress())
//        CrashReport.setIsDevelopmentDevice(application, BuildConfig.IN_DEBUG)
//        if ("DEBUG" == BuildConfig.ENV) {
//            CrashReport.setUserSceneTag(application, 208970)
//        } else if ("TEST" == BuildConfig.ENV) {
//            CrashReport.setUserSceneTag(application, 208971)
//        } else if ("RELEASE" == BuildConfig.ENV) {
//            CrashReport.setUserSceneTag(application, 208972)
//        }

        // 初始化百度地图
//        SDKInitializer.initialize(application)
//        SDKInitializer.setCoordType(CoordType.BD09LL)
        //配置 X5 浏览服务
        // tbs 配置
        QbSdk.setDownloadWithoutWifi(true)
        QbSdk.initX5Environment(AppUtils.getContext(), null)
        val setting = HashMap<String, Any>()
        setting[TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER] = true
        setting[TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE] = true
        QbSdk.initTbsSettings(setting)
    }

    override fun onTerminate(application: Application) {
        if (mAppLifeCycles != null) {
            for (appLifeCycle in mAppLifeCycles!!) {
                appLifeCycle.onTerminate(application)
            }
        }
        if (mActivityLifeCycles != null) {
            for (callbacks in mActivityLifeCycles!!) {
                application.unregisterActivityLifecycleCallbacks(callbacks)
            }
        }
        mAppLifeCycles = null
        mActivityLifeCycles = null
        mFragmentLifecycleCallbacks = null
        AppUtils.release()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val connectivityManager =
                application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }

    @SuppressLint("PrivateApi")
    private fun hookWebView() {
        val tag = "hookwebview"
        val sdkInt = Build.VERSION.SDK_INT
        try {
            val factoryClass = Class.forName("android.webkit.WebViewFactory")
            val field = factoryClass.getDeclaredField("sProviderInstance")
            field.isAccessible = true
            var sProviderInstance = field[null]
            if (sProviderInstance != null) {
                PLogger.i(tag, "sProviderInstance isn't null")
                return
            }
            val getProviderClassMethod = if (sdkInt > 22) {
                factoryClass.getDeclaredMethod("getProviderClass")
            } else if (sdkInt == 22) {
                factoryClass.getDeclaredMethod("getFactoryClass")
            } else {
                PLogger.i(tag, "Don't need to Hook WebView")
                return
            }
            getProviderClassMethod.isAccessible = true
            val factoryProviderClass = getProviderClassMethod.invoke(factoryClass) as Class<*>
            val delegateClass = Class.forName("android.webkit.WebViewDelegate")
            val delegateConstructor = delegateClass.getDeclaredConstructor()
            delegateConstructor.isAccessible = true
            if (sdkInt < 26) { //低于Android O版本
                val providerConstructor = factoryProviderClass.getConstructor(delegateClass)
                providerConstructor.isAccessible = true
                sProviderInstance =
                    providerConstructor.newInstance(delegateConstructor.newInstance())
            } else {
                val chromiumMethodName =
                    factoryClass.getDeclaredField("CHROMIUM_WEBVIEW_FACTORY_METHOD")
                chromiumMethodName.isAccessible = true
                val chromiumMethodNameStr = chromiumMethodName[null] as String
                val staticFactory: Method? =
                    factoryProviderClass.getMethod(chromiumMethodNameStr, delegateClass)
                sProviderInstance = staticFactory?.invoke(null, delegateConstructor.newInstance())
            }
            if (sProviderInstance != null) {
                field["sProviderInstance"] = sProviderInstance
                PLogger.i(tag, "Hook success!")
            } else {
                PLogger.i(tag, "Hook failed!")
            }
        } catch (e: Exception) {
            PLogger.e(tag, e)
        }
    }

    private fun openCrashProtected() {
        ThreadUtils.getMainHandler().post {
            while (true) {
                try {
                    Looper.loop()
                } catch (e: Throwable) {
                    Toast.makeText(application, "因发生异常情况，应用已重启", Toast.LENGTH_SHORT).show()
//                    CrashReport.postCatchedException(e)
                    // 遇到如下常见异常时，重启应用
                    if (e is NullPointerException || e is ClassCastException
                        || e is IndexOutOfBoundsException || e is IllegalStateException
                        || e is ArrayIndexOutOfBoundsException || e is SecurityException
                        || e is NumberFormatException
                    ) {
                        val intent = Intent(
                            Intent.ACTION_PACKAGE_REPLACED,
                            Uri.parse("package:com.scaaa.sds")
                        )
                        application.sendBroadcast(intent)
                    }
                }
            }
        }
    }
}