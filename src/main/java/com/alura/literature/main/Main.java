package com.alura.literature.main;

import com.alura.literature.model.AuthorData;
import com.alura.literature.model.Book;
import com.alura.literature.model.BookData;
import com.alura.literature.model.ResultsData;
import com.alura.literature.processing.InputHandler;
import com.alura.literature.processing.UserChoice;
import com.alura.literature.repository.BookRepository;
import com.alura.literature.services.ApiConsumption;
import com.alura.literature.services.DataConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    private final static String BASE_URL = "https://gutendex.com/books/?search=";
    private final static String SEARCH_URL = "?search=";
    private final static String TOPIC_URL = "??topic=";
    private final static String LANGUAGE_URL = "?languages=";
    private final static String START_YEAR_URL = "?author_year_start=";
    private final static String END_YEAR_URL = "&author_year_end=";
    private final static String TITLE_ID = "title";
    private final static String AUTHORS_ID = "authors";
    private final static String LANGUAGE_ID = "languages";
    private final static String SUBJECT_ID = "genre";
    private final DataConverter converter = new DataConverter();
    private BookRepository repository;
    InputHandler inputHandler = new InputHandler();
    private Optional<Book> searchedBook;


    public Main(BookRepository repository) {
        this.repository = repository;
    }

    ApiConsumption apiConsumption = new ApiConsumption();
    String response = null;

    public void run() {
        ResultsData data;


        System.out.println(
                "--------------------" +
                        "---BOOKS SEARCHER---" +
                        "--------------------"
        );

        while (true) {
            String url_request = BASE_URL;
            String request = null;
            final UserChoice userChoice = inputHandler.getUserChoice();

            if (userChoice == UserChoice.EXIT) {
                break;
            }

            switch (userChoice) {
                case SEARCH_BOOK:
                    searchBook(inputHandler.getInputBookName());
//                    request = TITLE_ID;
                    break;
//                case UserChoice.SEARCH_AUTHOR:
//                    url_request += SEARCH_URL + inputHandler.getInputAuthorName();
//                    request = AUTHORS_ID;
//                    break;
//                case SEARCH_AUTHOR_BY_DATE:
//                    url_request +=
//                            START_YEAR_URL +
//                                    inputHandler.getAuthorYear(true) +
//                                    END_YEAR_URL +
//                                    inputHandler.getAuthorYear(false);
//                    request = AUTHORS_ID;
//                    break;
//                case SEARCH_BOOKS_BY_LANGUAGE:
//                    url_request += LANGUAGE_URL + inputHandler.getBookLanguage();
//                    request = LANGUAGE_ID;
//                    break;
//                case SEARCH_BOOK_BY_TOPIC:
//                    url_request += TOPIC_URL + inputHandler.getInputTopic();
//                    request = SUBJECT_ID;
//                    break;
                default:
                    System.out.println("Invalid Option\n");
            }
            System.out.println(url_request);
        }
        System.out.println(
                "---------------------" +
                        "----PROGRAM ENDED----" +
                        "---------------------");
    }


    public List<Book> getBookDataFromApi(String bookName){
        String BASE_URL = "https://gutendex.com/books/?search=";
        String data = apiConsumption.getData(BASE_URL + bookName);
        ResultsData results = converter.getData(data, ResultsData.class);
        List<Book> booksList = new ArrayList<>();
        results.booksList().forEach(b -> booksList.add(new Book(b)));
        
        return booksList;
    }

    public void searchBook(String bookName) {
        searchedBook = repository.findByTitleContainsIgnoreCase(bookName);

        if (searchedBook.isPresent()){
            System.out.println("Book match founded in DB");
            Book book = searchedBook.get();
            System.out.println(book);

        } else {
            System.out.println("Can't find book in DB .... Searching in API ....");
            List<Book> bookList = getBookDataFromApi(bookName);
            System.out.println("Possible matches founded in DB ....");
            System.out.println("Trying to save books:");
            bookList.forEach(System.out::println);
            bookList.forEach(b -> repository.save(b));
        }
    }

}