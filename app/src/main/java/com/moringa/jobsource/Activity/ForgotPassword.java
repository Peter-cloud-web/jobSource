package com.moringa.jobsource.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.moringa.jobsource.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPassword extends AppCompatActivity {

    @BindView(R.id.reset) Button resetPassword;
    @BindView(R.id.email) EditText recoveryEmail;

    private FirebaseAuth auth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        ButterKnife.bind(this);

        resetPassword.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String thisEmail = recoveryEmail.getText().toString().trim();
                if(TextUtils.isEmpty(thisEmail)){
                    Toast.makeText(getApplicationContext(),"Enter your email address",Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.sendPasswordResetEmail(thisEmail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgotPassword.this, "Successfull!!,We sent you an e-mail to reset password", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),Login.class);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(ForgotPassword.this, "Error, Check your network connection", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                   }
              });
          }
      }
