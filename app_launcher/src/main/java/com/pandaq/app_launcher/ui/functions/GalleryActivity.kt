package com.pandaq.app_launcher.ui.functions

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import com.pandaq.appcore.framework.mvp.BasePresenter
import com.pandaq.commonui.utils.DisplayUtils
import com.pandaq.commonui.widget.gallery.GalleryPageAdapter
import com.pandaq.pandamvp.framework.AppBaseActivity
import kotlinx.android.synthetic.main.launcher_activity_gallery.*


/**
 * Created by huxinyu on 2019/4/2.
 * Email : panda.h@foxmail.com
 * Description :
 */
class GalleryActivity : AppBaseActivity<BasePresenter<*>>() {


    private val mList = ArrayList<String>()


    override fun injectPresenter(): BasePresenter<*>? = null

    override fun bindContentRes(): Int {
        return com.pandaq.pandamvp.R.layout.app_activity_gallery
    }

    override fun initVariable() {

    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        for (i in 1..3) {
            mList.add("Position: $i")
        }
        val adapter = GalleryPageAdapter(mList, true)
        adapter.bindPager(vp_gallery)
        vp_gallery.offscreenPageLimit = 2
        vp_gallery.pageMargin = DisplayUtils.dp2px(20f)
        ll_container.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                return vp_gallery.dispatchTouchEvent(event)
            }

        })

    }


    override fun loadData() {

    }
}