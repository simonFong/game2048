package com.simonfong.game2048;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WidgetAdapter mWidgetAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerview = findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        recyclerview.setLayoutManager(gridLayoutManager);

        mWidgetAdapter = new WidgetAdapter();
        recyclerview.setAdapter(mWidgetAdapter);

        initData();
    }

    private void initData() {
        List<Integer> viewWidgets = new ArrayList<>();
        viewWidgets.add(0);
        viewWidgets.add(1);
        viewWidgets.add(1);
        viewWidgets.add(0);
        viewWidgets.add(0);
        viewWidgets.add(1);
        viewWidgets.add(1);
        viewWidgets.add(0);
        viewWidgets.add(0);
        viewWidgets.add(1);
        viewWidgets.add(1);
        viewWidgets.add(0);
        viewWidgets.add(0);
        viewWidgets.add(1);
        viewWidgets.add(1);
        viewWidgets.add(0);
        mWidgetAdapter.setNewData(viewWidgets);
    }

    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //继承了Activity的onTouchEvent方法，直接监听点击事件
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x1 = event.getX();
            y1 = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            //当手指离开的时候
            x2 = event.getX();
            y2 = event.getY();
            if(y1 - y2 > 50) {
                Log.d("onTouchEvent","向上滑");
                touchUp();
            } else if(y2 - y1 > 50) {
                Log.d("onTouchEvent","向下滑");
            } else if(x1 - x2 > 50) {
                Log.d("onTouchEvent","向左滑");
            } else if(x2 - x1 > 50) {
                Log.d("onTouchEvent","向右滑");
            }
        }
        return super.onTouchEvent(event);
    }

    private void touchUp() {

    }
}
