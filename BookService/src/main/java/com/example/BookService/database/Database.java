package com.example.BookService.database;

import com.example.BookService.database.model.Book;
import com.example.BookService.dto.BookDto;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Database {
    
    private long idCounter;
    private Map<Long, Book> booksById = new HashMap<>();

    public Database() {

        idCounter = 1;

        createBook(new BookDto(0, 1, "The Great Gatsby"));
        createBook(new BookDto(0, 2, "To Kill a Mockingbird"));
        createBook(new BookDto(0, 3, "Harry Potter and the Sorcerer's Stone"));
        createBook(new BookDto(0, 4, "1984"));
        createBook(new BookDto(0, 5, "The Catcher in the Rye"));

    }

    private Book serializeFromDto(BookDto bookDto) {
        return new Book(bookDto.getId(), bookDto.getAuthorId(), bookDto.getTitle());
    }

    private BookDto deserializeToDto(Book book) {
        return new BookDto(book.getId(), book.getAuthorId(), book.getTitle());
    }

    public synchronized BookDto createBook(BookDto bookDto) {
        Book book = serializeFromDto(bookDto);
        book.setId(idCounter ++);
        booksById.put(book.getId(), book);
        return deserializeToDto(book);
    }

    public synchronized BookDto updateBook(BookDto bookDto) {

        Book book = serializeFromDto(bookDto);

        if(book.getId() < 0) {
            throw new RuntimeException("Book Id cannot be less than 0");
        }

        Book existingBook = booksById.get(book.getId());
        if(existingBook == null) {
            throw new RuntimeException("Such book not found");
        }

        booksById.put(book.getId(), book);

        return deserializeToDto(book);

    }

    public synchronized BookDto getBookById(long id) {
        return deserializeToDto(booksById.get(id));
    }

    public synchronized List<BookDto> getBooks() {
        List<BookDto> result = new LinkedList<>();
        for(Book book : booksById.values()) {
            result.add(deserializeToDto(book));
        }
        return result;
    }

    public synchronized boolean deleteBook(long id) {
        return booksById.remove(id) != null;
    }

}
