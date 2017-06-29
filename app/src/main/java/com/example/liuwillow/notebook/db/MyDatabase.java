package com.example.liuwillow.notebook.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.liuwillow.notebook.bean.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuwillow on 17-6-24.
 */

public class MyDatabase {
    private static MyDatabase myDatabase;
    private SQLiteDatabase db;
    public static final int VERSION = 1;

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
            values.put("title", note.getTitle());
            values.put("content", note.getContent());
            values.put("date", note.getDate());
            db.insert("Notes", null, values);
        }
    }

    public List<Note> loadNotes(){
        List<Note> notes = new ArrayList<>();
        Cursor cursor = db.query("Notes",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
               // String date = cursor.getString(cursor.getColumnIndex("date"));
                Note note = new Note();
                note.setTitle(title);
                note.setContent(content);
               // note.setDate(date);
                notes.add(note);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return notes;
    }

    public void deleteNote(String title){
        db.delete("Notes", "title = ?", new String[]{title});
    }

    public List<Note> queryNotes(String string){
        List<Note> notes = new ArrayList<>();
        String sql = "select * from Notes where title like '%" + string + "%'";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                // String date = cursor.getString(cursor.getColumnIndex("date"));
                Note note = new Note();
                note.setTitle(title);
                note.setContent(content);
                //note.setDate(date);
                notes.add(note);
            }while (cursor.moveToNext());
        }
        return notes;
    }
}
