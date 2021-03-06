package com.v72.novatek.newsservice.webapi.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class NewsDTO {

    private Long id;

    @NotEmpty
    @Size(min=3, max=20, message = "Заголовок должен быть от 3 до 20 символов")
    private String title;

    @NotEmpty
    private String content;

    private AuthorDTO author;

    private String publishDate;

    @NotEmpty
    private String category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



}
