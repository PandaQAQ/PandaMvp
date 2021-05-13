package com.pandaq.app_launcher.ui.widgets

import com.pandaq.app_launcher.databinding.LauncherActivityGalleryBinding
import com.pandaq.app_launcher.framework.AppBaseActivity
import com.pandaq.app_launcher.framework.AppBasePresenter
import com.pandaq.appcore.utils.system.DisplayUtils
import com.pandaq.uires.widget.gallery.IPagerItem
import com.pandaq.uires.widget.gallery.NormalBannerAdapter


/**
 * Created by huxinyu on 2019/4/2.
 * Email : panda.h@foxmail.com
 * Description :
 */
class GalleryActivity : AppBaseActivity<AppBasePresenter<*>,LauncherActivityGalleryBinding>() {

    private val mList = ArrayList<IPagerItem>()

    override fun initVariable() {

    }

    override fun loadData() {

    }

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
        adapter.bindPager(binding.vpGallery)
        binding.vpGallery.offscreenPageLimit = 2
        binding.vpGallery.pageMargin = DisplayUtils.dp2px(20f)
        binding.llContainer.setOnTouchListener { v, event -> binding.vpGallery.dispatchTouchEvent(event) }
    }
}