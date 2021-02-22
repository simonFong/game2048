package com.simonfong.game2048;

import android.app.Application;

/**
 * Created by fengzimin  on  2021/2/22.
 * interface by
 */
public class App extends Application {
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
