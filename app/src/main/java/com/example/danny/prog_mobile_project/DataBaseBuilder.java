package com.example.danny.prog_mobile_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseBuilder extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "DatabaseTest";
    private static final int DATABASE_VERSION = 1;

    SQLiteDatabase liteDatabase;

    public DataBaseBuilder(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS series (serie VARCHAR, state VARCHAR, count INTEGER);");
        sqLiteDatabase.execSQL("INSERT INTO series (serie, state, count) VALUES ('Scrubs', 'Finished', 15);");
        sqLiteDatabase.execSQL("INSERT INTO series (serie, state, count) VALUES ('Scrubs', 'Finished', 15);");
        sqLiteDatabase.execSQL("INSERT INTO series (serie, state, count) VALUES ('Scrubs', 'Finished', 15);");
        sqLiteDatabase.execSQL("INSERT INTO series (serie, state, count) VALUES ('Scrubs', 'Finished', 15);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}