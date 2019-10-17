package com.example.BookService.dto;

import lombok.Data;

@Data
public class BookDto {

    long id;
    long authorId;
    String title;

    public BookDto(long id, long authorId, String title) {
        this.id = id;
        this.authorId = authorId;
        this.title = title;
    }
}
