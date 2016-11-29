package com.fiteval.config;

import android.app.Application;

/**
 * Created by Henry Moon on 10/23/2016.
 */

public class FitevalApplication extends Application {
    private int screenHeight;

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
