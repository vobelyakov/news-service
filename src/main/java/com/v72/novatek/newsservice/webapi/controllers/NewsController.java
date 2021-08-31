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
    NewsDTO newNews(@RequestBody NewsDTO newNewsDTO) throws ParseException  {
        var news = convertToEntity(newNewsDTO);
        return convertToDto(newsService.createNews(news));
    }

    @GetMapping("/news/{id}")
    NewsDTO getNews(@PathVariable Long id) {

        var news = newsService.getNewsById(id);
        return convertToDto(news);
    }

    @PutMapping("/news/{id}")
    NewsDTO updateNews(@RequestBody News newNews, @PathVariable Long id) {
        return null;
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

    private News convertToEntity(NewsDTO newsDto) throws ParseException {
        News news = modelMapper.map(newsDto, News.class);
        news.setPublishDate(dateFormat.parse(newsDto.getPublishDate()));
        return news;
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
}
