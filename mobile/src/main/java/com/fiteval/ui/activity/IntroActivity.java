package com.fiteval.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.fiteval.R;

/**
 * Created by TravisNguyen on 11/3/16.
 */

//splash load screen to intro Fiteval

public class IntroActivity extends Activity
{
    //adjust splash time
    private final int SPLASH_DISPLAY_LENTH = 2000;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                IntroActivity.this.startActivity(intent);
                IntroActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENTH);

    }



}

