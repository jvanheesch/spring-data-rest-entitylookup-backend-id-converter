package com.github.jvanheesch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        Author author = new Author();
        author.setName("Jon Brisbin");
        authorRepository.save(author);

        Book book = new Book();
        book.setTitle("Spring Data REST Reference Guide");
        book.setAuthor(author);
        bookRepository.save(book);
    }
}
