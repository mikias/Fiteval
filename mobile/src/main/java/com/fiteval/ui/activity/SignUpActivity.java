package com.fiteval.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fiteval.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Mikias Alemu on 11/01/2016.
 */
public class SignUpActivity extends AppCompatActivity {

    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;


    //defining view objects
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignup;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
<<<<<<< HEAD
        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        //editTextAge = (EditText) findViewById(R.id.editTextAge);
        //spannerGender = (Spanner) find

=======
        buttonSignup = (Button) findViewById(R.id.buttonSignup);
>>>>>>> origin/henry-patch

        progressDialog = new ProgressDialog(this);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser() {

        //getting email and password from edit texts
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
<<<<<<< HEAD
        final String first_name = editTextFirstName.getText().toString().trim();
        final String last_name = editTextLastName.getText().toString().trim();
//        int age = Integer.parseInt(editTextAge.getText().toString().trim());
=======
>>>>>>> origin/henry-patch

        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

<<<<<<< HEAD
//        if (age <= 12) {
//            Toast.makeText(this, "Please enter a valid age", Toast.LENGTH_LONG).show();
//            return;
//        }
=======
        //if the email and password are not empty
        //displaying a progress dialog
>>>>>>> origin/henry-patch

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

<<<<<<< HEAD
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.cancel();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(R.anim.activity_start_enter, R.anim.activity_start_exit);
                finish();
            }
        }, 1000);
=======
>>>>>>> origin/henry-patch

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            //display some message here
<<<<<<< HEAD
                            Toast.makeText(SignUpActivity.this, "Successfully registered " + firebaseAuth.getCurrentUser().getUid().toString(), Toast.LENGTH_LONG).show();

                            String user_uid = firebaseAuth.getCurrentUser().getUid().toString();

                            User_Info u_info = new User_Info(user_uid, first_name, last_name, email);

                            mFirebaseDatabaseReference.child("user_info").push().setValue(u_info);

=======
                            Toast.makeText(SignUpActivity.this, "Successfully registered", Toast.LENGTH_LONG).show();
>>>>>>> origin/henry-patch
                        } else {
                            //display some message here
                            Toast.makeText(SignUpActivity.this, "Registration Error", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
<<<<<<< HEAD

      //  MainActivity.knight.setmAge(age);
=======
>>>>>>> origin/henry-patch
    }
}
