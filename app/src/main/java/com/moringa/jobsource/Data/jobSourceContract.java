package com.moringa.jobsource.Data;

import android.net.Uri;
import android.provider.BaseColumns;

import java.sql.Blob;

public class jobSourceContract {

    public static final String CONTENT_AUTHORITY = "com.example.moringa.jobsource";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private jobSourceContract(){}

    public static final class RegisterEntry implements BaseColumns{

        public final static String TABLE_NAME = "registerserviceprovider";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_FIRST_NAME = "firstname";
        public final static String COLUMN_LAST_NAME = "lastname";
        public final static String COLUMN_EMAIL = "email";
        public final static String COLUMN_PASSWORD = "password";
    }

    public static final class ProfileEntry implements BaseColumns{

        public final static String TABLE_NAME = "profileserviceprovider";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_SP_ID = "sp_id";
        public final static String COLUMN_PROFILEPICTURE = "profilepicture";
        public final static String COLUMN_PHONENUMBER = "phonenumber";
        public final static String COLUMN_SERVICE = "service";
        public final static String COLUMN_LOCATION = "location";
        public final static String COLUMN_EXPERIENCE = "experience";
    }

    public static final class Project1Entry implements BaseColumns{

        public final static String TABLE_NAME = "firstprojectserviceprovider";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_SP_ID = "sp_id";
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_DESC= "DESC";
        public final static String COLUMN_LINK = "link";
    }

    public static final class Project2Entry implements BaseColumns{

        public final static String TABLE_NAME = "secondprojectserviceprovider";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_SP_ID = "sp_id";
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_DESC= "DESC";
        public final static String COLUMN_LINK = "link";
    }

    public static final class Project3Entry implements BaseColumns{

        public final static String TABLE_NAME = "thirdprojectserviceprovider";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_SP_ID = "sp_id";
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_DESC= "DESC";
        public final static String COLUMN_LINK = "link";
    }

    public static final class ClientDetails implements BaseColumns{

        public final static String TABLE_NAME = "clientdetails";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_FNAME = "fname";
        public final static String COLUMN_LNAME= "lname";
        public final static String COLUMN_EMAIL = "email";
        public final static String COLUMN_PHONENUMBER = "phonenumber";
    }

    public static final class verifiedworkers implements BaseColumns{

        public final static String TABLE_NAME = "verifiedworkers";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_SP_ID = "sp_id";
        public final static String COLUMN_SW_ID = "sw_id";
    }
}
