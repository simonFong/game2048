package com.simonfong.game2048;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WidgetAdapter mWidgetAdapter;
    private List<Integer> mDatas;
    private TextView mTvMaxPoint;
    private EditText mEtCount;
    private int mCount = 4;

    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvMaxPoint = findViewById(R.id.tv_max_point);
        mTvMaxPoint.setText("历史最高分：" + DataHelper.getMaxPoint());
        findViewById(R.id.btn_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishGame();
            }
        });
        mEtCount = findViewById(R.id.et_count);
        initData();
    }


    private void initData() {
        String s = mEtCount.getText().toString();
        if (!TextUtils.isEmpty(s)) {
            mCount = Integer.valueOf(s);
            if (mCount < 2 || mCount > 15) {
                Toast.makeText(this, "请设置关卡为2-15", Toast.LENGTH_LONG).show();
                return;
            }
        }

        RecyclerView recyclerview = findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, mCount);
        recyclerview.setLayoutManager(gridLayoutManager);

        mWidgetAdapter = new WidgetAdapter();
        recyclerview.setAdapter(mWidgetAdapter);

        mDatas = new ArrayList<>();
        for (int i = 0; i < mCount * mCount; i++) {
            mDatas.add(0);
        }
        DataHelper.start(mDatas);
        DataHelper.start(mDatas);
        mWidgetAdapter.setNewData(mDatas);

        recyclerview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //继承了Activity的onTouchEvent方法，直接监听点击事件
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //当手指按下的时候
                    x1 = event.getX();
                    y1 = event.getY();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    //当手指离开的时候
                    x2 = event.getX();
                    y2 = event.getY();
                    if (y1 > y2 && Math.abs(y1 - y2) > Math.abs(x1 - x2)) {
                        Log.d("onTouchEvent", "向上滑");
                        touch(0);
                    } else if (y1 < y2 && Math.abs(y1 - y2) > Math.abs(x1 - x2)) {
                        Log.d("onTouchEvent", "向下滑");
                        touch(1);
                    } else if (x1 > x2 && Math.abs(x1 - x2) > Math.abs(y1 - y2)) {
                        Log.d("onTouchEvent", "向左滑");
                        touch(2);
                    } else if (x2 > x1 && Math.abs(x1 - x2) > Math.abs(y1 - y2)) {
                        Log.d("onTouchEvent", "向右滑");
                        touch(3);
                    }
                }
                return false;
            }
        });
    }


    public void touch(int touchevent) {
        switch (touchevent) {
            case 0:
                DataHelper.up(mDatas, mCount);
                break;
            case 1:
                DataHelper.down(mDatas, mCount);
                break;
            case 2:
                DataHelper.left(mDatas, mCount);
                break;
            case 3:
                DataHelper.right(mDatas, mCount);
                break;
        }
        boolean start = DataHelper.start(mDatas);
        if (!start) {
            finishGame();
            Toast.makeText(this, "你这菜鸡，游戏结束", Toast.LENGTH_LONG).show();

        }
        mWidgetAdapter.setNewData(mDatas);
    }

    private void finishGame() {
        saveMaxPoint();
        initData();
    }

    private void saveMaxPoint() {
        int maxPoint = DataHelper.getMaxPoint();
        int point = mWidgetAdapter.getMaxPoint();
        if (maxPoint < point) {
            DataHelper.saveMaxPoint(point);
            mTvMaxPoint.setText("历史最高分：" + point);
        }

    }
}
