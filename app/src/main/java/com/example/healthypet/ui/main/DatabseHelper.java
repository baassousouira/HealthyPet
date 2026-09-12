package com.example.healthypet.ui.main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// !!!! CELLE CI EST FINI FAUDRA SUREMENT MODIFIER QUAND ON AURA LES PAGES SUIVANTES
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


// HERE IS THE CLASS THAT MANAGE THE DATABASE OPERATIONS
public class DatabseHelper extends SQLiteOpenHelper {

    // NAME OF DATABASE :
    private static final String DATABSE_NAME = "user_databse";
    // this one is to increment whenever we modify the structure of database
    private static final int DATABSE_VERSION = 1;

    //we set the table and column names :
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_PET_NAME = "pet_name";
    private static final String COLUMN_PET_AGE = "pet_age";
    private static final String COLUMN_PET_SEX = "pet_sex";
    private static final String COLUMN_PET_HEIGHT = "pet_height";
    private static final String COLUMN_PET_WEIGHT = "pet_weight";
    private static final String COLUMN_PET_TYPE = "pet_type";


    // this is the constructor
    public DatabseHelper(Context context) {
        super(context, DATABSE_NAME, null, DATABSE_VERSION);
    }


    // this one is called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_USERNAME + " TEXT PRIMARY KEY, " +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_PET_NAME + " TEXT," +
                COLUMN_PET_AGE + " INTEGER," +
                COLUMN_PET_SEX + " TEXT," +
                COLUMN_PET_HEIGHT + " INTEGER," +
                // real is better than integer for weight datas bcs of decimal
                COLUMN_PET_WEIGHT + " REAL," +
                COLUMN_PET_TYPE + " TEXT," ;
        db.execSQL(createTableQuery);
    }

    // this one is called when the database needs to be upgraded / modified
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // it drop the existing table and recreate it
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    // HERE it used to insert a new user into the database
    // same as for double : better for decimal
    public void insertUser (String username, String email, String Password, String petName, int petAge, String petSex, int petHeight, double petWeight, String petType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, Password);
        values.put(COLUMN_PET_NAME, petName);
        values.put(COLUMN_PET_AGE, petAge);
        values.put(COLUMN_PET_SEX, petSex);
        values.put(COLUMN_PET_HEIGHT, petHeight);
        values.put(COLUMN_PET_WEIGHT, petWeight);
        values.put(COLUMN_PET_TYPE, petType);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    // This one check if a user with the given username and password exists in the database
    public boolean checkUserExistence(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USERNAME + "= ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }


    // and this one is used to display a toast message
    public void displayToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
