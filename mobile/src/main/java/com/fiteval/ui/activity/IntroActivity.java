package com.fiteval.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fiteval.R;
import com.fiteval.config.FitevalApplication;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        int loadingTime = 2000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        }, loadingTime);
    }

    /**
     * Starts MainActivity
     */
    private void startMainActivity() {
        boolean isUserAuthenticated = false;
        Intent intent;
        if (isUserAuthenticated) {
            intent = new Intent(IntroActivity.this, MainActivity.class);
        }
        else {
            intent = new Intent(IntroActivity.this, LoginActivity.class);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.activity_start_enter, R.anim.activity_start_exit);
        finish();
    }
}