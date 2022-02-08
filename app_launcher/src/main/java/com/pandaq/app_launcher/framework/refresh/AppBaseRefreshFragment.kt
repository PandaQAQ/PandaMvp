package com.pandaq.app_launcher.framework.refresh

import androidx.viewbinding.ViewBinding
import com.pandaq.uires.mvp.refresh.BaseRefreshFragment

/**
 * Created by huxinyu on 2019/7/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :模板类
 */
abstract class AppBaseRefreshFragment<P : AppBaseRefreshPresenter<*>, VB : ViewBinding> :
    BaseRefreshFragment<P, VB>() {
}