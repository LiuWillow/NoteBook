package com.example.liuwillow.notebook.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.example.liuwillow.notebook.R;
import com.example.liuwillow.notebook.adapter.NoteAdapter;
import com.example.liuwillow.notebook.bean.Note;
import com.example.liuwillow.notebook.db.MyDatabase;

import java.util.List;

/**
 * Created by liuwillow on 17-6-28.
 */

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    Toolbar toolbar;
    SearchView searchView;
    NoteAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        adapter = new NoteAdapter(this);
        recyclerView = (RecyclerView)findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //设置返回为上一级而不是最顶级
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.menu_search));
        searchView.setQueryHint("输入关键字");
        searchView.setOnQueryTextListener(this);
        searchView.setIconifiedByDefault(false);
        searchView.setIconified(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        List<Note> notes = MyDatabase.getInstance(this).queryNotes(query);
        adapter.setDatas(notes);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(!TextUtils.isEmpty(newText)){
            List<Note> notes = MyDatabase.getInstance(this).queryNotes(newText);
            adapter.setDatas(notes);
        }
        return true;
    }
}
