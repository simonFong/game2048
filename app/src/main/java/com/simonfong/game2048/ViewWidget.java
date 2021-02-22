package com.simonfong.game2048;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * Created by fengzimin  on  2021/2/22.
 * interface by
 */
public class ViewWidget extends LinearLayout {
    //0  1  2   3   4   5   6    7    8     9    10
    //2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048
    public int mType;
    private TextView mTvNum;
    private View mBgView;
    private Context mContext;

    public ViewWidget(Context context, int type) {
        super(context);
        mType = type;
        initView(context);
    }

    public ViewWidget(Context context) {
        super(context);
        initView(context);
    }

    public ViewWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_weiget, this);
        mTvNum = inflate.findViewById(R.id.tv_num);
        mBgView = inflate.findViewById(R.id.view_bg);
    }

    private void setType(int type) {
        mType = type;
        int num = 0;
        int color = mContext.getColor(R.color.colorAccent);
        switch (type) {
            case 0:
                num = 2;
                color = mContext.getColor(R.color.colorAccent);
                break;
            case 1:
                num = 4;
                color = mContext.getColor(R.color.colorPrimaryDark);
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:

                break;
            case 8:

                break;
            case 9:

                break;
            case 10:

                break;
        }
        mTvNum.setText("" + num);
        mBgView.setBackgroundColor(color);
    }
}
