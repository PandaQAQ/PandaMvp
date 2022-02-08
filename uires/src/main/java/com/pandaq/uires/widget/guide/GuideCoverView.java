package com.pandaq.uires.widget.guide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.pandaq.appcore.R;

import androidx.annotation.LayoutRes;

/**
 * Created by huxinyu on 2018/4/23.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : 半透明遮罩新手引导 View
 */
public class GuideCoverView extends RelativeLayout {

    private FrameLayout mParent;

    public GuideCoverView(FrameLayout parent) {
        this(parent.getContext());
        mParent = parent;
    }

    private GuideCoverView(Context context) {
        this(context, null);
    }

    private GuideCoverView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化 View
     *
     * @param context context
     */
    private void init(Context context) {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
        setBackgroundColor(context.getResources().getColor(com.pandaq.appcore.R.color.colorTrans));
        this.setOnClickListener(v -> resetCoverView());
    }

    /**
     * 重置 coverView
     */
    private void resetCoverView() {
        if (mParent != null) {
            this.removeAllViews();
            mParent.removeView(this);
        }
    }

    /**
     * 为 GuideView 设置点击事件，默认为去掉 GuideView
     *
     * @param clickListener 点击事件接口
     */
    public void setOnclickListener(OnClickListener clickListener) {
        this.setOnClickListener(clickListener);
    }

    /**
     * 在中心点添加向导 View （向导 View 占满整个屏幕可直接使用此方法添加）
     *
     * @param guideView 向导 View
     */
    public void setGuideView(View guideView) {
        addView(guideView, getLp(0, 0));
    }


    /**
     * 在中心点添加向导 View （向导 View 占满整个屏幕可直接使用此方法添加）
     *
     * @param guideLayout 遮罩布局 id
     */
    public void setGuideView(Context context, @LayoutRes int guideLayout) {
        View coverView = LayoutInflater.from(context).inflate(guideLayout, null);
        setGuideView(coverView);
    }

    /**
     * 设置添加的引导图的位置，默认为中间位置0
     *
     * @param offsetX 偏移位，大于0 右移
     * @param offsetY 偏移位，大于0 下移
     */
    private LayoutParams getLp(int offsetX, int offsetY) {
        LayoutParams lp = new LayoutParams(ViewGroup
                .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //水平方向
        if (offsetX < 0) {
            lp.rightMargin = -offsetX;
        } else {
            lp.leftMargin = offsetX;
        }
        //垂直方向
        if (offsetY < 0) {
            lp.bottomMargin = -offsetY;
        } else {
            lp.topMargin = offsetY;
        }
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        return lp;
    }

    /**
     * 移除引导页
     */
    public void removeSelf() {
        resetCoverView();
    }
}
