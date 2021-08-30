package com.v72.novatek.newsservice.controllers;

class NewsNotFoundException extends RuntimeException {

    NewsNotFoundException(Long id) {
        super("Could not find news " + id);
    }
}
