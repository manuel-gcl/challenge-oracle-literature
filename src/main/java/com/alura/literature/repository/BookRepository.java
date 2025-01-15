package com.alura.literature.repository;

import com.alura.literature.model.Author;
import com.alura.literature.model.Book;
import com.alura.literature.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleContainsIgnoreCase(String bookName);
//    List<Book> findByAuthorContainsIgnoreCase(String authorName);
//    List<Book> findByGenre(Genre genre);
//    @Query("SELECT b FROM books b WHERE b.language ILIKE %:language%")
//    List<Book> findByLanguage(String language);
}
