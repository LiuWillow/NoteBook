package com.example.liuwillow.notebook.presenter;

import com.example.liuwillow.notebook.bean.Note;

/**
 * Created by liuwillow on 17-6-30.
 */

public interface IMainPresenter {
    void getNotes();
    void deleteNote(Note note);
}
