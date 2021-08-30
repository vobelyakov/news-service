package com.v72.novatek.newsservice.repository;

import com.v72.novatek.newsservice.models.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {

}
