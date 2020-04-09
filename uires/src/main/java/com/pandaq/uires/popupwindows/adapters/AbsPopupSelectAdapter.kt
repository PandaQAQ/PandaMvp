package com.pandaq.uires.popupwindows.adapters

import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.pandaq.uires.popupwindows.ItemData

/**
 * Created by huxinyu on 2020/4/9.
 * Email : panda.h@foxmail.com
 * Description :列表选择 popupwindow 的 adapter 基类
 */
abstract class AbsPopupSelectAdapter<T : ItemData, K : BaseViewHolder> : BaseQuickAdapter<T, K> {

    constructor(@LayoutRes res: Int) : this(res, null)

    constructor(@Nullable data: List<T>) : this(0, data)

    constructor(@LayoutRes layoutResId: Int, @Nullable data: List<T>?) : super(layoutResId, data)

    private var lastCheck = -1

    open fun setSelected(index: Int) {
        if (lastCheck != -1) {
            data[lastCheck].isChecked = false
            this.notifyItemChanged(lastCheck)
        }
        data[index].isChecked = true
        this.notifyItemChanged(index)
        lastCheck = index
    }

    override fun setNewData(data: MutableList<T>?) {
        super.setNewData(data)
    }

}