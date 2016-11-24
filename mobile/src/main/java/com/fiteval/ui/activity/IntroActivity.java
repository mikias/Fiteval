package com.fiteval.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fiteval.R;
import com.fiteval.config.FitevalApplication;
import com.fiteval.data.UserDto;

public class IntroActivity extends AppCompatActivity {

    private FitevalApplication mApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        int loadingTime = 2000;
        init();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        }, loadingTime);
    }

    public void init() {
        mApplication = (FitevalApplication) getApplicationContext();
        mApplication.setUserDto(new UserDto("hyunmoon", "Henry Moon", 1200, 3000,
                "head_1", "body_1", "weapon_1", "foot_1"));
    }

    /**
     * Starts MainActivity
     */
    private void startMainActivity() {
        boolean isUserAuthenticated = false;
        Intent intent;
        if (isUserAuthenticated) {
            intent = new Intent(IntroActivity.this, MainActivity.class);
        } else {
            intent = new Intent(IntroActivity.this, LoginActivity.class);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.activity_start_enter, R.anim.activity_start_exit);
        finish();
    }
}