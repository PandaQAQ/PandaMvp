package com.pandaq.uires.utils.picker;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.contrarywind.interfaces.IPickerViewData;
import com.contrarywind.view.WheelView;
import com.pandaq.uires.R;

import java.util.Calendar;
import java.util.List;

public final class PickerUtils {

    private static final int START_YEAR = 1900;
    private static final int START_MONTH = 0;
    private static final int START_DAY = 1;
    private static final int END_YEAR = 2099;
    private static final int END_MONTH = 11;
    private static final int END_DAY = 31;
    private static final int YEAR_1970 = 1970;
    private static final int CONTENT_TEXT_SIZE = 18;
    private static final int TITLE_TEXT_SIZE = 20;

    /**
     * 私有
     */
    private PickerUtils() {
    }

    /**
     * @param context      上下文
     * @param mListener    时间选择回调
     * @param selectedDate 当前时间
     * @return 时间选择实例
     */
    public static TimePickerView getYearMonth(final Context context, Calendar selectedDate,
                                              OnTimeSelectListener mListener) {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        //正确设置方式 原因：注意事项有说明
        startDate.set(START_YEAR, START_MONTH, START_DAY);
        endDate.set(END_YEAR, END_MONTH, END_DAY);

        return new TimePickerBuilder(context, mListener)
                .setType(new boolean[]{true, true, false, false, false, false})// 默认全部显示
                .setCancelText(selectedDate.get(Calendar.YEAR) + context.getString(R.string.text_pickYear) +
                        (selectedDate.get(Calendar.MONTH) + 1) + context.getString(R.string.text_pickMonth))//取消按钮文字
                .setSubmitText(context.getString(R.string.text_confirm))//确认按钮文字
                .setContentTextSize(CONTENT_TEXT_SIZE)//滚轮文字大小
                .setTitleSize(TITLE_TEXT_SIZE)//标题文字大小
                .setTitleText("  ")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(ContextCompat.getColor(context, R.color.res_colorTextMain))//标题文字颜色
                .setSubmitColor(ContextCompat.getColor(context, R.color.colorAccent))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(context, R.color.res_colorTextMain))//取消按钮文字颜色
                .setTitleBgColor(ContextCompat.getColor(context, R.color.colorPrimary))//标题背景颜色 Night mode
                .setBgColor(ContextCompat.getColor(context, R.color.white))//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel(context.getString(R.string.text_pickYear),
                        context.getString(R.string.text_pickMonth),
                        context.getString(R.string.text_pickDay),
                        context.getString(R.string.text_pickHour),
                        context.getString(R.string.text_pickMinute),
                        context.getString(R.string.text_pickSecond))//默认设置为年月日时分秒
                .setDividerColor(Color.LTGRAY).setDividerType(WheelView.DividerType.FILL)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();
    }

    /**
     * @param context      上下文
     * @param mListener    时间选择回调
     * @param selectedDate 当前时间
     * @return 时间选择实例
     */
    public static TimePickerView getYearMonthDay(final Context context,
                                                 Calendar selectedDate,
                                                 OnTimeSelectListener mListener) {
        return getDay(context, null, selectedDate, false, new boolean[]{true, true, true, false, false, false}, mListener);
    }

    /**
     * @param context      上下文
     * @param mListener    时间选择回调
     * @param selectedDate 当前时间
     * @param backTrans    背景遮罩是否透明
     * @return 时间选择实例
     */
    public static TimePickerView getYearMonthDay(final Context context, Calendar selectedDate,
                                                 boolean backTrans,
                                                 OnTimeSelectListener mListener) {
        return getDay(context, null, selectedDate, backTrans, new boolean[]{true, true, true, false, false, false}, mListener);
    }

    /**
     * @param context      上下文
     * @param mListener    时间选择回调
     * @param selectedDate 当前时间
     * @return 时间选择实例
     */
    public static TimePickerView getYearMonthDayWithStartTime(final Context context, Calendar starDate,
                                                              Calendar selectedDate,
                                                              OnTimeSelectListener mListener) {
        return getDay(context, starDate, selectedDate, false, new boolean[]{true, true, true, false, false, false}, mListener);
    }

    /**
     * @param context   上下文
     * @param mListener 时间选择回调
     * @return 时间选择实例
     */
    public static TimePickerView getTime(final Context context,
                                         OnTimeSelectListener mListener) {
        return getDay(context, Calendar.getInstance(),
                Calendar.getInstance(),
                false, new boolean[]{true, true, true, true, true, false}, mListener);
    }


    /**
     * @param context      上下文
     * @param mListener    时间选择回调
     * @param selectedDate 当前时间
     * @return 时间选择实例
     */
    public static TimePickerView getDay(final Context context, Calendar startDate,
                                        Calendar selectedDate,
                                        boolean[] type, OnTimeSelectListener mListener) {
        return getDay(context, startDate, selectedDate, false, type, mListener);
    }

    /**
     * @param context      上下文
     * @param mListener    时间选择回调
     * @param selectedDate 当前时间
     * @return 时间选择实例
     */
    public static TimePickerView getDay(final Context context, Calendar startDate,
                                        Calendar selectedDate, boolean backTrans,
                                        boolean[] type, OnTimeSelectListener mListener) {
        if (startDate == null) {
            startDate = Calendar.getInstance();
            //正确设置方式 原因：注意事项有说明
            startDate.set(START_YEAR, START_MONTH, START_DAY);
        }
        Calendar endDate = Calendar.getInstance();
        endDate.set(END_YEAR, END_MONTH, END_DAY);

        return new TimePickerBuilder(context, mListener)
                .setType(type)// 默认全部显示
                .setCancelText(selectedDate.get(Calendar.YEAR) + context.getString(R.string.text_pickYear) +
                        (selectedDate.get(Calendar.MONTH) + 1) + context.getString(R.string.text_pickMonth) +
                        selectedDate.get(Calendar.DAY_OF_MONTH) + context.getString(R.string.text_pickDay))//取消按钮文字
                .setSubmitText(context.getString(R.string.text_confirm))//确认按钮文字
                .setContentTextSize(CONTENT_TEXT_SIZE)//滚轮文字大小
                .setTitleSize(TITLE_TEXT_SIZE)//标题文字大小
                .setTitleText("  ")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setOutSideColor(ContextCompat.getColor(context, R.color.pickerview_bgColor_overlay))
                .setTitleColor(ContextCompat.getColor(context, R.color.res_colorTextMain))//标题文字颜色
                .setSubmitColor(ContextCompat.getColor(context, R.color.res_colorTextMain))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(context, R.color.res_colorTextMinor))//取消按钮文字颜色
                .setTitleBgColor(ContextCompat.getColor(context, R.color.res_colorWhite))//标题背景颜色 Night mode
                .setBgColor(ContextCompat.getColor(context, R.color.white))//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel(context.getString(R.string.text_pickYear),
                        context.getString(R.string.text_pickMonth),
                        context.getString(R.string.text_pickDay),
                        context.getString(R.string.text_pickHour),
                        context.getString(R.string.text_pickMinute),
                        context.getString(R.string.text_pickSecond))//默认设置为年月日时分秒
                .setDividerColor(Color.LTGRAY).setDividerType(WheelView.DividerType.FILL)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();
    }

    /**
     * @param context      上下文
     * @param mListener    时间选择回调
     * @param selectedDate 当前时间
     * @return 时间选择实例
     */
    public static TimePickerView getHourMinute(final Context context, Calendar selectedDate,
                                               OnTimeSelectListener mListener) {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        //正确设置方式 原因：注意事项有说明
        startDate.set(YEAR_1970, START_MONTH, START_DAY);
        endDate.set(YEAR_1970, END_MONTH, END_DAY);

        return new TimePickerBuilder(context, mListener)
                .setType(new boolean[]{false, false, false, true, true, false})// 默认全部显示
                .setCancelText(context.getString(R.string.text_cancel))//取消按钮文字
                .setSubmitText(context.getString(R.string.text_confirm))//确认按钮文字
                .setContentTextSize(CONTENT_TEXT_SIZE)//滚轮文字大小
                .setTitleSize(TITLE_TEXT_SIZE)//标题文字大小
                .setTitleText("  ")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setOutSideColor(ContextCompat.getColor(context, R.color.pickerview_bgColor_overlay))
                .setTitleColor(ContextCompat.getColor(context, R.color.res_colorTextMain))//标题文字颜色
                .setSubmitColor(ContextCompat.getColor(context, R.color.colorAccent))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(context, R.color.res_colorTextMain))//取消按钮文字颜色
                .setTitleBgColor(ContextCompat.getColor(context, R.color.res_colorWhite))//标题背景颜色 Night mode
                .setBgColor(ContextCompat.getColor(context, R.color.white))//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel(context.getString(R.string.text_pickYear),
                        context.getString(R.string.text_pickMonth),
                        context.getString(R.string.text_pickDay),
                        context.getString(R.string.text_pickHour),
                        context.getString(R.string.text_pickMinute),
                        context.getString(R.string.text_pickSecond))//默认设置为年月日时分秒
                .setDividerColor(Color.LTGRAY).setDividerType(WheelView.DividerType.FILL)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();
    }

    /**
     * @param context   上下文
     * @param datas     显示的内容列表
     * @param mListener 选择回调
     * @param listener  取消确定回调
     * @return pickerview
     */
    @SuppressWarnings("unchecked")
    public static OptionsPickerView makePicker(final Context context, List<IPickerViewData> datas,
                                               OnOptionsSelectListener mListener,
                                               OptionsPickerListener listener) {

        OptionsPickerView customPicker = new OptionsPickerBuilder(context, mListener)
                .setLayoutRes(R.layout.pickerview_layout, v -> {
                    final TextView tvSubmit = v.findViewById(R.id.tv_finish);
                    final TextView tvCancel = v.findViewById(R.id.tv_cancel);
                    tvSubmit.setOnClickListener(listener::confirm);

                    tvCancel.setOnClickListener(v1 -> listener.cancel());
                })
                .isDialog(false)
                .setOutSideCancelable(true)
                .build();
        customPicker.setPicker(datas);
        return customPicker;
    }

    public interface OptionsPickerListener {
        void confirm(View v);

        void cancel();
    }

}
