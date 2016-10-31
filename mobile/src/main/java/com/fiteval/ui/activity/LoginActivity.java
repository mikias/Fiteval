package com.fiteval.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fiteval.R;

public class LoginActivity extends AppCompatActivity {

    // Buttons, TextViews, etc...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    private void viewInit() {
        // findViewById
    }

    // launch after authentication
    private void launchMainActivity() {
        Intent passedIntent = getIntent();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

        startActivity(intent);
        overridePendingTransition(R.anim.activity_start_enter, R.anim.activity_start_exit);
        finish();
    }
}
