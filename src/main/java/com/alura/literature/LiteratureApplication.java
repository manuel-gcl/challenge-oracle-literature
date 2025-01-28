package com.alura.literature;

import com.alura.literature.main.Main;
import com.alura.literature.repository.AuthorRepository;
import com.alura.literature.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LiteratureApplication implements CommandLineRunner {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LiteratureApplication.class, args);
        SpringApplication.exit(context, () -> 0);
    }

    @Override
    public void run(String... args) throws Exception {
        Main main = new Main(bookRepository, authorRepository);
        main.run();
        System.exit(0);
    }
}
