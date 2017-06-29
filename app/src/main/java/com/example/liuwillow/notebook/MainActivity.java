package com.example.liuwillow.notebook;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.liuwillow.notebook.activity.EditActivity;
import com.example.liuwillow.notebook.activity.SearchActivity;
import com.example.liuwillow.notebook.adapter.NoteAdapter;
import com.example.liuwillow.notebook.bean.Note;
import com.example.liuwillow.notebook.db.MyDatabaseHelper;
import com.example.liuwillow.notebook.presenter.IMainActivity;
import com.example.liuwillow.notebook.presenter.MainPresenterImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainActivity{
    private MyDatabaseHelper dbHelper;
    private NoteAdapter adapter;
    private RecyclerView recycle;
    private LinearLayoutManager linearLayoutManager;
    private MainPresenterImpl mainPresenter;
    private FloatingActionButton fab;
    private ImageView search;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        recycle = (RecyclerView)findViewById(R.id.recycle);
        linearLayoutManager = new LinearLayoutManager(this);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        search = (ImageView)findViewById(R.id.search);

        adapter = new NoteAdapter(this);
        mainPresenter = new MainPresenterImpl(this, this);

        setSupportActionBar(toolbar);
        recycle.setLayoutManager(linearLayoutManager);
        recycle.setAdapter(adapter);

        mainPresenter.getNotes();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEditActivity();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateNotes(List<Note> notes) {
        //TODO 重写界面更新数据方法
        adapter.setDatas(notes);
    }

    private void startEditActivity(){
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        startActivity(intent);
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
        mainPresenter.getNotes();
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
