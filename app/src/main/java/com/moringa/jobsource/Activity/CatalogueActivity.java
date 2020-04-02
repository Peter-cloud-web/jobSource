package com.moringa.jobsource.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.moringa.jobsource.Data.jobSourceContract;
import com.moringa.jobsource.Data.jobSourceDbHelper;
import com.moringa.jobsource.R;

public class CatalogueActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue);
        // Setup FAB to open EditorActivity
        Button fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogueActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        displayDatabaseInfo();
    }
     private void displayDatabaseInfo(){

         jobSourceDbHelper DbHelper = new jobSourceDbHelper(this);

         SQLiteDatabase sqLiteDatabase = DbHelper.getReadableDatabase();

         Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + jobSourceContract.RegisterEntry.TABLE_NAME, null);

         try{
             TextView displayView = (TextView) findViewById(R.id.textData);
             displayView.setText("Number of rows in register database table:" + cursor.getCount());
         }finally {
             cursor.close();
         }
     }

 }