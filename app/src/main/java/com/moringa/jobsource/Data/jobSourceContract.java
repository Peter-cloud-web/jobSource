package com.moringa.jobsource.Data;

import android.provider.BaseColumns;

public class jobSourceContract {

    public static final String CONTENT_AUTHORITY = "com.example.moringa.jobsource";

    private jobSourceContract(){}

    public static final class RegisterEntry implements BaseColumns{
        public final static String TABLE_NAME = "register";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_FIRST_NAME = "firstname";
        public final static String COLUMN_LAST_NAME = "lastname";
        public final static String COLUMN_EMAIL_NAME = "email";
        public final static String COLUMN_PASSWORD_NAME = "password";


    }


}
