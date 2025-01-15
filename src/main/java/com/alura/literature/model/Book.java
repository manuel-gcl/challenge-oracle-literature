package com.alura.literature.model;
import com.alura.literature.model.converters.GenreListConverter;
import com.alura.literature.model.converters.LanguageListConverter;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Convert(converter = GenreListConverter.class)
    @Enumerated(EnumType.STRING)
    private List<Genre> genre = new ArrayList<>();

    @Convert(converter = LanguageListConverter.class)
    private List<String> language = new ArrayList<>();

    @Column(name = "download_count")
    private Double downloadCount;

    private Boolean copyright;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="books_authors",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="author_id")
    )
    private List<Author> authors = new ArrayList<>();

    public Book(){}

    public Book(BookData bookData) {
        this.title = bookData.title();
        this.language = bookData.languages();
        this.downloadCount = bookData.downloadCount() != null ? bookData.downloadCount() : 0;

        if (!bookData.genre().isEmpty()) {
            this.genre = Genre.filterAndGetGenresFromStrings(bookData.genre());
        }
        if (!bookData.authors().isEmpty()){
            bookData.authors().forEach(a -> {
                this.authors.add(new Author(a.name(), a.birth_year(), a.death_year()));
            });
        }
        this.copyright = bookData.copyright();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    public Double getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Double downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Boolean getCopyright() {
        return copyright;
    }

    public void setCopyright(Boolean copyright) {
        this.copyright = copyright;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Book ").append(id).append(":\n")
                .append("\tTitle: ").append(title).append("\n")
                .append("\tAuthors: \n").append(authors).append("\n")
                .append("\tGenre: ").append(genre).append("\n")
                .append("\tLanguage: ").append(language).append("\n")
                .append("\tDownloads Count: ").append(downloadCount).append("\n")
                .append("\tCopyrights: ").append(copyright).append("\n");

        return str.toString();
    }
}
