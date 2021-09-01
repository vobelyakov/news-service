package com.v72.novatek.newsservice.core.interfaces;

import com.v72.novatek.newsservice.core.models.Author;

public interface IAuthorService {
    Author getRandomAuthor();

    Author getAuthorById(Long id);
}
