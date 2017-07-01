package com.example.liuwillow.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.liuwillow.notebook.activity.EditActivity;
import com.example.liuwillow.notebook.activity.SearchActivity;
import com.example.liuwillow.notebook.adapter.NoteAdapter;
import com.example.liuwillow.notebook.bean.Note;
import com.example.liuwillow.notebook.presenter.IBaseActivity;
import com.example.liuwillow.notebook.presenter.MainPresenterImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IBaseActivity{
    private NoteAdapter adapter;
    private RecyclerView recycle;
    private LinearLayoutManager linearLayoutManager;
    private MainPresenterImpl mainPresenter;
    private FloatingActionButton fab;
    private ImageView search;
    private LinearLayout linearNoData;
    private Toolbar toolbar;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        adapter = new NoteAdapter(this,this);
        mainPresenter = new MainPresenterImpl(this, this);

        recycle.setAdapter(adapter);

        mainPresenter.getNotes();

    }

    private void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recycle = (RecyclerView)findViewById(R.id.recycle);
        linearLayoutManager = new LinearLayoutManager(this);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        search = (ImageView)findViewById(R.id.search);
        linearNoData = (LinearLayout)findViewById(R.id.linear_nodata);
        setSupportActionBar(toolbar);
        recycle.setLayoutManager(linearLayoutManager);
    }

    private void initListener(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goEditActivity();
                finish();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSearchActivity();
                finish();
            }
        });
        linearNoData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goEditActivity();
                finish();
            }
        });
    }
    @Override
    public void updateNotes(List<Note> notes) {
        recycle.setVisibility(View.VISIBLE);
        linearNoData.setVisibility(View.GONE);
        adapter.setDatas(notes);
    }

    @Override
    public void showTint() {
        recycle.setVisibility(View.GONE);
        linearNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishThis() {
        finish();
    }

    private void goEditActivity(){
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        startActivity(intent);
    }

    private void goSearchActivity(){
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
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
