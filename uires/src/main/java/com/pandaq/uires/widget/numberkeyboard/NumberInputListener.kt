package com.pandaq.uires.widget.numberkeyboard

/**
 * Created by huxinyu on 6/11/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
interface NumberInputListener {
    fun onInputChanged(value:String?)
    fun onConfirmed(value:String)
}