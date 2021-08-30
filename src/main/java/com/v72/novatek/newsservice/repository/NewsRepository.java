package com.v72.novatek.newsservice.repository;

import com.v72.novatek.newsservice.models.News;
import com.v72.novatek.newsservice.models.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("select n from News n where n.category = :category")
    List<News> findByCategory(NewsCategory category);
}
