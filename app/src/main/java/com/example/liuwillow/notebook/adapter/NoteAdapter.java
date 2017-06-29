package com.example.liuwillow.notebook.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liuwillow.notebook.R;
import com.example.liuwillow.notebook.activity.EditActivity;
import com.example.liuwillow.notebook.bean.Note;
import com.example.liuwillow.notebook.db.MyDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuwillow on 17-6-22.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private Context mContext;
    private List<Note> datas = new ArrayList<>();
    public NoteAdapter(Context mContext){
        this.mContext = mContext;
    }
    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.notes_item_layout,parent,false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(final NoteHolder holder, int position) {
        final int t = datas.size() - position -1;
        holder.titleText.setText(datas.get(t).getTitle());
        holder.contentText.setText(datas.get(t).getContent());
        // holder.dateText.setText(datas.get(t).getDate());




        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditActivity.class);
                Note note = datas.get(t);
                Bundle bundle = new Bundle();
                bundle.putSerializable("note", note);
                intent.putExtras(bundle);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setDatas(List<Note> arrayLists){
        if(datas.size() == 0){
            datas.addAll(arrayLists);
            notifyDataSetChanged();
        }else{
            datas.clear();
            datas = arrayLists;
        }


    }

    public static class NoteHolder extends RecyclerView.ViewHolder{
        TextView titleText;
        TextView contentText;
        TextView dateText;
        LinearLayout container;
        public NoteHolder(View itemView) {
            super(itemView);
            titleText = (TextView)itemView.findViewById(R.id.title);
            contentText = (TextView)itemView.findViewById(R.id.content);
            dateText = (TextView)itemView.findViewById(R.id.date);
            container = (LinearLayout)itemView.findViewById(R.id.container);
        }
    }
}
