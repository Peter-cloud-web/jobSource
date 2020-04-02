package com.moringa.jobsource.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.moringa.jobsource.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateProfile_Client extends AppCompatActivity {


    @BindView(R.id.FName) EditText fName;
    @BindView(R.id.LName) EditText lName;
    @BindView(R.id.email) EditText email;
    @BindView(R.id.mobile) EditText mobile;
    @BindView(R.id.savedetails) Button saveDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile_client);
        ButterKnife.bind(this);
    }

}
