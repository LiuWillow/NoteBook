package com.example.liuwillow.notebook.presenter;

import android.content.Context;

import com.example.liuwillow.notebook.bean.Note;
import com.example.liuwillow.notebook.utils.DbUtils;


/**
 * Created by liuwillow on 17-6-30.
 */

public class EditPresenterImpl implements IEditPresenter {

    @Override
    public void saveNote(Note note) {
        if(note != null)
            DbUtils.saveNote(note);
    }

    @Override
    public void updateNote(String md5, Note note) {
        DbUtils.updateNote(md5,note);
    }
}
