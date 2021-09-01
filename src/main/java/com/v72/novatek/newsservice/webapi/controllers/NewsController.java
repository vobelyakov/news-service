package com.v72.novatek.newsservice.webapi.controllers;

import com.v72.novatek.newsservice.core.interfaces.IAuthorService;
import com.v72.novatek.newsservice.core.interfaces.INewsService;
import com.v72.novatek.newsservice.webapi.dto.NewsDTO;
import com.v72.novatek.newsservice.core.models.News;
import com.v72.novatek.newsservice.core.models.NewsCategory;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NewsController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private INewsService newsService;

    @Autowired
    private IAuthorService authorService;

    @Operation(summary = "Получить все новости")
    @GetMapping("/news")
    List<NewsDTO> getAllNews() {
        return newsService.getNews()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Получить все новости раздела")
    @GetMapping("/news/category/{category}")
    List<NewsDTO> getAllNewsByCategory(@ApiParam(value = "Раздел")
                                       @PathVariable NewsCategory category) {
        return newsService.getNewsByCategory(category).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Получить все новости автора")
    @GetMapping("/news/author/{id}")
    List<NewsDTO> getAllNewsByAuthor(@ApiParam(value = "Id автора")
                                     @PathVariable Long id) {
        var author = authorService.getAuthorById(id);

        if (author.getName() == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found");

        return newsService.getNewsByAuthor(author)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Добавить новость")
    @PostMapping("/news")
    NewsDTO newNews(@ApiParam(value = "Заголовок") @RequestParam String title,
                    @ApiParam(value = "Содержание новости") @RequestParam String content,
                    @ApiParam(value = "Раздел") @RequestParam NewsCategory category) {
        var newNewsDTO = new NewsDTO();
        newNewsDTO.setTitle(title);
        newNewsDTO.setContent(content);
        newNewsDTO.setCategory(category.toString());

        var news = convertToEntity(newNewsDTO);
        return convertToDto(newsService.createNews(news));
    }

    @Operation(summary = "Получить новость по Id")
    @GetMapping("/news/{id}")
    NewsDTO getNews(@ApiParam(value = "Id новости")
                    @PathVariable Long id) {

        var news = newsService.getNewsById(id);
        return convertToDto(news);
    }

    @Operation(summary = "Обновить новость")
    @PutMapping("/news/{id}")
    void updateNews(@Validated @RequestBody NewsDTO newNews,
                    @ApiParam(value = "Id новости") @PathVariable Long id) {
        var oldNews = newsService.getNewsById(id);

        oldNews.setTitle(newNews.getTitle());
        oldNews.setContent(newNews.getContent());
        oldNews.setCategory(NewsCategory.valueOf(newNews.getCategory()));

        newsService.updateNews(oldNews);
    }

    @Operation(summary = "Удалить новость")
    @DeleteMapping("/news/{id}")
    void deleteNews(@ApiParam(value = "Id новости") @PathVariable Long id) {
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
