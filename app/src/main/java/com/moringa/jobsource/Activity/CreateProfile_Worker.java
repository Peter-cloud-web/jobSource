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

public class CreateProfile_Worker extends AppCompatActivity {

    @BindView(R.id.profilephoto) ImageView profilephoto;
    @BindView(R.id.editprofilephoto) ImageView editprofilephoto;
    @BindView(R.id.displayName) TextView displayName;
    @BindView(R.id.displayEmail) TextView displayEmail;
    @BindView(R.id.phonenumber) EditText phoneNumber;
    @BindView(R.id.servicename) EditText serviceName;
    @BindView(R.id.location) EditText location;
    @BindView(R.id.experience) EditText experience;
    @BindView(R.id.project1name) EditText project1Name;
    @BindView(R.id.project1link) EditText project1Link;
    @BindView(R.id.project1desc) EditText project1Desc;
    @BindView(R.id.project2name) EditText project2Name;
    @BindView(R.id.project2link) EditText project2Link;
    @BindView(R.id.project2desc) EditText project2Desc;
    @BindView(R.id.project3name) EditText project3Name;
    @BindView(R.id.project3link) EditText project3Link;
    @BindView(R.id.project3desc) EditText project3Desc;
    @BindView(R.id.savedetails) Button saveDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile_serviceprovider);
        ButterKnife.bind(this);
    }
}
