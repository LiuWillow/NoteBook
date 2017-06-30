package com.example.liuwillow.notebook.presenter;

import android.content.Context;

import com.example.liuwillow.notebook.bean.Note;
import com.example.liuwillow.notebook.db.MyDatabase;


/**
 * Created by liuwillow on 17-6-30.
 */

public class EditPresenterImpl implements IEditPresenter {
    private MyDatabase db;
    private Context mContext;
    public EditPresenterImpl(Context mContext){
        this.mContext = mContext;
        db = MyDatabase.getInstance(mContext);
    }
    @Override
    public void saveNote(Note note) {
        if(note != null)
        db.saveNote(note);
    }

    @Override
    public void updateNote(String md5, Note note) {
        db.updateNote(md5, note);
    }
}
