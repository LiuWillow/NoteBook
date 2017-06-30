package com.example.liuwillow.notebook.presenter;

import com.example.liuwillow.notebook.bean.Note;

/**
 * Created by liuwillow on 17-6-30.
 */

public interface IEditPresenter {
    void saveNote(Note note);
    void updateNote(String md5, Note note);
}
