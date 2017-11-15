package com.mamh.hibernate.demo.entities;

import java.sql.Blob;
import java.sql.Clob;
import java.util.Date;

public class News {
    private Integer id;
    private String title;
    private String author;

    private Date date;

    private String desc;

    private Clob content;
    private Blob image;

    public News() {
    }

    public News(String title, String author, Date date) {
        this.title = title;
        this.author = author;
        this.date = date;
    }

    public News(Integer id, String title, String author, Date date) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Clob getContent() {
        return content;
    }

    public void setContent(Clob content) {
        this.content = content;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", desc='" + desc + '\'' +
                '}';
    }
}
