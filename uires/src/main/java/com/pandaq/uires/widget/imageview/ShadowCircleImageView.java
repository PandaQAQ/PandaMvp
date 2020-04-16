package com.pandaq.uires.widget.imageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.pandaq.uires.R;

/**
 * Created by huxinyu on 2018/6/5.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
public class ShadowCircleImageView extends AppCompatImageView {

    private float shadowWidth;
    private float borderWidth;
    private int viewWidth;
    private int viewHeight;
    private Bitmap image;
    private Paint paint;
    private Paint paintBorder;
    private int bgColor;

    public ShadowCircleImageView(Context context) {
        super(context);
        setup(null);
    }

    public ShadowCircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(attrs);
    }

    public ShadowCircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup(attrs);
    }

    private void setup(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ShadowCircleImageView);
        int shadowColor = ta.getColor(R.styleable.ShadowCircleImageView_shadowColor, Color.DKGRAY);
        int borderColor = ta.getColor(R.styleable.ShadowCircleImageView_borderColor, Color.WHITE);
        bgColor = ta.getColor(R.styleable.ShadowCircleImageView_bgColor, Color.TRANSPARENT);
        setBackgroundColor(bgColor);
        shadowWidth = ta.getDimension(R.styleable.ShadowCircleImageView_shadowWidth, 0);
        borderWidth = ta.getDimension(R.styleable.ShadowCircleImageView_borderWidth, 0);
        ta.recycle();
        // init paint
        paint = new Paint();
        paint.setAntiAlias(true);

        paintBorder = new Paint();
        setBorderColor(borderColor);
        paintBorder.setAntiAlias(true);
        this.setLayerType(LAYER_TYPE_SOFTWARE, paintBorder);
        if (shadowWidth != 0) {
            paintBorder.setShadowLayer(16.0f, 0.0f, shadowWidth, shadowColor);
        }
    }

    public void setBorderWidth(int shadowWidth) {
        this.shadowWidth = shadowWidth;
        this.invalidate();
    }

    public void setBorderColor(int shadowColor) {
        if (paintBorder != null)
            paintBorder.setColor(shadowColor);
        this.invalidate();
    }

    private void loadBitmap() {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) this.getDrawable();
        if (bitmapDrawable != null)
            image = bitmapDrawable.getBitmap();
    }

    @SuppressLint("DrawAllocation")
    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(bgColor);
        // load the bitmap
        loadBitmap();
        // init shader
        if (image != null) {
            BitmapShader shader = new BitmapShader(Bitmap.createScaledBitmap(image, canvas.getWidth(),
                    canvas.getHeight(), false), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            paint.setShader(shader);
            int circleCenter = viewWidth / 2;

            // circleCenter is the x or y of the view's center
            // radius is the radius in pixels of the cirle to be drawn
            // paint contains the shader that will texture the shape
            canvas.drawCircle(circleCenter + shadowWidth, circleCenter + shadowWidth,
                    circleCenter + borderWidth, paintBorder);
            canvas.drawCircle(circleCenter + shadowWidth, circleCenter + shadowWidth,
                    circleCenter, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);

        viewWidth = (int) (width - (shadowWidth * 2));
        viewHeight = (int) (height - (shadowWidth * 2));

        setMeasuredDimension(width, height);
    }

    private int measureWidth(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text
            result = viewWidth;
        }

        return result;
    }

    private int measureHeight(int measureSpecHeight) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpecHeight);
        int specSize = MeasureSpec.getSize(measureSpecHeight);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text (beware: ascent is a negative number)
            result = viewHeight;
        }

        return (int) (result + shadowWidth + shadowWidth / 4);
    }
}
