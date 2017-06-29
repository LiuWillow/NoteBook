package com.example.liuwillow.notebook.presenter;

import android.content.Context;

import com.example.liuwillow.notebook.bean.Note;
import com.example.liuwillow.notebook.db.MyDatabase;

import java.util.List;

/**
 * Created by liuwillow on 17-6-22.
 */

public class MainPresenterImpl implements IMainPresenter {
    IMainActivity iMainActivity;
    Context mContext;
    public MainPresenterImpl(Context context, IMainActivity iMainActivity){
        mContext = context;
        this.iMainActivity = iMainActivity;
    }
    @Override
    public void getNotes() {
        //TODO 从数据库中获取便签数据，调用iMainActivity的方法更新界面
        List<Note> notes = MyDatabase.getInstance(mContext).loadNotes();
        iMainActivity.updateNotes(notes);
    }
}
