package com.simonfong.game2048;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatTextView;

public class WrapContentTextView extends AppCompatTextView {
    private static final String TAG = "WrapContentTextView";
    private Paint mPaint;
    private Boolean isWrapContent = false;//是否开启根据view的宽高调节字体大小

    public void setWrapContent(Boolean wrapContent) {
        isWrapContent = wrapContent;
        invalidate();
    }

    public Boolean getWrapContent() {
        return isWrapContent;
    }

    public WrapContentTextView(Context context) {
        super(context);
        init();
    }

    public WrapContentTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.WrapContentTextView, 0, 0);
        isWrapContent = typedArray.getBoolean(R.styleable.WrapContentTextView_isWrapContent, false);
        typedArray.recycle();
        init();
    }

    public WrapContentTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        float density = getResources().getDisplayMetrics().density;
        Log.d(TAG, "onDraw: ------- 屏幕密度系数density = " + density);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isWrapContent) {
            int width = getWidth();
            int height = getHeight();
            Log.d(TAG, "onDraw: ------- View的width = " + width + "; View的height = " + height);
            CharSequence text = getText();
            if (!TextUtils.isEmpty(text)) {
//文字的字数
                int length = text.length();
//view的内边距
                int paddingLeft = getPaddingLeft();
                int paddingRight = getPaddingRight();
                int paddingTop = getPaddingTop();
                int paddingBottom = getPaddingBottom();
                Log.d(TAG, "onDraw: ------- paddingLeft = " + paddingLeft +
                        "; paddingTop = " + paddingTop +
                        "; paddingRight = " + paddingRight +
                        "; paddingBottom = " + paddingBottom);
//文字可绘制的宽高
                int textWidth = width - paddingLeft - paddingRight;
                int textHeight = height - paddingTop - paddingBottom;
//一个字可绘制的宽度
                int oneWidth = textWidth / length;
//一个字可绘制的宽度与可绘制的高度取最小的值
                int textSize = Math.min(oneWidth, textHeight);
                Log.d(TAG, "onDraw: ------- 文字长度：" + length +
                        "; 可绘制的textWidth = " + textWidth +
                        "; oneWidth = " + oneWidth +
                        "; 可绘制的textHeight = " + textHeight);
//当前文字的size
                float size = getTextSize();
                float measureText = getPaint().measureText(text, 0, length);
                Log.d(TAG, "onDraw: ------- 初始文字的size = " + size +
                        "; 初始文字的宽measureText = " + measureText);
//获取当前文字的高度
                Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
                float tHeight = fontMetrics.bottom - fontMetrics.top;
                Log.d(TAG, "onDraw: ------- 初始文字的fontMetrics.bottom= " + fontMetrics.bottom +
                        "; 初始文字的fontMetrics.top= " + fontMetrics.top +
                        "; 初始文字的高度tHeight= " + tHeight);
                float newSize = 0;
//新的文字大小根据宽度比例关系得到
                float newSizeW = size * textSize * length / measureText;
//新的文字大小根据高度比例关系得到
                float newSizeH = size * textHeight / tHeight;
                Log.d(TAG, "onDraw: ------- 根据宽度比例关系得到newSizeW= " + newSizeW +
                        "; 根据高度比例关系得到newSizeH= " + newSizeH);
                newSize = Math.min(newSizeW, newSizeH);
                Log.d(TAG, "onDraw: ------- 取最小的文字大小设置给画笔newSize : " + newSize);
                mPaint.setTextSize(newSize);
                mPaint.setColor(getCurrentTextColor());
                Rect textRect = new Rect();
                mPaint.getTextBounds(text.toString(), 0, length, textRect);
                Paint.FontMetrics mPaintFontMetrics = mPaint.getFontMetrics();
                float top = mPaintFontMetrics.top;
                float bottom = mPaintFontMetrics.bottom;
//文字绘制的x轴起点
                int startX = (width - textRect.width() + paddingLeft - paddingRight) / 2;
//文字绘制的中心
                int textCenterY = textHeight + paddingTop - textHeight / 2;
//文字绘制的基线 BaseLine
                int startY = (int) (textCenterY - (bottom - top) / 2 - top);
                Log.d(TAG, "onDraw: ------- textRect.width()= " + textRect.width() +
                        "; textRect.height()= " + textRect.height());
                Log.d(TAG, "onDraw: ------- 绘制的起点：X = " + startX + "; Y = " + startY);
                canvas.drawText(text, 0, length, startX, startY, mPaint);
            }
        } else super.onDraw(canvas);
    }
}
