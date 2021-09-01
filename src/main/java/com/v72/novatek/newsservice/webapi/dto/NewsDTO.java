package com.v72.novatek.newsservice.webapi.dto;

import io.swagger.annotations.ApiModelProperty;

public class NewsDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    private String title;

    private String content;

    @ApiModelProperty(hidden = true)
    private AuthorDTO author;

    @ApiModelProperty(hidden = true)
    private String publishDate;

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
