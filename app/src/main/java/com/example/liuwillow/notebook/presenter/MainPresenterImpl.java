package com.example.liuwillow.notebook.presenter;

import android.content.Context;

import com.example.liuwillow.notebook.bean.Note;
import com.example.liuwillow.notebook.utils.DbUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuwillow on 17-6-30.
 */

public class MainPresenterImpl implements IMainPresenter {
    private IBaseActivity iBaseActivity;
    public MainPresenterImpl(IBaseActivity iBaseActivity){
        this.iBaseActivity = iBaseActivity;
    }

    @Override
    public void getNotes() {
        List<Note> notes = DbUtils.loadNotes();
        if(notes == null || notes.size() == 0){
            iBaseActivity.showTint();
        }else{
            iBaseActivity.updateNotes(notes);
        }
    }

    @Override
    public void deleteNote(Note note) {
        DbUtils.deleteNote(note);
    }
}
