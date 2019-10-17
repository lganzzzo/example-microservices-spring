package com.example.Facade.service;

import com.example.Facade.dto.BookDto;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient("book-service")
public interface BookService {

    @RequestLine("GET /books")
    List<BookDto> getBooks();

    @RequestLine("GET /books/{id}")
    BookDto getBookById(@Param("id") Long id);

}
