package com.pandaq.appcore.utils.ext

import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.widget.TextView
import androidx.annotation.ColorInt
import java.math.BigDecimal
import java.text.DecimalFormat

/**
 * Created by huxinyu on 2019/7/26.
 * Email : panda.h@foxmail.com
 * Description : kotlin 扩展
 */

/**
 * BigDecimal 保留两位位小数并按小数字体小于整数字体
 */
fun BigDecimal.showPrice2Num(): SpannableString {
    val value = this.format2Num()
    val spannableString = SpannableString(value)
    if (value.contains(".")) {
        spannableString.setSpan(
            RelativeSizeSpan(0.6f),
            value.indexOf("."),
            value.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    return spannableString
}

fun Float.showPrice2Num(): String {
    return BigDecimal(this.toDouble()).showPrice2Num().toString()
}

fun Double.showPrice2Num(): Float {
    return BigDecimal(this).showPrice2Num().toString().toFloat()
}

fun Double.showPrice1Num(): Float {
    return BigDecimal(this).showPrice1Num().toString().toFloat()
}

/**
 * BigDecimal 保留一位小数并按小数字体小于整数字体
 */
fun BigDecimal.showPrice1Num(): SpannableString {
    val value = this.format1Num()
    val spannableString = SpannableString(value)
    if (value.contains(".")) {
        spannableString.setSpan(
            RelativeSizeSpan(0.6f),
            value.indexOf("."),
            value.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    return spannableString
}

/**
 * BigDecimal 保留两位小数
 */
fun BigDecimal.format2Num(): String {
    val format = DecimalFormat("#.00")
    return if (this.compareTo(BigDecimal.ZERO) == 0) {
        "0.00"
    } else if (this > BigDecimal.ZERO && (this < BigDecimal(1))) {
        "0" + format.format(this).toString()
    } else {
        format.format(this).toString()
    }
}

/**
 * BigDecimal 保留一位小数
 */
fun BigDecimal.format1Num(): String {
    val format = DecimalFormat("#.0")
    return if (this.compareTo(BigDecimal.ZERO) == 0) {
        "0.0"
    } else if (this > BigDecimal.ZERO && (this < BigDecimal(1))) {
        "0" + format.format(this).toString()
    } else {
        format.format(this).toString()
    }
}

/**
 * 将 list 按固定长度分割
 */
fun <T> List<T>.splitItems(step: Int): List<List<T>> {
    return if (size < step) {
        listOf(this)
    } else {
        val data = mutableListOf<List<T>>()
        // 以步长 step 进行循环
        for (index in 0..size step step) {
            if (index + step > size) {
                data.add(subList(index, size))
            } else {
                data.add(subList(index, index + step))
            }
        }
        data
    }
}

/**
 * 设置文字颜色渐变
 */
fun TextView.setGradientStyle(@ColorInt colorStart: Int, @ColorInt colorEnd: Int) {
    val gradient = LinearGradient(
        0f,
        0f,
        paint.textSize * text.length,
        0f,
        colorStart,
        colorEnd,
        Shader.TileMode.CLAMP
    )
    paint.shader = gradient
    invalidate()
}

/**
 * 设置文字中划线
 */
fun TextView.strikeCenter() {
    paint.flags = Paint.STRIKE_THRU_TEXT_FLAG //中划线
}

/**
 * 设置价格文字小数点后文字更小
 */
fun String.formatPriceStyle(): SpannableString {
    val spannableString = SpannableString(this)
    if (this.contains(".")) {
        spannableString.setSpan(
            RelativeSizeSpan(0.8f),
            this.indexOf("."),
            this.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    return spannableString
}

fun Long.byteToGb(): Double {
    return this * 1.0 / 1024 / 1024 / 1024
}
