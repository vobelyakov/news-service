package com.v72.novatek.newsservice.core.interfaces;

import com.v72.novatek.newsservice.core.models.Author;
import com.v72.novatek.newsservice.core.models.News;
import com.v72.novatek.newsservice.core.models.NewsCategory;

import java.util.List;

public interface INewsService {

    List<News> getNews();

    void updateNews(News news);

    void deleteNews(Long id);

    News createNews(News news);

    News getNewsById(Long id);

    List<News> getNewsByCategory(NewsCategory category);

    List<News> getNewsByAuthor(Author author);
}
