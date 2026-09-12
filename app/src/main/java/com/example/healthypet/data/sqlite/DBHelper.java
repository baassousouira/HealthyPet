package com.example.healthypet.data.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "myHealthyPet.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS client ( id INTEGER PRIMARY KEY AUTOINCREMENT, pseudo TEXT, email TEXT, pswd TEXT) ");
        db.execSQL("CREATE TABLE IF NOT EXISTS pet ( id INTEGER PRIMARY KEY AUTOINCREMENT, clientId INT,  name TEXT, age INT, sexe TEXT, height DOUBLE, weight DOUBLE, type TEXT) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS client");
        db.execSQL("DROP TABLE IF EXISTS pet");
        onCreate(db);
    }
}