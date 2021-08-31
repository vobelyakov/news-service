package com.v72.novatek.newsservice.infrastructure.repository;

import com.v72.novatek.newsservice.core.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
