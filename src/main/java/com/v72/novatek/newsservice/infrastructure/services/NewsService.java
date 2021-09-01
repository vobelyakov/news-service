package com.v72.novatek.newsservice.infrastructure.services;

import com.v72.novatek.newsservice.core.interfaces.IAuthorService;
import com.v72.novatek.newsservice.core.interfaces.INewsService;
import com.v72.novatek.newsservice.core.models.Author;
import com.v72.novatek.newsservice.core.models.News;
import com.v72.novatek.newsservice.core.models.NewsCategory;
import com.v72.novatek.newsservice.infrastructure.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NewsService implements INewsService {

    @Autowired
    private NewsRepository repository;

    @Autowired
    private IAuthorService authorService;

    @Override
    public List<News> getNews() {
        return repository.findAll();
    }

    @Override
    public void updateNews(News news) {
        news.setAuthor(authorService.getRandomAuthor());
        news.setPublishDate(new Date());
        repository.save(news);
    }

    @Override
    public void deleteNews(Long id) {
        repository.deleteById(id);
    }

    @Override
    public News createNews(News news) {
        news.setAuthor(authorService.getRandomAuthor());
        news.setPublishDate(new Date());
        return repository.save(news);
    }

    @Override
    public News getNewsById(Long id) {
        return repository.getById(id);
    }

    @Override
    public List<News> getNewsByCategory(NewsCategory category) {
        return repository.findByCategory(category);
    }

    @Override
    public List<News> getNewsByAuthor(Author author) {
        return repository.findByAuthor(author);
    }
}
