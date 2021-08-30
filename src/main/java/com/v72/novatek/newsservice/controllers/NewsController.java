package com.v72.novatek.newsservice.controllers;

import com.v72.novatek.newsservice.models.News;
import com.v72.novatek.newsservice.repository.NewsRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsController {
    private final NewsRepository repository;

    NewsController(NewsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/news")
    List<News> getAllNews() {
        return repository.findAll();
    }

    @PostMapping("/news")
    News newNews(@RequestBody News newNews) {
        return repository.save(newNews);
    }

    @GetMapping("/news/{id}")
    News getNews(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new NewsNotFoundException(id));
    }

    @PutMapping("/news/{id}")
    News replaceNews(@RequestBody News newNews, @PathVariable Long id) {

        return repository.findById(id)
                .map(news -> {
                    news.setTitle(newNews.getTitle());
                    news.setContent(newNews.getContent());
                    news.setAuthor(newNews.getAuthor());
                    news.setCategory(newNews.getCategory());
                    return repository.save(news);
                })
                .orElseGet(() -> {
                    newNews.setId(id);
                    return repository.save(newNews);
                });
    }

    @DeleteMapping("/news/{id}")
    void deleteNews(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
