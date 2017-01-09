package com.example.danny.prog_mobile_project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DataBaseBuilder extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "DatabaseTest";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_SERIE = "serie";
    private static final String COLUMN_SERIE_ID = "id";
    private static final String COLUMN_SERIE_STATE = "state";
    private static final String COLUMN_SERIE_NAME = "name";

    private static final String LOG = "DataBaseBuilder";

    public DataBaseBuilder(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DataBaseBuilder(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, SQLiteDatabase liteDatabase) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_SERIES_TABLE = "CREATE TABLE " + TABLE_SERIE
                + "( " + COLUMN_SERIE_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_SERIE_STATE + " TEXT, "
                + COLUMN_SERIE_NAME + " TEXT "
                + " )";
        sqLiteDatabase.execSQL(CREATE_SERIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SERIE);
        onCreate(sqLiteDatabase);
    }

    public void addSerie(Serie serie){
        if(checkIfExists(serie)){
            updateSerie(serie);
        }
        else {
            addNewSerie(serie);
        }
    }

    public void addNewSerie(Serie serie){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "INSERT INTO " + TABLE_SERIE + "( " + COLUMN_SERIE_ID + ", "
                + COLUMN_SERIE_STATE + ", " + COLUMN_SERIE_NAME
                + ") VALUES (" + serie.getId() + ", '" + serie.getState()
                + "', '" + serie.getName() + "');";
        database.execSQL(query);
        database.close();
    }

    public void updateSerie(Serie serie){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_SERIE + " SET "
                + COLUMN_SERIE_STATE + " = '" + serie.getState()
                + "' WHERE " + COLUMN_SERIE_ID + " = " + serie.getId() + ";" ;
        database.execSQL(query);
        database.close();
    }

    public boolean checkIfExists(Serie serie){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SERIE
                + " WHERE " + COLUMN_SERIE_ID + " = " + serie.getId() + ";";
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayList<Serie> getSeries(){
        ArrayList<Serie> list = new ArrayList<Serie>();
        String query = "SELECT * FROM " + TABLE_SERIE;
        SQLiteDatabase sq = this.getWritableDatabase();
        Cursor cursor = sq.rawQuery(query, null);
        while (cursor.moveToNext()){
            Serie ser = new Serie();
            ser.setId(cursor.getInt(0));
            ser.setState(cursor.getString(1));
            ser.setName(cursor.getString(2));
            list.add(ser);
        }
        return list;
    }
}