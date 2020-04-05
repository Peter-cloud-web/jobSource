package com.moringa.jobsource.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.moringa.jobsource.R;

import java.io.IOException;
import java.lang.reflect.MalformedParameterizedTypeException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateProfile_Worker extends AppCompatActivity implements View.OnClickListener {

     ImageView profilephoto;
     ImageView editprofilephoto;
     EditText FirstName;
    EditText LastName;
    EditText phoneNumber;
    EditText serviceName;
    EditText location;
    EditText experience;
     EditText project1Name;
    EditText project1Link;
     EditText project1Desc;
    EditText project2Name;
    EditText project2Link;
    EditText project2Desc;
     EditText project3Name;
   EditText project3Link;
   EditText project3Desc;
    Button saveDetails;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private static int PICK_IMAGE = 123;
    Uri imagePath;
    private StorageReference storageReference;

    public CreateProfile_Worker() {

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
      if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null){
         imagePath = data.getData();
         try{
             Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
            profilephoto.setImageBitmap(bitmap);
          }catch(IOException e){
             e.printStackTrace();
         }
        }
       super.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile_serviceprovider);
        ButterKnife.bind(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(getApplicationContext(),Login.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        profilephoto = findViewById(R.id.profilephoto);
        editprofilephoto = findViewById(R.id.editprofilephoto);
        FirstName = findViewById(R.id.fName);
        LastName = findViewById(R.id.lastName);
        phoneNumber = findViewById(R.id.phonenumber);
        serviceName = findViewById(R.id.servicename);
        location = findViewById(R.id.location);
        experience = findViewById(R.id.experience);
        project1Name = findViewById(R.id.project1name);
        project1Link = findViewById(R.id.project1link);
        project1Desc = findViewById(R.id.project1desc);
        project2Name = findViewById(R.id.project2name);
        project2Link = findViewById(R.id.project2link);
        project2Desc = findViewById(R.id.project2desc);
        project3Name = findViewById(R.id.project3name);
        project3Link = findViewById(R.id.project3link);
        project3Desc = findViewById(R.id.project3desc);

        saveDetails = findViewById(R.id.savedetails);

        FirebaseUser user=firebaseAuth.getCurrentUser();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();


        editprofilephoto.setOnClickListener(this);
        saveDetails.setOnClickListener(this);


    }
    @Override
    public void onClick(View v){
        if(v == editprofilephoto){
            openGallery();
        }
        if(v == saveDetails){
            saveDetails();
        }

    }

    public void openGallery(){
                Intent openGallery = new Intent();
                openGallery.setType("image/*");
                openGallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(openGallery, "Select Image"),PICK_IMAGE);
    }


    private void userInformation(){
        String fName = FirstName.getText().toString().trim();
        String lName = LastName.getText().toString().trim();
        String cell  = phoneNumber.getText().toString().trim();
        String servName = serviceName.getText().toString().trim();
        String mlocation = location.getText().toString().trim();
        String mExperience = experience.getText().toString().trim();

        String mProject1Name = project1Name.getText().toString().trim();
        String mProject1Link = project1Link.getText().toString().trim();
        String mProject1Desc = project1Desc.getText().toString().trim();

        String mProject2Name = project2Name.getText().toString().trim();
        String mProject2Link = project2Link.getText().toString().trim();
        String mProject2Desc = project2Desc.getText().toString().trim();

        String mProject3Name = project3Name.getText().toString().trim();
        String mProject3Link = project3Link.getText().toString().trim();
        String mProject3Desc = project3Desc.getText().toString().trim();
        Userinformation userinformation = new Userinformation(fName,lName,cell,servName,mlocation,mExperience,mProject1Name,mProject1Link,mProject1Desc,mProject2Name,mProject2Link,mProject2Desc,mProject3Name,mProject3Link,mProject3Desc);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userinformation);
        Toast.makeText(getApplicationContext(),"User information updated",Toast.LENGTH_LONG).show();
    }

    public void saveDetails(){
            if(imagePath == null){
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();


                Drawable drawable = this.getResources().getDrawable(R.drawable.account);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.account);
                userInformation();
                finish();
                startActivity(new Intent(CreateProfile_Worker.this,MainActivity.class));
            }else{
                userInformation();
                sendUserData();
                finish();
                startActivity(new Intent(CreateProfile_Worker.this,MainActivity.class));
            }
        }
    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("images");
        UploadTask uploadTask = imageReference.putFile(imagePath);
        uploadTask.addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateProfile_Worker.this,"Error: Uploading profile picture",Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(CreateProfile_Worker.this,"Profile picture uploaded",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void openSelectProfilePictureDialog(){
        AlertDialog alertDialogue  =new AlertDialog.Builder(this).create();
        TextView title = new TextView(this);
        title.setText("Profile Picture");
        title.setPadding(10,10,10,10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.BLACK);
        title.setTextSize(20);

        alertDialogue.setCustomTitle(title);
        TextView msg = new TextView(this);
        msg.setText("Please select a profile picture");
        msg.setGravity(Gravity.CENTER_HORIZONTAL);
        msg.setTextColor(Color.BLACK);
        alertDialogue.setView(msg);
        alertDialogue.setButton(AlertDialog.BUTTON_NEUTRAL,"OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        new Dialog(getApplicationContext());
        alertDialogue.show();
        final Button okBT = alertDialogue.getButton(AlertDialog.BUTTON_NEUTRAL);
        LinearLayout.LayoutParams neutralBtnLP = (LinearLayout.LayoutParams) okBT.getLayoutParams();
        neutralBtnLP.gravity = Gravity.FILL_HORIZONTAL;
        okBT.setPadding(50, 10, 10, 10);   // Set Position
        okBT.setTextColor(Color.BLUE);
        okBT.setLayoutParams(neutralBtnLP);
    }
}
