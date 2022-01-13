package com.pandaq.uires.utils

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.annotation.ColorInt
import com.afollestad.materialdialogs.MaterialDialog
import com.pandaq.appcore.utils.regex.RegexUtils
import com.pandaq.appcore.utils.system.DisplayUtils
import com.pandaq.uires.R
import java.util.regex.Pattern

/**
 * Created by huxinyu on 2019/9/27.
 * Email : panda.h@foxmail.com
 * Description :
 */
fun MaterialDialog.compileSize(): MaterialDialog {
    val params = this.window?.attributes
    params?.width = DisplayUtils.getScreenWidth() - DisplayUtils.dp2px(32f)
    this.window?.attributes = params
    return this
}

fun MaterialDialog.transWindow(): MaterialDialog {
    this.window?.setBackgroundDrawableResource(R.color.btn_red_normal)
    val parent = this.customView?.parent as View
    parent.transAll()
    return this
}

fun View.transAll() {
    if (this.parent != null) {
        val parent = this.parent as View
        parent.setBackgroundResource(R.color.colorTrans)
        parent.transAll()
    }
}

/**
 * 关键字高亮显示
 *
 * @param text 文字
 *
 * @param keyword1 文字中的关键字数组
 *
 * @return
 */
fun String.matchWords(keywords: Array<String?>): SpannableStringBuilder {
    val keyword = arrayOfNulls<String>(keywords.size)
    System.arraycopy(keywords, 0, keyword, 0, keywords.size)
    val spannable = SpannableStringBuilder(this)

    var span: CharacterStyle
    var wordReg: String
    for (i in keyword.indices) {
        var key = ""
        //  处理通配符问题
        keyword[i]?.apply {
            val chars = this.toCharArray()
            for (k in chars.indices) {
                if (RegexUtils.isNeedTransform(chars[k].toString())) {
                    key = key + "\\" + chars[k].toString()
                } else {
                    key += chars[k].toString()
                }
            }
            keyword[i] = key
        }


        wordReg = "(?i)" + keyword[i]   //忽略字母大小写
        val pattern = Pattern.compile(wordReg)
        val matcher = pattern.matcher(this)
        while (matcher.find()) {
            span = ForegroundColorSpan(Color.parseColor("#238bfe"))
            spannable.setSpan(span, matcher.start(), matcher.end(), Spannable.SPAN_MARK_MARK)
        }
    }
    return spannable
}

/**
 * 高亮匹配字符串
 */
fun String.matchKeyword(keyword: String): SpannableStringBuilder {
    val spannable = SpannableStringBuilder(this)
    var span: CharacterStyle
    val pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    while (matcher.find()) {
        span = ForegroundColorSpan(Color.parseColor("#238bfe"))
        spannable.setSpan(span, matcher.start(), matcher.end(), Spannable.SPAN_MARK_MARK)
    }
    return spannable
}

/**
 * 高亮匹配字符串
 */
fun String.matchKeyword(keyword: String, @ColorInt color: Int): SpannableStringBuilder {
    val spannable = SpannableStringBuilder(this)
    var span: CharacterStyle
    val pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    while (matcher.find()) {
        span = ForegroundColorSpan(color)
        spannable.setSpan(span, matcher.start(), matcher.end(), Spannable.SPAN_MARK_MARK)
    }
    return spannable
}

/**
 * 文字高亮且可点击
 */
fun String.matchAndClick(keyWords: Iterable<String>, listener: SpanClickListener): SpannableString {
    val s = SpannableString(this)
    keyWords.forEach {
        val p = Pattern.compile(it)
        val m = p.matcher(s)
        while (m.find()) {
            val start = m.start()
            val end = m.end()
            val span = TextClickSpan(listener, it)
            s.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
    return s
}