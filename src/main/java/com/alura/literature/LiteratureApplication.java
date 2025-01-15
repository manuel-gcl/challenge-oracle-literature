package com.alura.literature;

import com.alura.literature.main.Main;
import com.alura.literature.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteratureApplication implements CommandLineRunner {
	@Autowired
	private BookRepository repository;

	public static void main(String[] args) {

		SpringApplication.run(LiteratureApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(repository);
		main.run();
	}
}
