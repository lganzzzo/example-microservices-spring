package com.example.UserService.dto;

import lombok.Data;

@Data
public class UserDto {

    long id;
    String name;

    public UserDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

}
