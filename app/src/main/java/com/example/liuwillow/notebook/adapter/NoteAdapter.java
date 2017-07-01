package com.example.liuwillow.notebook.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuwillow.notebook.R;
import com.example.liuwillow.notebook.activity.EditActivity;
import com.example.liuwillow.notebook.bean.Note;
import com.example.liuwillow.notebook.db.MyDatabase;
import com.example.liuwillow.notebook.presenter.IBaseActivity;
import com.example.liuwillow.notebook.presenter.MainPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by liuwillow on 17-6-22.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private Context mContext;
    private List<Note> datas = new ArrayList<>();
    private MyDatabase myDatabase;
    private IBaseActivity iBaseActivity;

    public NoteAdapter(Context mContext){
        this(mContext, null);
    }
    public NoteAdapter(Context mContext, IBaseActivity iBaseActivity){
        this.mContext = mContext;
        myDatabase = MyDatabase.getInstance(mContext);
        this.iBaseActivity = iBaseActivity;
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.notes_item_layout,parent,false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(final NoteHolder holder, int position) {
        final int t = datas.size() - position -1;
        holder.titleText.setText("标题：   " + datas.get(t).getTitle());
        holder.contentText.setText("内容:   " + datas.get(t).getContent());

        holder.contentText.setSingleLine();
        holder.contentText.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
        holder.contentText.setEms(7);
        holder.dateText.setText(datas.get(t).getDate());

        final Note note = datas.get(t);


        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("note", note);
                intent.putExtras(bundle);
                mContext.startActivity(intent);

                iBaseActivity.finishThis();
            }
        });


        holder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(iBaseActivity != null){
                    final MaterialDialog materialDialog = new MaterialDialog(mContext)
                            .setMessage("是否删除便签")
                            .setCanceledOnTouchOutside(true);

                    materialDialog.setPositiveButton("是", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDatabase.deleteNote(note);
                            datas.remove(t);
                            notifyDataSetChanged();
                            if(datas == null || datas.size() == 0){
                                iBaseActivity.showTint();
                            }

                            Toast.makeText(mContext,"删除成功",Toast.LENGTH_SHORT).show();
                            materialDialog.dismiss();
                        }
                    })
                            .setNegativeButton("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    materialDialog.dismiss();
                                }
                            }).show();
                }

                return true;
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
            datas.addAll(arrayLists);
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
