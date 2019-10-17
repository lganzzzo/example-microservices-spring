package com.example.UserService.database.model;

import lombok.Data;

@Data
public class User {

    long id;
    String name;

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

}
