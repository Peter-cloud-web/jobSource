package com.moringa.jobsource.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.moringa.jobsource.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Register extends AppCompatActivity implements View.OnClickListener {

    EditText mail;
    EditText mPasscode;
    TextView member;
    Button signIn;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();

        member = findViewById(R.id.AlreadyMember);
        signIn = findViewById(R.id.registerUser);
        mail = findViewById(R.id.mail);
        mPasscode = findViewById(R.id.passwords);

        signIn.setOnClickListener(this);
        member.setOnClickListener(this);
    }

        @Override
         public void  onClick(View v){
        if(v == signIn){
            saveDetails();
         }
        if(v == member){
            openLogin();
        }

    }
      private void openLogin() {
        Intent intent = new Intent(Register.this, Login.class);
        Toast.makeText(Register.this,"Welcome to the registry panel " ,Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
            private void saveDetails() {

                String mEmail = mail.getText().toString().trim();
                String mPassword = mPasscode.getText().toString().trim();

                if(TextUtils.isEmpty(mEmail)){
                    Toast.makeText(getApplicationContext(),"Please enter your Email address",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(mPassword)){
                    Toast.makeText(getApplicationContext(),"Please enter your password",Toast.LENGTH_LONG).show();
                    return;
                }
                if (mPassword.length() == 0){
                    Toast.makeText(getApplicationContext(),"Please enter your Password",Toast.LENGTH_LONG).show();
                }
                if (mPassword.length() < 8){
                    Toast.makeText(getApplicationContext(),"Password must be more than 8 digit",Toast.LENGTH_LONG).show();
                }
                else{

                    auth.createUserWithEmailAndPassword(mEmail,mPassword)

                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (!task.isSuccessful()) {
                                        Toast.makeText(Register.this, "An error occured.Try again later",Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        Toast.makeText(Register.this, "Account created successfully",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(Register.this, CreateProfile_Worker.class));
                                        finish();
                                    }
                                }
                            });
                }
        }


    }


