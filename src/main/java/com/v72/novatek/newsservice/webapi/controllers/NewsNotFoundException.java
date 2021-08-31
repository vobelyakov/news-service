package com.v72.novatek.newsservice.webapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such News")
class NewsNotFoundException extends RuntimeException {
    NewsNotFoundException(Long id) {
        super("Could not find news " + id);
    }
}
