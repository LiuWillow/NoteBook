package com.example.liuwillow.notebook.utils;

import com.example.liuwillow.notebook.bean.Note;

import org.litepal.crud.DataSupport;

import java.util.List;


/**
 * Created by liuwillow on 17-7-8.
 */

public class DbUtils {

    public static void saveNote(Note note){
        if(note != null){
           note.save();
        }
    }
    public static List<Note> loadNotes(){
        List<Note> notes = DataSupport.findAll(Note.class);
        return notes;
    }

    public static void deleteNote(Note note){
        DataSupport.deleteAll(Note.class, "md5 = ?", note.getMd5());
    }

    public static void updateNote(String md5, Note newNote){
        newNote.updateAll("md5 = ?", md5);
    }

    public static List<Note> queryNotes(String string){
        String str = "title like '%" + string + "%'";
        List<Note> notes = DataSupport.where(str).find(Note.class);
        return notes;
    }
}
