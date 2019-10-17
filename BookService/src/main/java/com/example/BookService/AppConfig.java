package com.example.BookService;

import com.example.BookService.database.Database;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Database database() {
        return new Database();
    }

}
