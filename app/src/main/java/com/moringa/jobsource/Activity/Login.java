package com.moringa.jobsource.Activity;

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
import com.google.firebase.database.FirebaseDatabase;
import com.moringa.jobsource.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Login extends AppCompatActivity implements View.OnClickListener  {
    EditText email;
    EditText password;
    TextView forgotPassword;
    Button mLogin;
    Button mRegister;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mLogin = findViewById(R.id.login);
        mRegister = findViewById(R.id.register);
        email = findViewById(R.id.emails);
        password = findViewById(R.id.passwords);
        forgotPassword = findViewById(R.id.forgotpassword);

        mLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

    }
        @Override
        public void onClick(View v) {
        if(v == mLogin){
            openLogin();
        }
            if(v == mRegister){
                openRegister();
            }
            if(v == forgotPassword){
                openForgetPassword();
            }

        }
             private void openRegister() {
                Intent intent = new Intent(Login.this, Register.class);
                Toast.makeText(Login.this,"Welcome to the registry panel " ,Toast.LENGTH_LONG).show();
                 startActivity(intent);
             }
              private void openForgetPassword() {
              Intent intent = new Intent(Login.this, ForgotPassword.class);
             Toast.makeText(Login.this,"Welcome to the registry panel " ,Toast.LENGTH_LONG).show();
             startActivity(intent);
             }
            public void openLogin() {
                String mail = email.getText().toString();
                final String passcode = password.getText().toString();

                if(TextUtils.isEmpty(mail)){
                    Toast.makeText(getApplicationContext(),"Enter your email address",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(passcode)){
                    Toast.makeText(getApplicationContext(),"Enter your password",Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(mail, passcode)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"Error,wrong email or password",Toast.LENGTH_SHORT).show();
                                    if (password.length() < 8) {
                                        Toast.makeText(getApplicationContext(),"Password must be more than 8 digit",Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }

}
