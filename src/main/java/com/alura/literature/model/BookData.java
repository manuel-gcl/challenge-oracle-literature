package com.alura.literature.model;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<AuthorData> authors,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("bookshelves") List<String> genre,
        @JsonAlias("download_count") Double downloadCount,
        @JsonAlias("copyright") Boolean copyright
    ) {
}
