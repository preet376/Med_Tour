package com.jaypal.navigation_drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
    TextInputLayout name,reg_username,reg_password,reg_email,phoneNo;
    Button signUp;
    String email,password;
    AlertDialog.Builder builder;

    //ProgressBar pBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
      //  getSupportActionBar().hide();
        reg_email = (TextInputLayout) findViewById(R.id.eMail);
        reg_password = (TextInputLayout) findViewById(R.id.passWord);
        reg_username = (TextInputLayout) findViewById(R.id.userName);

    }
    public void Signup(View view)
    {
         email = reg_email.getEditText().getText().toString();
         password = reg_password.getEditText().getText().toString();
        String username = reg_username.getEditText().getText().toString();
        //pBar.setVisibility(View.VISIBLE);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        if(password.length() <= 6)
        {
            //Toast.makeText(getApplicationContext(),"Password too short",Toast.LENGTH_LONG).show();
            reg_password.setError("Password too short");
            return;
        }

        if(username.length() > 15)
        {
            reg_username.setError("Username too long");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful())
                        {
                            mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        reg_password.getEditText().setText("");
                                        Toast.makeText(getApplicationContext(),"Registration Successfull... Please Verify Your Email",Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(signup.this,login.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                            //pBar.setVisibility(View.INVISIBLE);
                            //reg_email.getEditText().setText("");

                            //builder.setMessage("do u want to login with same credential?") .setTitle("Registered Successfully");
//                            Intent intent = new Intent(signup.this,login.class);
//                            startActivity(intent);
//                            finish();

                        }
                        else
                        {
                            //pBar.setVisibility(View.INVISIBLE);
                            //reg_email.getEditText().setText("");
                            reg_password.getEditText().setText("");
                            Toast.makeText(getApplicationContext(),"Registration Not Successfull",Toast.LENGTH_LONG).show();

                        }

                        // ...
                    }
                });



    }
//    void  loginuser()
//    {
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful())
//                        {
//                            // Use intent here to next activity after login successfull
//                       //     reg_email.getEditText().setText("");
//                         //   reg_password.getEditText().setText("");
//                            Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_LONG).show();
//                            Intent intent = new Intent(signup.this,MainActivity.class);
//                            startActivity(intent);
//                        }
//                        else
//                        {
//                           // reg_email.getEditText().setText("");
//                            //reg_password.getEditText().setText("");
//                          //  Toast.makeText(getApplicationContext(),"Login Not Successfull",Toast.LENGTH_LONG).show();
//
//                        }
//
//                        // ...
//                    }
//                });
//
//    }
}