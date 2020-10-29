package com.pandaq.app_jetpack.app.ui.adapters

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.pandaq.app_jetpack.R
import com.pandaq.app_jetpack.app.entity.Story
import com.pandaq.appcore.imageloader.core.PicLoader
import kotlinx.android.synthetic.main.a_item_zhihu.view.*

/**
 * Created by huxinyu on 2020/4/27.
 * Email : panda.h@foxmail.com
 * Description :
 */
class ZhihuNewsAdapter : BaseQuickAdapter<Story, BaseViewHolder>(R.layout.a_item_zhihu) {

    init {
        addChildClickViewIds(R.id.item_cardview)
    }

    override fun convert(holder: BaseViewHolder, item: Story) {
        holder.itemView.apply {
            PicLoader.with(context)
                    .load(item.images[0])
                    .into(this.news_image)
            this.news_title.text = item.title
        }
    }
}