package com.pandaq.uires.widget.floatingmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;

import com.pandaq.uires.R;


/**
 * 继承了悬浮按钮的类
 *
 * FloatingActionButton的一种扩展吧，好像没啥特别的
 * 就加了一个mPlusColor属性,设置图像的颜色，是用来扩展的吧？
 *
 */
public class AddFloatingActionButton extends FloatingActionButton {
  int mPlusColor;

  public AddFloatingActionButton(Context context) {
    this(context, null);
  }

  public AddFloatingActionButton(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public AddFloatingActionButton(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  void init(Context context, AttributeSet attributeSet) {
    TypedArray attr = context.obtainStyledAttributes(attributeSet, R.styleable.AddFloatingActionButton, 0, 0);
    mPlusColor = attr.getColor(R.styleable.AddFloatingActionButton_fab_plusIconColor, getColor(android.R.color.holo_red_light));
    attr.recycle();

    super.init(context, attributeSet);
  }

  /**
   * @return the current Color of plus icon.
   */
  public int getPlusColor() {
    return mPlusColor;
  }

  public void setPlusColorResId(@ColorRes int plusColor) {
    setPlusColor(getColor(plusColor));
  }

  public void setPlusColor(int color) {
    if (mPlusColor != color) {
      mPlusColor = color;
      updateBackground();
    }
  }

  @Override
  public void setIcon(@DrawableRes int icon) {
    throw new UnsupportedOperationException("Use FloatingActionButton if you want to use custom icon");
  }

  @Override
  Drawable getIconDrawable() {
    final float iconSize = getDimension(R.dimen.fab_icon_size);
    final float iconHalfSize = iconSize / 2f;

    final float plusSize = getDimension(R.dimen.fab_plus_icon_size);
    final float plusHalfStroke = getDimension(R.dimen.fab_plus_icon_stroke) / 2f;
    final float plusOffset = (iconSize - plusSize) / 2f;

    final Shape shape = new Shape() {
      @Override
      public void draw(Canvas canvas, Paint paint) {
        canvas.drawRect(plusOffset, iconHalfSize - plusHalfStroke, iconSize - plusOffset, iconHalfSize + plusHalfStroke, paint);
        canvas.drawRect(iconHalfSize - plusHalfStroke, plusOffset, iconHalfSize + plusHalfStroke, iconSize - plusOffset, paint);
      }
    };

    ShapeDrawable drawable = new ShapeDrawable(shape);

    final Paint paint = drawable.getPaint();
    paint.setColor(mPlusColor);
    paint.setStyle(Style.FILL);
    paint.setAntiAlias(true);

    return drawable;
  }
}
