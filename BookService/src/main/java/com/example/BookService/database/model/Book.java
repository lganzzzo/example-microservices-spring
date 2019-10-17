package com.example.BookService.database.model;

import lombok.Data;

@Data
public class Book {

    long id;
    long authorId;
    String title;

    public Book(long id, long authorId, String title) {
        this.id = id;
        this.authorId = authorId;
        this.title = title;
    }

}
