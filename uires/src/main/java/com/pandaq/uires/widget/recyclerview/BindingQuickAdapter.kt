package com.pandaq.uires.widget.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.pandaq.rxpanda.utils.CastUtils
import java.lang.reflect.ParameterizedType

/**
 * Created by huxinyu on 5/13/21.
 * Email : panda.h@foxmail.com
 * Description :使用 viewbinding 绑定数据的 BaseQuickAdapter
 */
abstract class BindingQuickAdapter<T, VB : ViewBinding> :
    BaseQuickAdapter<T, BindingQuickAdapter.BindingHolder<VB>>(-1) {

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): BindingHolder<VB> {
        val binding = inflateViewBinding(getVbClass(), parent, LayoutInflater.from(context))
        return BindingHolder(binding)
    }

    private fun getVbClass(): Class<VB> {
        val type = (this.javaClass.genericSuperclass as ParameterizedType)
        val typeArray = type.actualTypeArguments
        return CastUtils.cast(typeArray[1])
    }

    /**
     * inflate
     */
    private fun inflateViewBinding(
        clazz: Class<VB>,
        parent: ViewGroup,
        layoutInflater: LayoutInflater
    ): VB {
        val paramsClazz =
            arrayOf(LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        val params = arrayOf(layoutInflater, parent, false)
        return CastUtils.cast(
            clazz.getMethod("inflate", *paramsClazz)
                .invoke(null, *params)
        )
    }


    open class BindingHolder<VB : ViewBinding>(val binding: VB) : BaseViewHolder(binding.root)
}