package com.pandaq.commonui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.pandaq.commonui.R;

/**
 * Created by huxinyu on 2018/5/23.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : a count view can increase and decrease count size
 */
public class CountChangeView extends RelativeLayout implements View.OnClickListener {

    private int minValue;
    private int maxValue;
    private int count = 0;
    private int changeCount = 0;
    private EditText etCount;
    private CountChangeListener mCountChangeListener;

    public CountChangeView(Context context) {
        this(context, null);
    }

    public CountChangeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CountChangeView);
        minValue = ta.getInteger(R.styleable.CountChangeView_minCount, 0);
        maxValue = ta.getInteger(R.styleable.CountChangeView_maxCount, 9999);
        boolean focusAble = ta.getBoolean(R.styleable.CountChangeView_focusable, false);
        int textColor = ta.getColor(R.styleable.CountChangeView_countColor, 0);
        float textSize = ta.getDimension(R.styleable.CountChangeView_countTextSize, 0);
        ta.recycle();
        inflate(getContext(), R.layout.res_count_view, this);
        ImageView ivReduce = findViewById(R.id.iv_tab_reduce);
        ImageView ivAdd = findViewById(R.id.iv_tab_add);
        etCount = findViewById(R.id.et_count);
        etCount.setText(String.valueOf(count));
        if (textColor != 0) {
            etCount.setTextColor(textColor);
        }
        if (textSize != 0) {
            etCount.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }
        if (!focusAble) {
            etCount.clearFocus();
        }
        etCount.setFocusable(focusAble);
        ivReduce.setOnClickListener(this);
        ivAdd.setOnClickListener(this);
        etCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && s.length() > 0) {
                    changeCount = Integer.parseInt(s.toString()) - count;
                    count = Integer.parseInt(s.toString());
                } else {
                    changeCount = -count;
                    count = 0;
                }
                if (mCountChangeListener != null) {
                    mCountChangeListener.change(count, changeCount);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_tab_add) {
            if (count + 1 <= maxValue) {
                etCount.setText(String.valueOf(count + 1));
            }

        } else if (i == R.id.iv_tab_reduce) {
            if (count - 1 >= minValue) {
                etCount.setText(String.valueOf(count - 1));
            }
        }
    }

    /**
     * 获取当期的限制最小值
     *
     * @return 最小值
     */
    public int getMinValue() {
        return minValue;
    }

    /**
     * 设置当期的限制最小值
     */
    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    /**
     * 获取当期的限制最大值
     *
     * @return 最大值
     */
    public int getMaxValue() {
        return maxValue;
    }

    /**
     * 设置当期的限制最大值
     */
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * 获取当前值
     *
     * @return 当前值
     */
    public int getCount() {
        return count;
    }

    /**
     * 设置当前值
     */
    public void setCount(int count) {
        if (count <= maxValue && count >= minValue) {
            etCount.setText(String.valueOf(count));
        } else {
//            Toast.makeText(getContext(), "maxValue " + maxValue + " minValue " + minValue, Toast.LENGTH_SHORT).show();
            return;
        }
        int changeCount = count - this.count;
        this.count = count;
        if (mCountChangeListener != null) {
            mCountChangeListener.change(this.count, changeCount);
        }
    }

    public void addCountChangeListener(CountChangeListener countChangeListener) {
        mCountChangeListener = countChangeListener;
    }

    public interface CountChangeListener {

        /**
         * @param value       当前值
         * @param changeValue 当前值减去旧值
         */
        void change(int value, int changeValue);
    }
}
