package com.example.Facade.dto;

import lombok.Data;

@Data
public class BookInfoDto {

    long id;
    String title;
    UserDto author;

}
