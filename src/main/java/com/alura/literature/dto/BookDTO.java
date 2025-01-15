package com.alura.literature.dto;

import com.alura.literature.model.Author;
import com.alura.literature.model.Genre;

import java.util.List;

public record BookDTO(
    String title,
    List<Author> authors,
    List<Genre> genre,
    String language,
    String bookSummary) {
}
