package com.example.liuwillow.notebook.presenter;

import com.example.liuwillow.notebook.bean.Note;

import java.util.List;

/**
 * Created by liuwillow on 17-6-30.
 */

public interface IBaseActivity {
    void updateNotes(List<Note> notes);
    void showTint();
}
