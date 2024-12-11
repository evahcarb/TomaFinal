package com.internal.tomafinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TomafinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(TomafinalApplication.class, args);
    }
}
