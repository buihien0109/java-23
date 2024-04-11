package com.example.demoapp.controller;

import com.example.demoapp.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
 * @Controller & @RestController: Đánh dấu một class là một Controller. Controller là nơi xử lý các request từ client.
 * @Controller: Trả về template. Ngoài ra có thể trả về dữ liệu ở trạng JSON, XML
 * @RestController: Trả về dữ liệu ở dạng JSON, XML, ...
 * @RestController = @Controller + @ResponseBody
 * Class ResponseEntity<T>: Đại diện cho một HTTP Response, bao gồm status code, headers và body.
 * */
@Controller
@RequestMapping("books")
public class BookController {
    private List<Book> books;

    public BookController() {
        books = new ArrayList<>();
        books.add(new Book(1, "Harry Potter", "J.K. Rowling", 1997));
        books.add(new Book(2, "The Lord of the Rings", "J.R.R. Tolkien", 1954));
        books.add(new Book(3, "The Da Vinci Code", "Dan Brown", 2003));
    }

    // Trả v template index.html nằm trong thư mục resources/templates
    @GetMapping("/home")
    public String getHome() {
        return "index";
    }

    // Lấy danh sách tất cả Book
    // GET: http://localhost:8080/books
    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED) // 201
    public List<Book> getAllBooks() {
        return books;
    }

    @GetMapping("getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks1() {
        return new ResponseEntity<>(books, HttpStatus.CREATED);
    }


    // Lấy thông tin Book theo id
    // GET: http://localhost:8080/books/1 -> id = 1, http://localhost:8080/books/2 -> id = 2, ...
    @GetMapping("/{id}")
    @ResponseBody
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
