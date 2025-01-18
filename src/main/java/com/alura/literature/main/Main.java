package com.alura.literature.main;

import com.alura.literature.model.Author;
import com.alura.literature.model.Book;
import com.alura.literature.model.ResultsData;
import com.alura.literature.processing.InputHandler;
import com.alura.literature.processing.UserChoice;
import com.alura.literature.repository.AuthorRepository;
import com.alura.literature.repository.BookRepository;
import com.alura.literature.services.ApiConsumption;
import com.alura.literature.services.DataConverter;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    private final DataConverter converter = new DataConverter();
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    InputHandler inputHandler = new InputHandler();
    ApiConsumption apiConsumption = new ApiConsumption();
    private List<Book> searchedBooks;

    public Main(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void run() {
        System.out.println(
                "--------------------" +
                        "---BOOKS SEARCHER---" +
                        "--------------------"
        );

        while (true) {
            final UserChoice userChoice = inputHandler.getUserChoice();
            if (userChoice == UserChoice.EXIT) {
                break;
            }
            switch (userChoice) {
                case SEARCH_BOOK:
                    searchBook();
                    break;
                case SEARCH_AUTHOR:
                    searchAuthor();
                    break;
                case LIST_BOOKS:
                    listBooks();
                    break;
                case LIST_AUTHORS:
                    listAuthors(false);
                    break;
                case LIST_AUTHORS_BY_YEAR:
                    listAuthors(true);
                    break;
                case FILTER_BOOKS_BY_LANGUAGE:
                    listBooksByLanguage();
                    break;
                case FILTER_BOOKS_BY_GENRE:
                    listBooksByGenre();
                    break;
                case FILTER_BOOKS_PREVIOUS_YEAR:
                    listBooksBeforeDate();
                    break;
                default:
                    System.out.println("Invalid Option\n");
            }
        }
        System.out.println(
                "---------------------" +
                        "----PROGRAM ENDED----" +
                        "---------------------");
    }


    public List<Book> getBookDataFromApi(String bookName) {
        String BASE_URL = "https://gutendex.com/books/?search=";
        String data = apiConsumption.getData(BASE_URL + bookName);
        ResultsData results = converter.getData(data, ResultsData.class);

        return results.booksList().stream()
                .map(Book::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void searchBook() {
        String bookName = inputHandler.getInputBookName();
        searchedBooks = bookRepository.findByTitleContainingIgnoreCase(bookName);

        if (!searchedBooks.isEmpty()) {
            System.out.println(searchedBooks.size() + " books matches founded in database");
            searchedBooks.forEach(System.out::println);
        } else {
            System.out.println("Can't find book in database .... Searching in API ....");
            List<Book> bookList = getBookDataFromApi(bookName.replace(" ", "+"));
            System.out.println("Possible matches found from API ....");

            bookList.forEach(book -> {
                List<Author> managedAuthors = new ArrayList<>();
                for (Author author : book.getAuthors()) {
                    Optional<Author> existingAuthor = authorRepository.findByName(author.getName());
                    if (existingAuthor.isPresent()) {
                        managedAuthors.add(existingAuthor.get());
                    } else {
                        managedAuthors.add(authorRepository.save(author));
                    }
                }
                book.setAuthors(managedAuthors);

                Optional<Book> existingBook = bookRepository.findByTitleIgnoreCase(book.getTitle());
                if (existingBook.isEmpty()) {
                    try {
                        bookRepository.save(book);
                    } catch (RuntimeException e) {
                        System.out.println("Can't save book in database: " + e.getMessage());
                    }
                }
                System.out.println(book);
            });
        }
    }

    public void searchAuthor() {
        String authorName = inputHandler.getInputAuthorName();
        List<Author> searchedAuthors = authorRepository.findByNameContainsIgnoreCase(authorName);
        if (searchedAuthors.isEmpty() && authorName.contains(" ")) {
            authorName = authorName.split(" ")[0].replace(",", "");
            System.out.println("Trying to find partial match {" + authorName + "}.");
            searchedAuthors = authorRepository.findByNameContainsIgnoreCase(authorName);
        }

        if (searchedAuthors.isEmpty()) {
            System.out.println("No authors found in database with that name or first name");
        } else {
            searchedAuthors = searchedAuthors.stream()
                    .sorted(Comparator.comparing(Author::getName))
                    .toList();
            searchedAuthors.forEach(System.out::println);
        }
    }

    public void listBooks() {
        searchedBooks = bookRepository.findAllWithAuthors();
        if (searchedBooks.isEmpty()){
            System.out.println("No available books in database");
        } else {
            printBooksByTitle(searchedBooks);
        }
    }

    public void listAuthors(Boolean orderByYear) {
        List<Author> authors = authorRepository.findAll();

        if (orderByYear) {
            authors = authors.stream()
                    .sorted(Comparator.comparing(Author::getBirthYear))
                    .toList();
        } else {
            authors = authors.stream()
                    .sorted(Comparator.comparing(Author::getName))
                    .toList();
        }
        authors.forEach(System.out::println);
    }

    public void listBooksByLanguage() {
        String language = inputHandler.getBookLanguage();
        searchedBooks = bookRepository.findByLanguage(language);
        if (searchedBooks.isEmpty()){
            System.out.println("Can't find books with in language: '" + language + "'");
        } else {
            printBooksByTitle(searchedBooks);
        }
    }

    public void listBooksByGenre() {
        String genre = inputHandler.getInputGenre();
        searchedBooks = bookRepository.findBooksByGenre(genre);
        if (searchedBooks.isEmpty()){
            System.out.println("Can't find books with that genre: '" + genre + "'");
        } else {
            printBooksByTitle(searchedBooks);
        }
    }

    public void printBooksByTitle(List<Book> books) {
        books = books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .toList();
        books.forEach(System.out::println);
    }

    public void listBooksBeforeDate() {
        int inputYear = inputHandler.getAuthorYear(true);
        searchedBooks = bookRepository.findBooksByAuthorYear(inputYear);
        searchedBooks = searchedBooks.stream()
                .sorted(Comparator.comparingInt(book ->
                        book.getAuthors().stream()
                                .mapToInt(Author::getBirthYear)
                                .min()
                                .getAsInt()))
                .toList();
        if (searchedBooks.isEmpty()){
            System.out.println("No available books before author birth year: " + inputYear);
        } else {
            searchedBooks.forEach(System.out::println);
        }
    }

}