package com.v72.novatek.newsservice;

import com.v72.novatek.newsservice.models.Author;
import com.v72.novatek.newsservice.models.News;
import com.v72.novatek.newsservice.models.NewsCategory;
import com.v72.novatek.newsservice.repository.AuthorRepository;
import com.v72.novatek.newsservice.repository.NewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeedDatabase {
    private static final Logger log = LoggerFactory.getLogger(SeedDatabase.class);

    @Bean
    CommandLineRunner initDatabase(NewsRepository repository, AuthorRepository authRepository) {
        var author1 = authRepository.save(new Author("Pushkin"));
        var author2 = authRepository.save(new Author("Lermontov"));

        return args -> {
            log.info("Preloading " + repository.save(new News("Title #1", "Content #1", author1, NewsCategory.Economy)));
            log.info("Preloading " + repository.save(new News("Title #2", "Content #2", author2, NewsCategory.Politics)));
        };
    }
}
