package com.v72.novatek.newsservice.webapi.controllers;

import com.v72.novatek.newsservice.core.interfaces.INewsService;
import com.v72.novatek.newsservice.webapi.dto.NewsDTO;
import com.v72.novatek.newsservice.core.models.News;
import com.v72.novatek.newsservice.core.models.NewsCategory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NewsController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private INewsService newsService;

    @GetMapping("/news")
    List<NewsDTO> getAllNews() {
        return newsService.getNews()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/news/category/{category}")
    List<NewsDTO> getAllNewsByCategory(@PathVariable NewsCategory category) {
        return newsService.getNewsByCategory(category).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/news")
    NewsDTO newNews(String title, String content, NewsCategory category) {
        var newNewsDTO = new NewsDTO();
        newNewsDTO.setTitle(title);
        newNewsDTO.setContent(content);
        newNewsDTO.setCategory(category.toString());

        var news = convertToEntity(newNewsDTO);
        return convertToDto(newsService.createNews(news));
    }

    @GetMapping("/news/{id}")
    NewsDTO getNews(@PathVariable Long id) {

        var news = newsService.getNewsById(id);
        return convertToDto(news);
    }

    @PutMapping("/news/{id}")
    void updateNews(@RequestBody NewsDTO newNews, @PathVariable Long id) {
        var oldNews = newsService.getNewsById(id);

        oldNews.setTitle(newNews.getTitle());
        oldNews.setContent(newNews.getContent());
        oldNews.setCategory(NewsCategory.valueOf(newNews.getCategory()));

        newsService.updateNews(oldNews);
    }

    @DeleteMapping("/news/{id}")
    void deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
    }

    private NewsDTO convertToDto(News news) {
        NewsDTO newsDto = modelMapper.map(news, NewsDTO.class);
        newsDto.setPublishDate(dateFormat.format(news.getPublishDate()));
        return newsDto;
    }

    private News convertToEntity(NewsDTO newsDto) {
        return modelMapper.map(newsDto, News.class);
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
}
