package com.fiteval.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.fiteval.R;
import com.fiteval.ui.dialog.SimpleAlertDialog;
import com.fiteval.util.MiscUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

/**
 * Created by Mikias Alemu on 11/01/2016.
 */
public class LoginActivity extends Activity implements MediaPlayer.OnPreparedListener {

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
    private MediaPlayer mMediaplayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUtils = new MiscUtil(this);
        mDialog = new SimpleAlertDialog(this);
        mAuth = FirebaseAuth.getInstance();
        initView();
        //new
        mMediaplayer = new MediaPlayer();
        mMediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        fetchAudioUrlFromFirebase();
    }

    private void fetchAudioUrlFromFirebase() {
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReferenceFromUrl("gs://fiteval-89566.appspot.com/sounds/warrior.mp3");
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                try {
                    // Download url of file
                    final String url = uri.toString();
                    mMediaplayer.setDataSource(url);
                    // wait for media player to get prepare
                    mMediaplayer.setOnPreparedListener(LoginActivity.this);
                    mMediaplayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("TAG", e.getMessage());
                    }
                });
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //mp.setLooping(true);
        mp.start();
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
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
