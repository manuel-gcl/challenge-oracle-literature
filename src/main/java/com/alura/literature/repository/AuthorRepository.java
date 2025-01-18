package com.alura.literature.repository;

import com.alura.literature.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByNameContainsIgnoreCase(String name);

    Optional<Author> findByName(String name);
}
