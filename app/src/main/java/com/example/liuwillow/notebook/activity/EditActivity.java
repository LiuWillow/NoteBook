package com.example.liuwillow.notebook.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.liuwillow.notebook.MainActivity;
import com.example.liuwillow.notebook.R;
import com.example.liuwillow.notebook.bean.Note;
import com.example.liuwillow.notebook.presenter.EditPresenterImpl;
import com.example.liuwillow.notebook.utils.MathUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by liuwillow on 17-6-22.
 */

public class EditActivity extends AppCompatActivity{
    LinearLayout container;
    EditText titleText;
    EditText contentText;
    Toolbar toolbar;
    private EditPresenterImpl editPresenter;
    private String md5;
    private Note noteOld;

    private static final String TAG = "EditActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.edit_layout);
        initView();
        initListener();
        editPresenter = new EditPresenterImpl();

        if(getIntent().getExtras() != null){
            noteOld = (Note)getIntent().getExtras().getSerializable("note");
            titleText.setText(noteOld.getTitle());
            contentText.setText(noteOld.getContent());
            md5 = noteOld.getMd5();
        }

    }

    private void initView(){
        container = (LinearLayout) findViewById(R.id.container);
        titleText = (EditText)findViewById(R.id.title_text);
        contentText = (EditText)findViewById(R.id.content_text);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initListener(){
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentText.requestFocus();

                InputMethodManager imm= (InputMethodManager)contentText.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        final Note note = getNote();
        switch (id){
            case R.id.submit_item:

                if(note.getContent() != null && note.getContent().length() != 0){
                    if(getIntent().getExtras() != null){
                        editPresenter.updateNote(md5, note);
                        Toast.makeText(this, "更改成功", Toast.LENGTH_SHORT).show();
                        goMainActivity();
                        finish();
                    }else{
                        editPresenter.saveNote(note);
                        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                        goMainActivity();
                        finish();
                    }
                }
                break;
            case android.R.id.home:
                String title = titleText.getText().toString();
                String content = contentText.getText().toString();
                if(title.length() == 0 || content.length() == 0){
                    goMainActivity();
                    finish();
                }else{

                    if (note.getContent() != null && note.getContent().length() != 0){

                        final MaterialDialog materialDialog = new MaterialDialog(this)
                                .setMessage("便签未保存，")
                                .setCanceledOnTouchOutside(true);

                        materialDialog.setPositiveButton("保存", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(getIntent().getExtras() != null){
                                    editPresenter.updateNote(md5, note);

                                    finish();
                                    goMainActivity();
                                    Toast.makeText(EditActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                                    materialDialog.dismiss();
                                }else {
                                    editPresenter.saveNote(note);
                                    finish();
                                    goMainActivity();
                                    Toast.makeText(EditActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                                    materialDialog.dismiss();
                                }

                            }
                        })
                                .setNegativeButton("退出", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        finish();
                                        goMainActivity();
                                        materialDialog.dismiss();
                                    }
                                }).show();

                    }else{
                        goMainActivity();
                        finish();
                    }
                }
        }
        return true;
    }

    private void goMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private Note getNote(){
        Note note = new Note();

        String title = titleText.getText().toString();
        String content = contentText.getText().toString();
        Log.d(TAG, title);
        if(title.length() == 0 || content.length() == 0){
            Toast.makeText(this, "未输入标题或内容！", Toast.LENGTH_SHORT).show();
        }
        else{
            SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日");
            Date curDate = new Date(System.currentTimeMillis());
            String date = formatter.format(curDate);

            note.setTitle(title);
            note.setContent(content);
            note.setMd5(MathUtils.getMd5(title));
            note.setDate(date);
        }
        return note;
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

    @Override
    public void onBackPressed() {
        String title = titleText.getText().toString();
        String content = contentText.getText().toString();
        if(title.length() == 0 || content.length() == 0){
            goMainActivity();
            finish();
        }else{

            final Note note = getNote();
            if (note.getContent() != null && note.getContent().length() != 0){

                final MaterialDialog materialDialog = new MaterialDialog(this)
                        .setMessage("便签未保存，")
                        .setCanceledOnTouchOutside(true);

                materialDialog.setPositiveButton("保存", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(getIntent().getExtras() != null){
                            editPresenter.updateNote(md5, note);

                            finish();
                            goMainActivity();
                            Toast.makeText(EditActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                            materialDialog.dismiss();
                        }else {
                            editPresenter.saveNote(note);
                            finish();
                            goMainActivity();
                            Toast.makeText(EditActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                            materialDialog.dismiss();
                        }

                    }
                })
                        .setNegativeButton("退出", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                                goMainActivity();
                                materialDialog.dismiss();
                            }
                        }).show();
            }else{
                goMainActivity();
                finish();
            }
        }
}}
