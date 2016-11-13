package com.fiteval.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.fiteval.R;
import com.fiteval.ui.dialog.SimpleAlertDialog;
import com.fiteval.util.MiscUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 *
 */
public class LoginActivity extends Activity {

    private Button mSignIn;
    private Button mSignUp;
    private Button mForgotPassword;
    private EditText mEtEmail;
    private EditText mEtPassword;
    private ImageButton mBtnEmailClear;
    private ImageButton mBtnPasswordClear;
    private ProgressDialog mProgress;

    private FirebaseAuth mAuth;
    private MiscUtil mUtils;
    private SimpleAlertDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUtils = new MiscUtil(this);
        mDialog = new SimpleAlertDialog(this);
        mAuth = FirebaseAuth.getInstance();
        initView();

    }

    private void initView() {
        mSignIn = (Button) findViewById(R.id.signin);
        mSignUp = (Button) findViewById(R.id.signup);
        mForgotPassword = (Button) findViewById(R.id.forgot);
        mEtEmail = (EditText) findViewById(R.id.login_et_userId);
        mEtPassword = (EditText) findViewById(R.id.login_et_userPass);
        mBtnEmailClear = (ImageButton) findViewById(R.id.login_btn_idClear);
        mBtnPasswordClear = (ImageButton) findViewById(R.id.login_btn_pwClear);


        mProgress = new ProgressDialog(this);

        mBtnEmailClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtEmail.setText("");
            }
        });
        mBtnPasswordClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtPassword.setText("");
            }
        });

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        mForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     *
     */
    private void signIn() {
        //Getting Email and password from users
        String email = mEtEmail.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            mDialog.show("Please enter email address");
            mUtils.showKeyboardFrom(this, mEtEmail);
            return;
        }

        if (TextUtils.isEmpty(password)){
            mDialog.show("Please enter password");
            mUtils.showKeyboardFrom(this, mEtPassword);
            return;
        }

        //If email and password provided display progress dialog
        mProgress.setMessage("Signing in please wait.....");
        mProgress.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgress.cancel();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                //overridePendingTransition(R.anim.activity_start_enter, R.anim.activity_start_exit);
                finish();
            }
        }, 1000);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this,
                new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Successfully signed in", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this,"There was an error....", Toast.LENGTH_LONG).show();

                }


            }
        });
    }

}
