package com.pandaq.uires.widget.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
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
        val binding = inflateViewBinding(getVbClass(), LayoutInflater.from(context))
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
    private fun inflateViewBinding(clazz: Class<VB>, layoutInflater: LayoutInflater): VB {
        return CastUtils.cast(clazz.getMethod("inflate", LayoutInflater::class.java).invoke(null, layoutInflater))
    }


    open class BindingHolder<VB : ViewBinding>(val binding: VB) : BaseViewHolder(binding.root)
}