package com.moringa.jobsource.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.moringa.jobsource.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Profile_Worker extends AppCompatActivity {
    ImageView profilepicture;
    ImageView editprofilephoto;
    TextView FirstName;
    TextView LastName;
    TextView phoneNumber;
    TextView serviceName;
    TextView location;
    TextView experience;
    TextView project1Name;
    TextView project1Link;
    TextView project1Desc;
    TextView project2Name;
    TextView project2Link;
    TextView project2Desc;
    TextView project3Name;
    TextView project3Link;
    TextView project3Desc;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_worker);

        profilepicture = findViewById(R.id.profilepic);
        editprofilephoto = findViewById(R.id.editprofilephoto);
        FirstName = findViewById(R.id.displayFName);
        LastName = findViewById(R.id.displayLName);
        phoneNumber = findViewById(R.id.displayPhonenumber);
        serviceName = findViewById(R.id.displayServicename);
        location = findViewById(R.id.displaylocation);
        experience = findViewById(R.id.displayExperience);
        project1Name = findViewById(R.id.displayProject1name);
        project1Link = findViewById(R.id.displayProject1link);
        project1Desc = findViewById(R.id.displayProject1desc);
        project2Name = findViewById(R.id.displayProject2name);
        project2Link = findViewById(R.id.displayProject2link);
        project2Desc = findViewById(R.id.displayProject2desc);
        project3Name = findViewById(R.id.displayProject3name);
        project3Link = findViewById(R.id.displayProject3link);
        project3Desc = findViewById(R.id.displayProject3desc);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child(firebaseAuth.getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Picasso.get().load(uri).fit().centerInside().into(profilepicture);
            }
        });
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(),Register.class));
        }
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Userinformation userProfile = dataSnapshot.getValue(Userinformation.class);
                FirstName.setText(userProfile.getfName());
                LastName.setText(userProfile.getlName());
                phoneNumber.setText(userProfile.getCell());
                serviceName.setText(userProfile.getServName());
                location.setText(userProfile.getMlocation());
                experience.setText(userProfile.getmExperience());
                project1Name.setText(userProfile.getmProject1Name());
                project1Desc.setText(userProfile.getmProject1Desc());
                project1Link.setText(userProfile.getmProject1Link());
                project2Name.setText(userProfile.getmProject2Name());
                project2Desc.setText(userProfile.getmProject2Desc());
                project2Link.setText(userProfile.getmProject2Link());
                project3Name.setText(userProfile.getmProject3Name());
                project3Desc.setText(userProfile.getmProject3Desc());
                project3Link.setText(userProfile.getmProject3Link());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Profile_Worker.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
