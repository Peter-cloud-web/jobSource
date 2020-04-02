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

public class Profile_Worker extends AppCompatActivity {
    @BindView(R.id.profilephoto) ImageView profilephoto;
    @BindView(R.id.editprofilephoto) ImageView editprofilephoto;
    @BindView(R.id.displayNames) TextView displayName;
    @BindView(R.id.displayEmail) TextView displayEmail;
    @BindView(R.id.displayPhonenumber) TextView displayPhoneNumber;
    @BindView(R.id.displayServicename) TextView displayServiceName;
    @BindView(R.id.displaylocation) TextView displayLocation;
    @BindView(R.id.displayExperience) TextView displayExperience;
    @BindView(R.id.displayProject1name) TextView displayProject1Name;
    @BindView(R.id.displayProject1link) TextView displayProject1Link;
    @BindView(R.id.displayProject1desc) TextView displayProject1desc;
    @BindView(R.id.displayProject2name) TextView displayProject2Name;
    @BindView(R.id.displayProject2link) TextView displayProject2Link;
    @BindView(R.id.displayProject2desc) TextView displayProject2desc;
    @BindView(R.id.displayProject3name) TextView displayProject3Name;
    @BindView(R.id.displayProject3link) TextView displayProject3Link;
    @BindView(R.id.displayProject3desc) TextView displayProject3desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_worker);
        ButterKnife.bind(this);
    }

}
