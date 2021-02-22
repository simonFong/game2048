package com.simonfong.game2048;

import android.content.Context;
import android.util.AttributeSet;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Created by fengzimin  on  2021/2/22.
 * interface by
 */
public class MyCL extends ConstraintLayout {
    public MyCL(Context context) {
        super(context);
    }

    public MyCL(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
