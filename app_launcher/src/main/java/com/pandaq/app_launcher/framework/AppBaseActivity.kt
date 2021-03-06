package com.pandaq.app_launcher.framework

import androidx.viewbinding.ViewBinding
import com.pandaq.uires.mvp.BaseActivity


/**
 * Created by huxinyu on 2019/7/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
abstract class AppBaseActivity<P : AppBasePresenter<*>,VB: ViewBinding> : BaseActivity<P,VB>() {

}