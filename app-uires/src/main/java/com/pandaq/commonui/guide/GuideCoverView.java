package com.pandaq.commonui.guide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.pandaq.commonui.R;

/**
 * Created by huxinyu on 2018/4/23.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : 半透明遮罩新手引导 View
 */
public class GuideCoverView extends RelativeLayout {

    private FrameLayout mParent;
    private AlignType mAlignType = AlignType.CENTER;

    public enum AlignType {
        CENTER, CENTER_TOP, CENTER_BOTTOM,
        LEFT_TOP, LEFT_CENTER, LEFT_BOTTOM,
        RIGHT_TOP, RIGHT_CENTER, RIGHT_BOTTOM
    }

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
        setBackgroundColor(context.getResources().getColor(R.color.public_colorCover));
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
    public void setOnclickListener(View.OnClickListener clickListener) {
        this.setOnClickListener(clickListener);
    }

    /**
     * 往半透明遮罩上添加引导 View
     *
     * @param guideView 引导 View
     * @param offsetX   相对于 AlignType 决定的中心点的 X 轴偏移量
     * @param offsetY   相对于 AlignType 决定的中心点的 Y 轴偏移量
     */
    public void addGuideView(View guideView, int offsetX, int offsetY) {
        addView(guideView, getLp(offsetX, offsetY));
    }

    /**
     * 在中心点添加向导 View （向导 View 占满整个屏幕可直接使用此方法添加）
     *
     * @param guideView 向导 View
     */
    public void addGuideView(View guideView) {
        addView(guideView, getLp(0, 0));
    }


    /**
     * 设置添加的引导图的位置，默认为中间位置0
     *
     * @param offsetX 偏移位，大于0 右移
     * @param offsetY 偏移位，大于0 下移
     */
    private RelativeLayout.LayoutParams getLp(int offsetX, int offsetY) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup
                .LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
        switch (mAlignType) {
            case CENTER:
                lp.addRule(RelativeLayout.CENTER_IN_PARENT);
                return lp;
            case CENTER_TOP:
                lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
                lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                return lp;
            case CENTER_BOTTOM:
                lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
                lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                return lp;
            case LEFT_CENTER:
                lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                lp.addRule(RelativeLayout.CENTER_VERTICAL);
                return lp;
            case LEFT_TOP:
                lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                return lp;
            case LEFT_BOTTOM:
                lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                return lp;
            case RIGHT_CENTER:
                lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                lp.addRule(RelativeLayout.CENTER_VERTICAL);
                return lp;
            case RIGHT_TOP:
                lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                return lp;
            case RIGHT_BOTTOM:
                lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                return lp;
            default:
                return lp;
        }
    }


    /**
     * 设置添加的引导图默认在屏幕的方位（上下左右中...）
     *
     * @param alignType align 类型
     */
    public void setAlginType(AlignType alignType) {
        mAlignType = alignType;
    }

    /**
     * 移除引导页
     */
    public void removeSelf() {
        resetCoverView();
    }
}
