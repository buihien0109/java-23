package com.example.demoapp.controller;

import com.example.demoapp.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    private List<Book> books;

    public BookController() {
        books = new ArrayList<>();
        books.add(new Book(1, "Harry Potter", "J.K. Rowling", 1997));
        books.add(new Book(2, "The Lord of the Rings", "J.R.R. Tolkien", 1954));
        books.add(new Book(3, "The Da Vinci Code", "Dan Brown", 2003));
    }

    // Lấy danh sách tất cả Book
    // GET: http://localhost:8080/books
     @GetMapping
    // @RequestMapping(method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        return books;
    }


    // Lấy thông tin Book theo id
    // GET: http://localhost:8080/books/1 -> id = 1, http://localhost:8080/books/2 -> id = 2, ...
     @GetMapping("/{id}")
    // @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Book getBookById(@PathVariable int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null; // Throw exception
    }
}
