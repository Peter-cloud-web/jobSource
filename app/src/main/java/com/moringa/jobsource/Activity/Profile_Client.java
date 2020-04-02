package com.moringa.jobsource.Activity;

import android.os.Bundle;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.moringa.jobsource.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Profile_Client extends AppCompatActivity {

    @BindView(R.id.Names) TextView displayNames;
    @BindView(R.id.fName) TextView displayFNames;
    @BindView(R.id.lName) TextView displayLName;
    @BindView(R.id.mobilenumber) TextView displayMobileNumber;
    @BindView(R.id.email) TextView displayEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_client);
        ButterKnife.bind(this);
    }
}
