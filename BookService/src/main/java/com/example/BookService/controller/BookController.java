package com.example.BookService.controller;

import com.example.BookService.database.Database;
import com.example.BookService.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    
    @Autowired
    private Database database;

    @RequestMapping(
            value = {"/books"},
            method = RequestMethod.POST, produces = "application/json"
    )
    public ResponseEntity<BookDto> postBook(@RequestBody BookDto book) {
        return new ResponseEntity<>(database.createBook(book), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/books"},
            method = RequestMethod.GET, produces = "application/json"
    )
    public ResponseEntity<List<BookDto>> getBooks() {
        return new ResponseEntity<>(database.getBooks(), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/books/{id}"},
            method = RequestMethod.GET, produces = "application/json"
    )
    public ResponseEntity<BookDto> getBookById(@PathVariable long id) {
        return new ResponseEntity<>(database.getBookById(id), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/books/{id}"},
            method = RequestMethod.PUT, produces = "application/json"
    )
    public ResponseEntity<BookDto> putBookById(@PathVariable long id, @RequestBody BookDto book) {
        book.setId(id);
        return new ResponseEntity<>(database.updateBook(book), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/books/{id}"},
            method = RequestMethod.DELETE, produces = "text/plain"
    )
    public ResponseEntity<String> deleteBookById(@PathVariable long id) {
        if(database.deleteBook(id)){
            return new ResponseEntity<>("Book successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Book not deleted. Perhaps no such Book in the Database", HttpStatus.NOT_FOUND);
    }


}
