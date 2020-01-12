package com.pandaq.uires.utils.picker

import com.contrarywind.interfaces.IPickerViewData

/**
 * Created by huxinyu on 2020/1/12.
 * Email : panda.h@foxmail.com
 * Description :
 */
data class PickerItem(val id: String, val value: String) : IPickerViewData {
    override fun getPickerViewText(): String = value
}