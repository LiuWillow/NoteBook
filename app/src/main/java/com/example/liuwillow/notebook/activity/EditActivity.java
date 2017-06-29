package com.example.liuwillow.notebook.activity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.liuwillow.notebook.R;
import com.example.liuwillow.notebook.bean.Note;
import com.example.liuwillow.notebook.db.MyDatabase;

/**
 * Created by liuwillow on 17-6-22.
 */

public class EditActivity extends AppCompatActivity {
    LinearLayout container;
    EditText titleText;
    EditText contentText;
    Toolbar toolbar;

    private static final String TAG = "EditActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);


        container = (LinearLayout) findViewById(R.id.container);

        titleText = (EditText)findViewById(R.id.title_text);
        contentText = (EditText)findViewById(R.id.content_text);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentText.requestFocus();

                    InputMethodManager imm= (InputMethodManager)contentText.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

            }
        });
/*
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");*/
        if(getIntent().getExtras() != null){

            Note note = (Note)getIntent().getExtras().getSerializable("note");
            titleText.setText(note.getTitle());
            contentText.setText(note.getContent());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.submit_item:
                saveNote();
        }
        return true;
    }

    private void saveNote(){
        Note note = new Note();
        String title = titleText.getText().toString();
        String content = contentText.getText().toString();
        if(title.length() <= 0 || content.length() <= 0){
            Toast.makeText(this, "未输入内容！", Toast.LENGTH_SHORT).show();
        }
        else{
            note.setTitle(title);
            note.setContent(content);
            MyDatabase.getInstance(this).saveNote(note);
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }
}
