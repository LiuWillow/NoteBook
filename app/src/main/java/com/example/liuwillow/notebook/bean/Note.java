package com.example.liuwillow.notebook.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by liuwillow on 17-6-22.
 */

public class Note extends DataSupport implements Serializable{
    private String date;
    private String title;
    private String content;
    private String md5;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
