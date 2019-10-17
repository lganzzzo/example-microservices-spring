package com.example.UserService;

import com.example.UserService.database.Database;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Database database() {
        return new Database();
    }

}
