package com.v72.novatek.newsservice.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class News {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    private String author;

    private Date publishDate;

    @Enumerated(EnumType.STRING)
    private NewsCategory category;

    public News() {}

    public News(String title, String content, String author, NewsCategory category) {
        this.title = title;
        this.content = content;

        this.author = author;
        this.category = category;

        this.publishDate = new Date();
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public NewsCategory getCategory() {
        return category;
    }

    public void setCategory(NewsCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "News{" + "id=" + this.id + ", title='" + this.title + '\'' + ", author='" + this.author + '\''+ ", publishDate='" + this.publishDate.toString() + '\'' + '}';
    }
}
