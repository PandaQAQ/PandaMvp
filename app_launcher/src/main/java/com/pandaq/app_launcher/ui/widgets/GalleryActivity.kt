package com.pandaq.app_launcher.ui.widgets

import android.annotation.SuppressLint
import com.pandaq.app_launcher.R
import com.pandaq.app_launcher.framework.AppBaseActivity
import com.pandaq.app_launcher.framework.AppBasePresenter
import com.pandaq.appcore.utils.system.DisplayUtils
import com.pandaq.uires.widget.gallery.IPagerItem
import com.pandaq.uires.widget.gallery.NormalBannerAdapter
import kotlinx.android.synthetic.main.launcher_activity_gallery.*


/**
 * Created by huxinyu on 2019/4/2.
 * Email : panda.h@foxmail.com
 * Description :
 */
class GalleryActivity : AppBaseActivity<AppBasePresenter<*>>() {

    private val mList = ArrayList<IPagerItem>()

    override fun bindContentRes(): Int {
        return R.layout.launcher_activity_gallery
    }

    override fun initVariable() {

    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        for (i in 1..3) {
            mList.add(object : IPagerItem {
                override fun getUrl(): String {
                    return "title$i"
                }

                override fun getTitleStr(): String {
                    return "title$i"
                }

            })
        }
        val adapter = NormalBannerAdapter(mList, true)
        adapter.bindPager(vp_gallery)
        vp_gallery.offscreenPageLimit = 2
        vp_gallery.pageMargin = DisplayUtils.dp2px(20f)
        ll_container.setOnTouchListener { v, event -> vp_gallery.dispatchTouchEvent(event) }
    }


    override fun loadData() {

    }
}