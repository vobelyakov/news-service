package com.v72.novatek.newsservice;

import com.v72.novatek.newsservice.core.interfaces.IAuthorService;
import com.v72.novatek.newsservice.core.models.Author;
import com.v72.novatek.newsservice.core.models.News;
import com.v72.novatek.newsservice.core.models.NewsCategory;
import com.v72.novatek.newsservice.infrastructure.repository.AuthorRepository;
import com.v72.novatek.newsservice.infrastructure.repository.NewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeedDatabase {
    private static final Logger log = LoggerFactory.getLogger(SeedDatabase.class);

    @Autowired
    private IAuthorService authorService;

    @Bean
    CommandLineRunner initDatabase(NewsRepository repository, AuthorRepository authRepository) {
        var author1 = authRepository.save(new Author("Pushkin"));
        var author2 = authRepository.save(new Author("Lermontov"));
        var author3 = authRepository.save(new Author("Chehov"));

        return args -> {
            log.info("Preloading " + repository.save(new News("Title #1", "Content #1", authorService.getRandomAuthor(), NewsCategory.Economy)));
            log.info("Preloading " + repository.save(new News("Title #2", "Content #2", authorService.getRandomAuthor(), NewsCategory.Politics)));
        };
    }
}
