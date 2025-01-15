package com.alura.literature.model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String title;
    @Enumerated(EnumType.STRING)
    private List<Genre> genre;
    private List<String> language;
    private Double downloadCount;
    private Boolean copyright;
    @ManyToMany()
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
        if (!bookData.genre().isEmpty()) {
            this.genre = Genre.filterAndGetGenresFromStrings(bookData.genre());
        }
        if (!bookData.authors().isEmpty()){
            bookData.authors().forEach(a -> {
                this.authors.add(new Author(a.name(), a.birth_year(), a.death_year()));
            });
        }
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
        str.append("Book:\n\tTitle:").append(title).append("\n")
                .append("\tId:").append(id).append("\n")
                .append("\tAuthors:").append(authors).append("\n")
                .append("\tGenre:").append(genre).append("\n")
                .append("\tLanguage:").append(language).append("\n")
                .append("\tDownloads Count:").append(downloadCount).append("\n")
                .append("\tCopyrights:").append(copyright).append("\n");

        return str.toString();
    }
}
