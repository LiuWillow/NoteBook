package com.example.liuwillow.notebook.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by liuwillow on 17-6-24.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context mContext;
    public static final String CREATE_NOTES_TABLE = "create table Notes ("
            + "id integer primary key autoincrement,"
            + "title text,"
            + "content text,"
            + "date text)";
    public static final String CREATE_DRAFTS_TABLE = "create table Drafts ("
            + "id integer primary key autoincrement,"
            + "title text,"
            + "content text,"
            + "date text )";
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTES_TABLE);
        db.execSQL(CREATE_DRAFTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Notes");
        db.execSQL("drop table if exists Drafts");
        onCreate(db);
    }
}
