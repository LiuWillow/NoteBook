package com.example.liuwillow.notebook.bean;

import java.io.Serializable;

/**
 * Created by liuwillow on 17-6-22.
 */

public class Note implements Serializable{
    private String date;
    private String title;
    private String content;

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
}
