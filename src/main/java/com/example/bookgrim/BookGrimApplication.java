package com.example.bookgrim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookGrimApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookGrimApplication.class, args);
    }

}