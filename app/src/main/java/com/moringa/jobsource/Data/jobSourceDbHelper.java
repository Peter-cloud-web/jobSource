package com.moringa.jobsource.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.moringa.jobsource.Data.jobSourceContract.*;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import com.moringa.jobsource.Activity.CatalogueActivity;
import com.moringa.jobsource.Data.jobSourceContract;
import static com.moringa.jobsource.Data.jobSourceContract.ProfileEntry.COLUMN_SP_ID;
import static com.moringa.jobsource.Data.jobSourceContract.verifiedworkers.COLUMN_SW_ID;
import static java.sql.Types.INTEGER;

public class jobSourceDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    static final  String DATABASE_NAME = "jobSource.db";

    public jobSourceDbHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final  String SQL_CREATE_REGISTERSERVICEPROVIDER_TABLE = "CREATE TABLE " + RegisterEntry.TABLE_NAME +"(" +
                RegisterEntry._ID + "INTEGER PRIMARY KEY," +

                RegisterEntry.COLUMN_EMAIL + " TEXT UNIQUE NOT NULL," +
                RegisterEntry.COLUMN_FIRST_NAME + " TEXT NOT NULL," +
                RegisterEntry.COLUMN_LAST_NAME + " TEXT NOT NULL," + RegisterEntry.COLUMN_PASSWORD + " TEXT UNIQUE NOT NULL" +
                ");";

        final  String SQL_CREATE_PROFILESERVICEPROVIDER_TABLE = "CREATE TABLE " + ProfileEntry.TABLE_NAME +"(" +
                ProfileEntry._ID + " INTEGER PRIMARY KEY," +
                ProfileEntry.COLUMN_SP_ID + " INTEGER NOT NULL," +
                ProfileEntry.COLUMN_PROFILEPICTURE + " BLOB," +
                ProfileEntry.COLUMN_SERVICE + " TEXT UNIQUE NOT NULL," +
                ProfileEntry.COLUMN_LOCATION + " TEXT NOT NULL," +
                ProfileEntry.COLUMN_EXPERIENCE + " TEXT NOT NULL," +
                ProfileEntry.COLUMN_PHONENUMBER + " NOT NULL, "+

                "FOREIGN KEY ("+ COLUMN_SP_ID + ") REFERENCES " +
                RegisterEntry.TABLE_NAME + " (" + RegisterEntry._ID + ");";

        final  String SQL_CREATE_FIRSTPROJECTSERVICEPROVIDER_TABLE = "CREATE TABLE " + Project1Entry.TABLE_NAME +"(" +
                Project1Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Project1Entry.COLUMN_SP_ID + " INTEGER NOT NULL," +
                Project1Entry.COLUMN_NAME + "TEXT NOT NULL," +
                Project1Entry.COLUMN_DESC + "TEXT NOT NULL," +
                Project1Entry.COLUMN_LINK + " TEXT NOT NULL,"+

                " FOREIGN KEY ("+ COLUMN_SP_ID + ") REFERENCES " +
             RegisterEntry.TABLE_NAME + " (" + RegisterEntry._ID + ");";

        final  String SQL_CREATE_SECONDPROJECTSERVICEPROVIDER_TABLE = " CREATE TABLE " + Project2Entry.TABLE_NAME +"(" +
                Project2Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Project2Entry.COLUMN_SP_ID + " INTEGER NOT NULL," +
                Project2Entry.COLUMN_NAME + " TEXT UNIQUE NOT NULL," +
                Project2Entry.COLUMN_DESC + " TEXT NOT NULL," +
                Project2Entry.COLUMN_LINK + " TEXT NOT NULL," +

                "FOREIGN KEY ("+ COLUMN_SP_ID + ") REFERENCES " +
                jobSourceContract.RegisterEntry.TABLE_NAME + " (" + RegisterEntry._ID + ");";

        final  String SQL_CREATE_THIRDPROJECTSERVICEPROVIDER_TABLE = " CREATE TABLE " + Project3Entry.TABLE_NAME +"(" +
                Project3Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Project3Entry.COLUMN_SP_ID + " INTEGER NOT NULL," +
                Project3Entry.COLUMN_NAME + "TEXT UNIQUE NOT NULL," +
                Project3Entry.COLUMN_DESC + "TEXT NOT NULL," +
                Project3Entry.COLUMN_LINK + " TEXT NOT NULL, " +

                "FOREIGN KEY ("+ COLUMN_SP_ID + ") REFERENCES " +
            RegisterEntry.TABLE_NAME + " (" + RegisterEntry._ID + ");";

        final String SQL_CREATE_CLIENTDETAILS_TABLE = " CREATE TABLE " + ClientDetails.TABLE_NAME + "(" +
               ClientDetails._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
               ClientDetails.COLUMN_EMAIL + " TEXT NOT NULL," +
               ClientDetails.COLUMN_FNAME + " TEXT NOT NULL," +
               ClientDetails.COLUMN_LNAME + " TEXT NOT NULL," +
               ClientDetails.COLUMN_PHONENUMBER +"TEXT NOT NULL" +
                ");";

        final  String SQL_CREATE_VERIFIEDWORKERS = "CREATE TABLE " + verifiedworkers.TABLE_NAME +"(" +
               verifiedworkers._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
               verifiedworkers.COLUMN_SP_ID + " INTEGER NOT NULL " +
                COLUMN_SW_ID + "INTEGER NOT NULL" +

                "FOREIGN KEY ("+ COLUMN_SP_ID + ") REFERENCES " +
                RegisterEntry.TABLE_NAME + " (" + RegisterEntry._ID +

               "FOREIGN KEY ("+ COLUMN_SW_ID + ") REFERENCES " +
                verifiedworkers.TABLE_NAME + " (" + verifiedworkers._ID + ");";


        sqLiteDatabase.execSQL(SQL_CREATE_CLIENTDETAILS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_FIRSTPROJECTSERVICEPROVIDER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_PROFILESERVICEPROVIDER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_REGISTERSERVICEPROVIDER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_SECONDPROJECTSERVICEPROVIDER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_THIRDPROJECTSERVICEPROVIDER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_VERIFIEDWORKERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RegisterEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ProfileEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Project1Entry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Project2Entry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Project3Entry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Project3Entry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ClientDetails.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + verifiedworkers.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
