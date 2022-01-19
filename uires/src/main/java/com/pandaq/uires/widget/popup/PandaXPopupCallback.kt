package com.pandaq.uires.widget.popup

import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.interfaces.XPopupCallback

/**
 * Created by huxinyu on 6/11/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
 abstract class PandaXPopupCallback : XPopupCallback {
    override fun onCreated(popupView: BasePopupView?) {

    }

    override fun beforeShow(popupView: BasePopupView?) {

    }

    override fun onShow(popupView: BasePopupView?) {

    }

    override fun onDismiss(popupView: BasePopupView?) {

    }

    override fun beforeDismiss(popupView: BasePopupView?) {

    }

    override fun onBackPressed(popupView: BasePopupView?): Boolean {
        return false
    }

    override fun onKeyBoardStateChanged(popupView: BasePopupView?, height: Int) {

    }

    override fun onDrag(popupView: BasePopupView?, value: Int, percent: Float, upOrLeft: Boolean) {

    }

}