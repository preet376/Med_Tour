package com.jaypal.navigation_drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    private static final Object TAG = "login";
    Button callSignUp,Signin,fgtPass;
    TextInputLayout reg_password,reg_email;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Signin = (Button) findViewById(R.id.ssignIn);
        callSignUp = (Button) findViewById(R.id.sbtnSignup);
        reg_email = (TextInputLayout) findViewById(R.id.slogin_email);
        reg_password = (TextInputLayout) findViewById(R.id.slogin_password);
        fgtPass = (Button) findViewById(R.id.sbtn_fgt);
//        user = mAuth.getCurrentUser();

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,signup.class);
                startActivity(intent);
            }
        });
    }

    public void SignIn(View view)
    {
        String email = reg_email.getEditText().getText().toString();
        String password = reg_password.getEditText().getText().toString();
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            if(mAuth.getCurrentUser().isEmailVerified())
                            {
                                reg_email.getEditText().setText("");
                                reg_password.getEditText().setText("");
                                Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(login.this,MainActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Please Veify Email",Toast.LENGTH_LONG).show();
                            }
                            // Use intent here to next activity after login successfull
//                            reg_email.getEditText().setText("");
//                            reg_password.getEditText().setText("");
//                            Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_LONG).show();
//                            Intent intent = new Intent(login.this,MainActivity.class);
//                            startActivity(intent);
                        }
                        else
                        {
                            reg_email.getEditText().setText("");
                            reg_password.getEditText().setText("");
                            Toast.makeText(getApplicationContext(),"Login Not Successfull",Toast.LENGTH_LONG).show();

                        }

                        // ...
                    }
                });


    }

    public void resetPassword(View view) {

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        final EditText resetPass = new EditText(view.getContext());
        final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
        passwordResetDialog.setTitle("Reset Password ?");
        passwordResetDialog.setMessage("Enter New Password(Greater than 6)");
        passwordResetDialog.setView(resetPass);

        passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String newPassword = resetPass.getText().toString();
                user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Password Reset Successfull",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Password Reset Failed",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        passwordResetDialog.create().show();


    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            // User is signed in
//            Intent i = new Intent(login.this, MainActivity.class);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(i);
//        } else {
//            // User is signed out
//            Log.d((String) TAG, "onAuthStateChanged:signed_out");
//        }
//    }
}