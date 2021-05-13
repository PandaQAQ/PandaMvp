package com.pandaq.app_jetpack.app.ui.adapters

import com.pandaq.app_jetpack.R
import com.pandaq.app_jetpack.app.entity.Story
import com.pandaq.app_jetpack.databinding.AItemZhihuBinding
import com.pandaq.appcore.imageloader.core.PicLoader
import com.pandaq.uires.widget.recyclerview.BindingQuickAdapter

/**
 * Created by huxinyu on 2020/4/27.
 * Email : panda.h@foxmail.com
 * Description :
 */
class ZhihuNewsAdapter : BindingQuickAdapter<Story, AItemZhihuBinding>() {

    init {
        addChildClickViewIds(R.id.item_cardview)
    }

    override fun convert(holder: BindingHolder<AItemZhihuBinding>, item: Story) {
        holder.binding.let {
            PicLoader.with(context)
                    .load(item.images[0])
                    .into(it.newsImage)
            it.newsTitle.text = item.title
        }
    }
}