package com.pandaq.uires.widget.popup

import android.annotation.SuppressLint
import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.core.PositionPopupView
import com.pandaq.appcore.utils.FixedCountDownTimer
import com.pandaq.appcore.log.PLogger
import com.pandaq.uires.R
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Created by huxinyu on 6/11/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
class PlayAdNoticePopup(
    context: Context,
   private val notice: String,
    val timerListener: TimerFinishListener
) :
    PositionPopupView(context) {

    private var timer: FixedCountDownTimer? = null
    private var timeView: TextView? = null

    override fun show(): BasePopupView {
        timer = null
        timer = object : FixedCountDownTimer(10000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                val left = millisUntilFinished / 1000f
                val df = DecimalFormat("#")
                df.roundingMode = RoundingMode.HALF_UP
                val leftTime = df.format(left)
                timeView?.text = "$leftTime 秒 "
                PLogger.d("Time", leftTime)
            }

            override fun onFinish() {
                PLogger.d("Time", "0")
                timerListener.timerFinish()
                dismiss()
            }
        }
        timer?.start()
        return super.show()
    }

    override fun dismiss() {
        super.dismiss()
        timer?.cancel()
        timer = null
    }

    override fun initPopupContent() {
        super.initPopupContent()
        timeView = findViewById(R.id.tv_content)
        val noticeText = findViewById<TextView>(R.id.tv_notice)
        noticeText.text = notice
        val container: LinearLayout = findViewById(R.id.ll_popup_container)
        container.setOnClickListener {
            timerListener.timerReset()
            dismiss()
        }
    }

    override fun getImplLayoutId(): Int {
        return R.layout.popup_play_ad_notice
    }

    interface TimerFinishListener {
        fun timerFinish()
        fun timerReset()
    }

}