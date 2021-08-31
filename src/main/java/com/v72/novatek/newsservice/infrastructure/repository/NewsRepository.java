package com.v72.novatek.newsservice.infrastructure.repository;

import com.v72.novatek.newsservice.core.models.Author;
import com.v72.novatek.newsservice.core.models.News;
import com.v72.novatek.newsservice.core.models.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {


    @Query("select n from News n where n.category = :category")
    List<News> findByCategory(NewsCategory category);

    @Query("select n from News n where n.author = :author")
    List<News> findByAuthor(Author author);
}
