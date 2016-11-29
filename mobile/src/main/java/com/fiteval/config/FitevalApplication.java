package com.fiteval.config;

import android.app.Application;

import com.fiteval.data.UserDto;

/**
 * Created by Henry Moon on 10/23/2016.
 */

public class FitevalApplication extends Application {
    private int screenHeight;

    private UserDto mUserDto;

    public UserDto getUserDto() {
        return mUserDto;
    }

    public void setUserDto(UserDto mUserDto) {
        this.mUserDto = mUserDto;
    }

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
