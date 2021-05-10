package com.pandaq.pandamvp.template

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import com.pandaq.appcore.framework.app.ActivityTask
import com.pandaq.appcore.framework.mvp.CoreBaseActivity
import com.pandaq.uires.R
import com.pandaq.uires.loading.LoadingDialogUtil
import com.pandaq.uires.msgwindow.Toaster
import com.pandaq.uires.mvp.BaseActivity
import com.pandaq.uires.toolbar.CNToolbar


/**
 * Created by huxinyu on 2019/7/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
abstract class AppBaseActivity<P : AppBasePresenter<*>> : BaseActivity<P>() {


}