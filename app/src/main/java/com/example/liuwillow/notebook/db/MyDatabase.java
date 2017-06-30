package com.example.liuwillow.notebook.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.liuwillow.notebook.bean.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuwillow on 17-6-24.
 */

public class MyDatabase {
    private static MyDatabase myDatabase;
    private SQLiteDatabase db;
    public static final int VERSION = 2;
    private static final String TAG = "MyDatabase";
    private MyDatabase(Context context){
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context, "Notes.db", null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static MyDatabase getInstance(Context mContext){
        if(myDatabase == null){
            myDatabase = new MyDatabase(mContext);
        }
        return myDatabase;
    }

    public void saveNote(Note note){
        if(note != null){
            ContentValues values = new ContentValues();
            values.put("md5", note.getMd5());
            values.put("title", note.getTitle());
            values.put("content", note.getContent());
            values.put("date", note.getDate());
            db.insert("Notes", null, values);
        }
    }

    public List<Note> loadNotes(){
        List<Note> notes = new ArrayList<>();
        String sql = "select * from Notes";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String md5 = cursor.getString(cursor.getColumnIndex("md5"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                Note note = new Note();
                note.setTitle(title);
                note.setContent(content);
                note.setMd5(md5);
                note.setDate(date);
                notes.add(note);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return notes;
    }

    public void deleteNote(Note note){
        String sql = "delete from Notes where md5 = '" + note.getMd5() + "'";
        db.execSQL(sql);
    }

    public List<Note> queryNotes(String string){
        List<Note> notes = new ArrayList<>();
        String sql = "select * from Notes where title like '%" + string + "%'";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                Note note = new Note();
                note.setTitle(title);
                note.setContent(content);
                note.setDate(date);
                notes.add(note);
            }while (cursor.moveToNext());
        }
        return notes;
    }
    public void updateNote(String md5, Note newNote){
        String sql = "update Notes set md5 = '" + newNote.getMd5()
                + "',title= '" + newNote.getTitle()
                + "',content = '" + newNote.getContent()
                + "' where md5 = '" + md5 + "'";
        db.execSQL(sql);
    }
}
