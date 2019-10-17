package com.example.Facade.controller;

import com.example.Facade.dto.BookDto;
import com.example.Facade.dto.BookInfoDto;
import com.example.Facade.dto.UserDto;
import com.example.Facade.service.BookService;
import com.example.Facade.service.UserService;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private BookService bookService;
    private UserService userService;

    @Autowired
    public BookController() {

        this.userService = Feign.builder()
                .decoder(new GsonDecoder())
                .target(UserService.class, "http://localhost:8001/");

        this.bookService = Feign.builder()
                .decoder(new GsonDecoder())
                .target(BookService.class, "http://localhost:8002/");
    }

    @RequestMapping(
            value = {"/books/{id}"},
            method = RequestMethod.GET, produces = "application/json"
    )
    public ResponseEntity<BookInfoDto> getBookById(@PathVariable long id) {

        BookDto book = bookService.getBookById(id);

        UserDto user = userService.getUserById(book.getAuthorId());

        BookInfoDto bookInfoDto = new BookInfoDto();
        bookInfoDto.setId(book.getId());
        bookInfoDto.setTitle(book.getTitle());
        bookInfoDto.setAuthor(user);

        return new ResponseEntity<>(bookInfoDto, HttpStatus.OK);

    }

}
