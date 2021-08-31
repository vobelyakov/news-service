package com.v72.novatek.newsservice.repository;

import com.v72.novatek.newsservice.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
