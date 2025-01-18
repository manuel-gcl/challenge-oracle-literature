package com.alura.literature.repository;

import com.alura.literature.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);

    Optional<Book> findByTitleIgnoreCase(String title);

    @Query("SELECT b FROM Book b JOIN FETCH b.authors")
    List<Book> findAllWithAuthors();

    @Query("SELECT b FROM Book b WHERE LOWER(b.language) = LOWER(:language)")
    List<Book> findByLanguage(@Param("language") String language);

    @Query(value = "SELECT * FROM books WHERE LOWER(:genre) = ANY(SELECT LOWER(unnest(genre)))", nativeQuery = true)
    List<Book> findBooksByGenre(@Param("genre") String genre);

    @Query("SELECT b FROM Book b JOIN b.authors a WHERE a.birthYear < :year")
    List<Book> findBooksByAuthorYear(@Param("year") int year);
}
