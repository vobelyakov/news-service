package com.v72.novatek.newsservice.infrastructure.services;

import com.v72.novatek.newsservice.core.interfaces.IAuthorService;
import com.v72.novatek.newsservice.core.models.Author;
import com.v72.novatek.newsservice.infrastructure.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthorService implements IAuthorService {

    @Autowired
    private AuthorRepository repository;

    @Override
    public Author getRandomAuthor() {
        var authors = repository.findAll();
        var rnd = new Random();

        return authors.get(rnd.nextInt(authors.size()));
    }

    @Override
    public Author getAuthorById(Long id) {
        return repository.getById(id);
    }
}
