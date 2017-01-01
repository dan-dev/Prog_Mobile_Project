package com.example.danny.prog_mobile_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLData;
import java.util.ArrayList;

public class DataBaseBuilder extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "DatabaseTest";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_SERIE = "serie";
    private static final String COLUMN_SERIE_ID = "id";
    private static final String COLUMN_SERIE_STATE = "state";

    private static final String LOG = "DataBaseBuilder";

    public DataBaseBuilder(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DataBaseBuilder(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, SQLiteDatabase liteDatabase) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //SQLiteDatabase liteDatabase;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_SERIES_TABLE = "CREATE TABLE " + TABLE_SERIE
                + "(" + COLUMN_SERIE_ID + "INTEGER PRIMARY KEY, "
                + COLUMN_SERIE_STATE + "TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_SERIES_TABLE);


        /*sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS series (serie VARCHAR, state VARCHAR, count INTEGER);");
        sqLiteDatabase.execSQL("INSERT INTO series (serie, state, count) VALUES ('Scrubs', 'Finished', 15);");
        sqLiteDatabase.execSQL("INSERT INTO series (serie, state, count) VALUES ('Scrubs', 'Finished', 15);");
        sqLiteDatabase.execSQL("INSERT INTO series (serie, state, count) VALUES ('Scrubs', 'Finished', 15);");
        sqLiteDatabase.execSQL("INSERT INTO series (serie, state, count) VALUES ('Scrubs', 'Finished', 15);");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SERIE);
        onCreate(sqLiteDatabase);
    }

    public void addSerie(Serie serie){
        ContentValues values = new ContentValues();
        values.put(COLUMN_SERIE_STATE, serie.getState());
        SQLiteDatabase database = this.getWritableDatabase();
        database.insert(TABLE_SERIE, null, values);
        database.close();
    }

    public ArrayList<Serie> getSeries(String serie){
        ArrayList<Serie> list = new ArrayList<Serie>();
        String query = "SELECT .* FROM " + TABLE_SERIE;
        SQLiteDatabase sq = this.getWritableDatabase();
        Cursor cursor = sq.rawQuery(query, null);
        while (cursor.moveToNext()){
            Serie ser = new Serie();
            ser.setId(cursor.getInt(0));
            ser.setState(cursor.getString(1));
            list.add(ser);
        }
        return list;
    }

}